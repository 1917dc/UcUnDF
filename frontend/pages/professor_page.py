import guli
import json
import streamlit as st
import requests as req
from PIL._imaging import font

from login_page import URL
from streamlit_card import card

st.set_page_config(
    page_title="Professor - UcUnDF",
    page_icon="📚",
    layout="wide"
)

st.markdown(
    """
    <style>
    .header {
        display: flex;
        align-items: center;
        background-color: #2694bf;
        padding: 10px 20px;
        top: 0;
        width: 100%;
        z-index: 1000;
        margin-bottom: 40px;
        border-radius: 10px;
    }
    .header img {
        height: 40px;
        cursor: pointer;
        margin: 15px;
    }
    .header-title {
        color: white;
        font-size: 24px;
        font-weight: bold;
        margin: 0;
    }
    
    .footer {
        text-align: center;
        padding: 10px;
        background-color: #ffffff;
        border-top: 2px solid #2661bf;
        width: 100%;
        bottom: 0;
        left: 0;
    }

    </style>
    """,
    unsafe_allow_html=True
)

cpf = guli.GuliVariable("cpf").get()

# pegando as informações do professor

__professor = {
    "id" : None,
    "nome" : None,
    "cpf" : None,
    "senha" : None
}
professor = (req.get(URL + '/professores/professor', params = {"cpf" : cpf})).json()

st.markdown(
    f"""
    <div class="header">
        <a href="/"><img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhR3jbnt6fzzQ7vy7rvBk7Xsz7-WkA0G73EctYBD5kYTC6mdBho5mJaejWEgMvCjjnvHEir_Ydr13pHP2DC0Y9XyeFfK3kHcPl_sMIzCSEQE70ZCfZIWg8uZNeLKquhuv7yq7q-B-V3ViVrQwLQaiccoju5g-el6A439S4_Ym8zApZtx8OCdO6kuOHm/s432/undf.png" alt="Logo"></a>
        <h1 class="header-title" style="font-size: 42px;">Gerenciador de Disciplinas</h1>
    </div>
    """,
    unsafe_allow_html=True
)

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
    cols = st.columns(3)

    for i, turma in enumerate(turmas):
        with cols[i % 3]:
            card_turma = card(
                title=turma['nome'],
                text=turma['descricao'],
                on_click=lambda turma_id=turma['id']: turma_professor(turma_id),
                styles={
                    "card": {
                        "width": "320px",
                        "height": "140px",
                        "border-radius": "15px",
                        "box-shadow": "0 4px 8px rgba(0, 0, 0, 0.2)",
                        "margin": "10px",
                        "padding": "10px",
                        "border": "2px solid #2661bf",
                        "background-color": "#ffffff",
                    },
                    "text": {
                        "font-family": "Arial, sans-serif",
                        "font-size": "16px",
                        "color": "#444444"
                    },
                    "title": {
                        "font-family": "Arial, sans-serif",
                        "font-size": "20px",
                        "color": "#2661bf",
                        "margin-bottom": "5px"
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