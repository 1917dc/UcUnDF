import streamlit as st
from login_page import URL 
import requests as req
import guli
from streamlit_card import card 

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

# função que mostra os cards de cada opção
def card_turma():
    col1, col2 = st.columns(2)
    
    # colunas que organizam os cards 
    with col1:
        card_alunos = card(
            title='Alunos',
            text='',
            on_click=lambda: turma_alunos(turma['id']),
            styles={
                "card": {
                    "width": "250px",
                    "height": "150px",
                    "border-radius": "30px",
                    "box-shadow": "0 0 0px rgba(0,0,0,0.5)",
                },
                "text": {
                    "font-family": "sans serif",
                    "font-size" : "15px"
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
                    "width": "250px",
                    "height": "150px",
                    "border-radius": "30px",
                    "box-shadow": "0 0 0px rgba(0,0,0,0.5)",
                },
                "text": {
                    "font-family": "sans serif",
                    "font-size" : "15px"
                }
            }
        )
        
# função que mostra a mensagem de boas vindas
def welcome():
    st.markdown('## Turma de :blue-background[%s]' %turma['nome'])
    col1, col2 = st.columns(2)
    with col1:
        st.markdown('- **Professor: :blue[%s]**' %turma['professor']['nome'])
    with col2:
        st.markdown('- **Sala: %i**' %turma['sala']['numero'])
    
# função que começa a aplicação
def start():
    welcome()
    st.divider()
    card_turma()
    
    
    
start()