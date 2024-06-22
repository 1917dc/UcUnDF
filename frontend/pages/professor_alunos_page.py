import streamlit as st
from login_page import URL
import requests as req
import json
import guli
import pandas as pd

turma = {
    'id' : None,
    'professor': None,
    'nome' : None
}

turma_id = guli.GuliVariable('turma_id').get()
turma = req.get(URL + '/turmas/%s' %turma_id).json()



def welcome():
    st.markdown('## Alunos de :blue[%s]' %turma['nome'])
    
def table_alunos():
    alunos = {
    "id" : None,
    "aluno_nome" : None,
    "nota" : None,
    "curso" : None,
    }
    
    turma_alunos_json = (req.get(URL + "/turmas/%s/alunos" %turma_id)).json()
    
    alunos_nomes = []
    alunos_cursos = []
    alunos_notas = []

    for aluno in turma_alunos_json:
        
        # definindo quais informações vou pegar do json
        detalhes_aluno = {'id': None, 'aluno_id' : None, 'nota' : None, 'laudo' : None, 'curso' : None}
        
        detalhes_aluno['id'] = aluno['id']
        detalhes_aluno['aluno_nome'] = aluno['aluno']['nome']
        detalhes_aluno['curso'] = aluno['aluno']['curso']
        detalhes_aluno['nota'] = aluno['nota']
                
        alunos_nomes.append(detalhes_aluno['aluno_nome'])
        alunos_notas.append(detalhes_aluno['nota'])
        alunos_cursos.append(detalhes_aluno['curso'])
        
    
    df = pd.DataFrame({
        'Nome' : alunos_nomes,
        'Curso' : alunos_cursos,
        'Nota' : alunos_notas
    })    
    st.data_editor(df, column_config={"alunos_nomes" : "Nomes","alunos_curso" : "Curso","alunos_notas" : "Nota"}, 
                   disabled=["Nome", "Curso"], hide_index= True, width=900, ) 



    
def start():
    welcome()
    st.divider()
    table_alunos()
    
start()