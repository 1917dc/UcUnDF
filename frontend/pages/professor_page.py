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

__professor = {
    "id" : None,
    "nome" : None,
    "cpf" : None,
    "senha" : None
}
professor = (req.get(URL + '/professores/professor', params = {"cpf" : cpf})).json()


# mensagem de boas vindas

def welcome():
    
    # a biblioteca guli retorna a variável de cpf armazenada
    st.title(('Bem-vindo(a), Professor(a) :blue[%s]!' %(professor['nome'])), anchor=False)

def turma_professor(turma_id):
    guli.GuliVariable("turma_id").setValue(turma_id)
    st.switch_page('pages/professor_turma_page.py')

# função para exibir cards do professor

def cards_professor():
    
    # definindo como funciona o objeto de turma
    turmas = {
        "id" : None,
        "nome" : None,
        "descricao" : None,
    }
    
    # pegando as turmas do banco de dados para json
    turmas_json = req.get(URL + '/professores/%i/turmas' %professor['id']).json()
    turmas = []
    
    # for each no json de turmas para fazer o append das turmas
    for turma in turmas_json:
        
        # definindo quais informações vou pegar do json
        detalhes_turma = {'id': None, 'nome' : None, 'descricao' : None, 'cargaHoraria' : None}
        
        #definindo o array de informações de acordo com as respectivas turmas
        
        detalhes_turma['id'] = turma['id']
        detalhes_turma['nome'] = turma['nome']
        detalhes_turma['descricao'] = turma['disciplina']['descricao']
        detalhes_turma['cargaHoraria'] = turma['disciplina']['cargaHoraria']
                
        turmas.append(detalhes_turma)

    # for que exibe as disciplinas
    col1, col2 = st.columns(2)
    for turma in turmas:
        with col1 if turmas.index(turma) % 2 == 0 else col2:
            card_turma = card(
                title=turma['nome'],
                text=(turma['descricao']),
                on_click=lambda: turma_professor(turma['id']),
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
    cards_professor()

start()
