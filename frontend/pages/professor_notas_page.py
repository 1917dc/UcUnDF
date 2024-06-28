import streamlit as st
from login_page import URL
import requests as req
import json
import guli
import pandas as pd

st.set_page_config(
    page_title="Notas Finais - UcUnDF",
    page_icon="📚"
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

turma = {
    'id' : None,
    'professor': None,
    'nome' : None
}

turma_id = guli.GuliVariable('turma_id').get()
turma = req.get(URL + '/turmas/%s' %turma_id).json()

# Função para listar feedbacks por turma
def list_feedbacks_by_turma(turma_id):
    url = f"{URL}/feedbacks/turma/{turma_id}"
    response = req.get(url)
    if response.status_code == 200:
        feedbacks = response.json()
        return feedbacks
    else:
        st.error(f"Erro ao buscar feedbacks da turma {turma_id}. Status code: {response.status_code}")
        return None

# Função para exibir feedbacks
def show_feedbacks():
    turma_id = guli.GuliVariable('turma_id').get()
    feedbacks = list_feedbacks_by_turma(turma_id)

    if feedbacks:
        st.markdown("## Feedbacks da Turma")

        table_data = []
        for feedback in feedbacks:
            table_data.append({
                "Aluno": feedback['aluno']['nome'],
                "Título": feedback['titulo'],
                "Corpo": feedback['corpo']
            })

        # Exibindo a tabela
        df = pd.DataFrame(table_data)

        st.table(df.set_index('Aluno'))

def welcome():
    st.markdown('## Notas Finais de :blue[%s]' %turma['nome'])

def table_alunos():
    turma_alunos_json = (req.get(URL + "/turmas/%s/alunos" %turma_id)).json()

    alunos_nomes = []
    alunos_cursos = []
    alunos_notas = []
    alunos_feedbacks = []
    alunos_ids = []

    for aluno in turma_alunos_json:

        # definindo quais informações vou pegar do json
        detalhes_aluno = {'id': None, 'aluno_id' : None, 'nota' : None, 'curso' : None}

        detalhes_aluno['id'] = aluno['id']
        detalhes_aluno['aluno_nome'] = aluno['aluno']['nome']
        detalhes_aluno['curso'] = aluno['aluno']['curso']
        detalhes_aluno['nota'] = aluno['nota']

        alunos_ids.append(detalhes_aluno['id'])
        alunos_nomes.append(detalhes_aluno['aluno_nome'])
        alunos_notas.append(detalhes_aluno['nota'])
        alunos_cursos.append(detalhes_aluno['curso'])

    df = pd.DataFrame({
        'Indice' : alunos_ids,
        'Nome' : alunos_nomes,
        'Curso' : alunos_cursos,
        'Nota' : alunos_notas
    })
    original_df = df.copy()
    # Exiba o editor de dataframe
    edited_df = st.data_editor(df, disabled=["Nome", "Curso", "Laudo"], hide_index= True, width=900, key= 'df_copy', column_config={"Indice" : None})

    col1, col2 = st.columns(2)

    with col1:
        if st.button('Enviar Alterações'):
            diff = pd.concat([original_df, edited_df]).drop_duplicates(keep=False)

            counter = 0
            for index, row in diff.iterrows():
                counter += 1
                if counter == 2:
                    print(f"Índice: {row['Indice']}, Nome: {row['Nome']}, Nota: {row['Nota']}")
                    url = URL + "/alunos/%s/nota" %row['Indice']
                    headers = {"Content-Type": "application/json"}
                    data_json = row['Nota']
                    response = req.put(url, headers=headers, json=float(data_json))
                    if response.status_code != 200:
                        print(f"Error: Failed to update nota for aluno_id {row['Indice']}. Status code: {response.status_code}")

    st.divider()

    #with col2:
    #    if st.button('Gerar feedback'):
    #       create_feedback()

def start():
    welcome()
    st.divider()
    table_alunos()
    show_feedbacks()

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