import streamlit as st
import requests as req
import json

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


def click_button():
    st.session_state.click = True
    if st.session_state.click :
        
        
    
    

st.title('Unidades Curriculares - :blue[UnDF]')


