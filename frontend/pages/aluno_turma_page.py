import streamlit as st
from login_page import URL
import requests as req
import json
import guli
from streamlit_card import card

st.set_page_config(
    page_title="Unidade Curricular - UcUnDF",
    page_icon="📚",
    layout="centered"
)

st.markdown(
    """
    <style>
    .footer {
        text-align: center;
        padding: 10px;
        background-color: #ffffff;
        border-top: 2px solid #2661bf;
        position: fixed;
        width: 100%;
        bottom: 0;
        left: 0;
    }
    </style>
    """,
    unsafe_allow_html=True
)

# pegando a variavel de nota da pagina passada, e assimilando com o objeto turma
turma_id = guli.GuliVariable('turma_aluno_id').get()

response = req.get(URL + '/alunos/turma/%s' % turma_id)
aluno_json = response.json()

# Requisição para obter feedbacks do aluno
response_feedback = req.get(URL + '/feedbacks/aluno/%s' % turma_id)

feedback_json = None
if response_feedback.status_code == 200:
    try:
        feedback_json = response_feedback.json()
    except json.JSONDecodeError:
        print("Failed to parse response as JSON")
else:
    print(f"Request failed with status code {response_feedback.status_code}")

def create_feedback(titulo, corpo):
    url = f"{URL}/feedbacks/aluno/{aluno_json['aluno']['id']}/turmas/{turma_id}/feedback"
    data = {
        "titulo": titulo,
        "corpo": corpo
    }
    headers = {"Content-Type": "application/json"}
    try:
        response = req.post(url, headers=headers, json=data)
        if response.status_code == 200:
            st.success("Feedback criado com sucesso!")
        else:
            st.error(f"Erro ao criar feedback: {response.text}")
    except Exception as e:
        st.error(f"Ocorreu um erro: {str(e)}")

def welcome():
    st.markdown('## Unidade Curricular: :blue[%s]' %aluno_json['turma']['nome'])

def container_aluno():
    container_resultados = st.container(height=240, border=True)

    container_resultados.markdown(
        '**<span style="font-size: 18px;">:blue[Nome]:</span>** ' + aluno_json['aluno']['nome'], unsafe_allow_html=True)
    container_resultados.markdown(
        '**<span style="font-size: 18px;">:blue[Curso]:</span>** ' + aluno_json['aluno']['curso'],
        unsafe_allow_html=True)
    container_resultados.markdown(
        '**<span style="font-size: 18px;">:blue[Professor]:</span>** ' + aluno_json['turma']['professor']['nome'],
        unsafe_allow_html=True)
    container_resultados.markdown(
        '**<span style="font-size: 18px;">:blue[Sala de Aula]:</span>** ' + str(aluno_json['turma']['sala']['numero']),
        unsafe_allow_html=True)
    container_resultados.markdown(
        '**<span style="font-size: 18px;">:blue[Nota final]:</span>** ' + str(aluno_json['nota']),
        unsafe_allow_html=True)

    if st.button('Adicionar Feedback'):
        st.session_state['show_feedback_modal'] = True

def feedback_modal():
    if st.session_state.get('show_feedback_modal', False):
        with st.form("feedback_form"):
            titulo = st.text_input("Título")
            corpo = st.text_area("Feedback")
            enviado = st.form_submit_button("Enviar")

            if enviado:
                st.session_state['show_feedback_modal'] = False
                enviar_feedback(titulo, corpo)

def enviar_feedback(titulo, corpo):
    # Preparando os dados para envio
    feedback_data = {
        "titulo": titulo,
        "corpo": corpo,
        "aluno": {"id": aluno_json['aluno']['id']},
        "turma": {"id": aluno_json['turma']['id']},
        "professor": {"id": aluno_json['turma']['professor']['id']}
    }

    # Log dos dados para depuração
    print("Enviando feedback:", feedback_data)

    # Envio dos dados ao backend
    response = req.post(
        URL + f'/feedbacks/aluno/post/{aluno_json["aluno"]["id"]}',
        json=feedback_data,
        headers={'Content-Type': 'application/json'}
    )

    # Análise da resposta do servidor
    if response.status_code in [200, 201]:
        st.success("Feedback enviado com sucesso!")
    else:
        st.error(f"Falha ao enviar feedback: {response.status_code} - {response.text}")


def start():
    welcome()
    container_aluno()
    feedback_modal()

    st.markdown(
        """
        <div class="footer">
            <p style="margin: 0px;"><span style="font-size: 14px;">Junho de 2024 • Universidade do Distrito Federal</span></p>
            <p><span style="font-size: 12px;"><a href="#">Política de Privacidade</a> | <a href="#">Termos de Uso</a></span></p>
        </div>
        """,
        unsafe_allow_html=True
    )

start()