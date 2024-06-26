import guli
import json
import streamlit as st
import requests as req
from login_page import URL
from streamlit_card import card


# st.set_page_config(
#     page_title = "Professor - UcUnDF",
#     page_icon = "📚"
# )

cpf = guli.GuliVariable("cpf").get()

# pegando as informações do professor

__aluno = {
    "id" : None,
    "nome" : None,
    "cpf" : None,
    "senha" : None,
    "curso" : None
}
aluno = (req.get(URL + '/alunos/aluno', params = {"cpf" : cpf})).json()


# mensagem de boas vindas

def welcome():

    # a biblioteca guli retorna a variável de cpf armazenada
    st.title(('Bem-vindo(a), :blue[%s]!' %(aluno['nome'])), anchor=False)

def aluno_turma(turma_aluno_id):
    guli.GuliVariable("turma_aluno_id").setValue(turma_aluno_id)
    st.switch_page('pages/aluno_turma_page.py')

# função para exibir cards do professor

def cards_aluno():

    # definindo como funciona o objeto de turma
    turmas = {
        "id" : None,
        "nome" : None,
        "descricao" : None,
    }

    # pegando as turmas do banco de dados para json
    turmas_json = req.get(URL + '/turmas/aluno/%s' %aluno['id']).json()
    turmas = []

    # for each no json de turmas para fazer o append das turmas
    for turma_aluno in turmas_json:

        # definindo quais informações vou pegar do json
        detalhes_turma_aluno = {'id': None, 'turma' : None, 'aluno' : None, 'nota' : None}

        #definindo o array de informações de acordo com as respectivas turmas

        detalhes_turma_aluno['id'] = turma_aluno['id']
        detalhes_turma_aluno['turma'] = turma_aluno['turma']
        detalhes_turma_aluno['aluno'] = turma_aluno['aluno']
        detalhes_turma_aluno['nota'] = turma_aluno['nota']

        turmas.append(detalhes_turma_aluno)

    # for que exibe as disciplinas

    col1, col2 = st.columns(2)

    for turma_aluno in turmas:
        with col1 if turmas.index(turma_aluno) % 2 == 0 else col2:
            card_turma = card(
                title=turma_aluno['turma']['nome'],
                text=(turma_aluno['turma']['disciplina']['descricao']),
                on_click=lambda: aluno_turma(turma_aluno['id']),
                styles={
                    "card": {
                        "width": "320px",
                        "height": "90px",
                        "border-radius": "25px",
                        "box-shadow": "0 0 0px rgba(0,0,0,0)",
                        "margin": "0px",
                        "padding": "35%",
                        "border": "4px solid rgb(0, 104, 201)",
                    },
                    "text": {
                        "font-family": "Arial, sans-serif",
                        "font-size" : "20px",
                        "color" : "#rgb(38, 39, 48)"
                    },
                    "title": {
                        "font-family": "Arial, sans-serif",
                        "font-size" : "27px",
                        "color" : "#2661bf"
                    },
                    "filter": {
                        "background-color": "#fafafa",
                    }
                }
            )

def start():
    welcome()
    st.divider()
    cards_aluno()

start()