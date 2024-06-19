import guli
import json as json
import streamlit as st
import requests as req
from time import sleep


URL = 'http://localhost:8080'


st.set_page_config(
    page_title = "Login - UcUnDF",
    page_icon = "🔵"
)   

def verify(acesso, cpf, senha):
    # parametros para consulta de requisição no springboot
    params = {"cpf": cpf, "senha": senha}
    
    #switch case para verificar acessos
    match acesso:
        case 'Professor':
            res = req.get(URL + '/login/professor', params=params)

            # caso a requisição seja autorizada
            if res.json() is True:
                st.success('Entrando...', icon="✅")
                # armazenando a variável do cpf através da biblioteca guli
                guli.GuliVariable("cpf").setValue(cpf)
                # validando autenticação
                guli.GuliVariable("auth").setValue(True)
                sleep(1)
                st.switch_page('pages/professor_page.py')
                
            else:
                if cpf and senha != None:
                    st.error("Você inseriu dados incorretos.", icon = "🚨")
        case 'Aluno':
            res = req.get(URL + '/login/aluno', params=params)
            if res.json() is True:
                st.success("Entrando...", icon="✅")
                guli.GuliVariable("cpf").setValue(cpf)
                guli.GuliVariable("auth").setValue(True)
                sleep(1)
                st.switch_page('pages/aluno_page.py')
            else:
                if cpf and senha != None:
                    st.error("Você inseriu dados incorretos.", icon = "🚨")

def login():
    with st.form(key='login_form'):
        # inputs de texto para validação do login
        
        cpf = st.text_input("CPF", key='cpf')
        senha = st.text_input("Senha", key='senha')
        
        # check box para enviar uma requisicao ao "login controller" baseada no nível de acesso do usuário
        acesso = st.radio('Selecione o nível de acesso:', ['Professor', 'Aluno'])
       
        #botão para enviar os dados de login para a verificação 
        st.form_submit_button("Entrar", on_click = verify(acesso, cpf, senha))
        
def start():
    st.title("Sistema de Unidades Curriculares   :blue[UnDF] ", anchor=False)
    login()
    
start()