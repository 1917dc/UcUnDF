import streamlit as st
from login_page import URL
import requests as req
import json
import guli
from streamlit_card import card

@st.experimental_dialog("Crie o feedback")
def create_feedback():

    titulo_feedback = st.text_area("Título do feedback", key="titulo_feedback")
    corpo_feedback = st.text_area("Corpo do feedback", key="corpo_feedback")

    if st.button("Enviar"):
        st.rerun()



# pegando a variavel de nota da pagina passada, e assimilando com o objeto turma
turma_id = guli.GuliVariable('turma_aluno_id').get()

response = req.get(URL + '/alunos/turma/%s' %turma_id)
aluno_json = response.json()

response_feedback = req.get(URL + '/feedbacks/aluno/%s' %turma_id)

feedback_json = None

response_feedback = req.get(URL + '/feedbacks/aluno/%s' %turma_id)
if response_feedback.status_code == 200:
    try:
        feedback_json = response_feedback.json()
    except json.JSONDecodeError:
        print("Failed to parse response as JSON")
else:
    print(f"Request failed with status code {response_feedback.status_code}")

def welcome():
    st.markdown('## Unidade Curricular: :blue[%s]' %aluno_json['turma']['nome'])

def container_aluno():
    container_resultados = st.container(height=225, border=True)

    container_resultados.markdown('**:blue[Nome]:** ' + aluno_json['aluno']['nome'])
    container_resultados.markdown('**:blue[Curso]:** ' + aluno_json['aluno']['curso'])
    container_resultados.markdown('**:blue[Professor]:** ' + aluno_json['turma']['professor']['nome'])
    container_resultados.markdown('**:blue[Sala de Aula]:** ' + str(aluno_json['turma']['sala']['numero']))
    container_resultados.markdown('**:blue[Nota final]:** ' + str(aluno_json['nota']))

    # container_feedback = st.container(height=300, border=True)
    # container_feedback.markdown('### **:blue[Feedback]:** ')
    # if feedback_json is not None and 'corpo' in feedback_json:
    #     container_feedback.markdown(feedback_json['corpo'])
    # else:
    #     container_feedback.markdown('Sem feedback')

    if st.button('Gerar feedback'):
        create_feedback()



def start():
    welcome()
    container_aluno()

start()