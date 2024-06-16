import streamlit as st
import requests as req
import json

st.title('Unidades Curriculares - :blue[UnDF]')





with st.form('login'):
    st.write('Insira suas informações de login: ')
    
    name = st.text_input('CPF')
    senha = st.text_input('Senha')
    
    submitted = st.form_submit_button("Entrar")