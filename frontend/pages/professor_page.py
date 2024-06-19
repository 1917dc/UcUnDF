import guli
import json
import streamlit as st
import requests as req
from login_page import URL


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
    st.title(('Bem vindo professor :blue[%s]!' %(professor['nome'])), anchor=False)

# função para exibir cards do professor

def cards_professor():
    
    # definindo como funciona o objeto de turma
    turmas = {
        "id" : None,
        "nome" : None,
        "descricao" : None
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
    
    for turma in turmas:
        
        # montando e organizando as disciplinas em rows

        for col in st.columns(1) :
            tile = col.container(height=220)
            
            tile.markdown("### %s" %turma['nome'])
            tile.markdown(" - Descrição: %s" %turma['descricao'])
            tile.markdown(" - Carga horária: %s hrs" %turma['cargaHoraria'])
            tile.button('Entrar', key=turma['id'])

def start():
    welcome()
    st.divider()
    cards_professor()

start()