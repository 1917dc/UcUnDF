import streamlit as st
from login_page import URL
import requests as req
import json
import guli
from streamlit_card import card

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

@st.experimental_dialog("Crie o feedback")
def create_feedback():

    titulo_feedback = st.text_area("Título do feedback", key="titulo_feedback")
    corpo_feedback = st.text_area("Corpo do feedback", key="corpo_feedback")

    if st.button("Enviar"):
        st.rerun()



# pegando a variavel de nota da pagina passada, e assimilando com o objeto turma
turma_id = guli.GuliVariable('turma_aluno_id').get()

response = req.get(URL + '/alunos/turma/%s' %turma_id)
aluno_json = response.json()

response_feedback = req.get(URL + '/feedbacks/aluno/%s' %turma_id)

feedback_json = None

response_feedback = req.get(URL + '/feedbacks/aluno/%s' %turma_id)
if response_feedback.status_code == 200:
    try:
        feedback_json = response_feedback.json()
    except json.JSONDecodeError:
        print("Failed to parse response as JSON")
else:
    print(f"Request failed with status code {response_feedback.status_code}")

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

    # container_feedback = st.container(height=300, border=True)
    # container_feedback.markdown('### **:blue[Feedback]:** ')
    # if feedback_json is not None and 'corpo' in feedback_json:
    #     container_feedback.markdown(feedback_json['corpo'])
    # else:
    #     container_feedback.markdown('Sem feedback')

    if st.button('Gerar feedback'):
        create_feedback()



def start():
    welcome()
    container_aluno()

start()

st.markdown(
    """
    <div class="footer">
        <p><span style="font-size: 14px;">Desenvolvido pela Equipe Epsilon - Junho de 2024 • Universidade do Distrito Federal</span></p>
        <p><span style="font-size: 12px;"><a href="#">Política de Privacidade</a> | <a href="#">Termos de Uso</a></span></p>
    </div>
    """,
    unsafe_allow_html=True
)