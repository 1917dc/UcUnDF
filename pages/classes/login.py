import streamlit as st
import requests as req
import json

from aluno import Aluno


def alunos():
    res = req.get("http://localhost:8080/turmas/1/alunos")
    json_data = res._content.decode("utf-8")

    alunos_list = json.loads(json_data)

    alunos = [Aluno(**aluno_dict) for aluno_dict in alunos_list]

    for aluno in alunos:
        print(aluno)


def login():
    cpf = st.session_state.input_cpf
    senha = st.session_state.input_senha

    res = req.post("https://localhost:8080/login", data = {"cpf": cpf, "senha": senha})
    
    if res.status_code == '200':
        st.sucess('Login efetuado com sucesso!', icon = '✅')



def login_form():
    cpf = st.text_input("CPF")
    senha = st.text_input("Senha", type='password')
    
    st.button("Entrar", on_click=click_button)

        
        
    
    

st.title('Unidades Curriculares - :blue[UnDF]')


alunos()