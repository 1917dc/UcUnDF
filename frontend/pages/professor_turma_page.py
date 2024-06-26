import streamlit as st
from login_page import URL 
import requests as req
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

# definindo turma
turma = {
    'id' : None,
    'nome' : None,
    'professor' : None,
    'sala' : None
}
# pegando a variavel de nota da pagina passada, e assimilando com o objeto turma
turma_id = guli.GuliVariable('turma_id').get()
turma = req.get(URL + '/turmas/%i' %turma_id).json()

# definindo a função para trocar de pagina
def turma_alunos(turma_id):
    guli.GuliVariable("turma_id").setValue(turma_id)
    st.switch_page('pages/professor_alunos_page.py')

def turma_notas(turma_id):
    guli.GuliVariable("turma_id").setValue(turma_id)
    st.switch_page('pages/professor_notas_page.py')

def turma_analise(turma_id):
    guli.GuliVariable("turma_id").setValue(turma_id)
    st.switch_page('pages/professor_analise_page.py')

# função que mostra os cards de cada opção
def card_turma():
    col1, col2, col3 = st.columns(3)

    # colunas que organizam os cards 
    with col1:
        card_alunos = card(
            title='Alunos',
            text='',
            on_click=lambda: turma_alunos(turma['id']),
            styles={
                "card": {
                    "width": "210px",
                    "height": "120px",
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
    with col2:
        card_notas = card(
            title='Notas',
            text='',
            on_click=lambda: turma_notas(turma['id']),
            styles={
                "card": {
                    "width": "210px",
                    "height": "120px",
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
    with col3:
        card_shiny = card(
            title='Desempenho',
            text='',
            on_click=lambda: turma_analise(turma['id']),
            styles={
                "card": {
                    "width": "210px",
                    "height": "120px",
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
        
# função que mostra a mensagem de boas vindas
def welcome():
    st.markdown('## Turma de :blue[%s]' %turma['nome'])
    col1, col2 = st.columns(2)
    with col1:
        st.markdown('- <span style="font-size: 24px;">**Professor: :blue[%s]**</span>' %turma['professor']['nome'], unsafe_allow_html=True)
    with col2:
        st.markdown('- <span style="font-size: 24px;">**Sala: :blue[%i]**</span>' %turma['sala']['numero'], unsafe_allow_html=True)
    
# função que começa a aplicação
def start():
    welcome()
    st.divider()
    card_turma()
    
    
    
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