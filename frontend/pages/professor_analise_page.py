import streamlit as st
import plotly.express as px
import plotly.io as pio
import pandas as pd
import requests as req
import guli
from login_page import URL

pio.templates.default = "seaborn"
st.set_page_config(page_title="Análise de notas", page_icon=":bar_chart:")

turma_id = guli.GuliVariable('turma_id').get()
alunos = req.get(URL + '/turmas/%s/alunos' %turma_id).json()

notas = 0.0
for aluno in alunos:
    notas += float(aluno['nota'])

qtdAlunos = len(alunos)
media = notas / qtdAlunos

data = {
    'Aluno': [aluno['aluno']['nome'] for aluno in alunos],
    'Nota': media
}

df = pd.DataFrame(data)
fig1 = px.bar(df, x='Aluno', y='Nota', title='Notas dos alunos',
              labels={'Nota': 'Nota (out of 10)'},
              range_y=[0, 10])
st.markdown('### Média da turma: %.2f' % media)
st.plotly_chart(fig1)
