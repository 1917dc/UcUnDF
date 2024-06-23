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
    turma_alunos_json = (req.get(URL + "/turmas/%s/alunos" %turma_id)).json()
    
    alunos_nomes = []
    alunos_cursos = []
    alunos_notas = []
    alunos_laudos = []
    alunos_ids = []

    for aluno in turma_alunos_json:

        # definindo quais informações vou pegar do json
        detalhes_aluno = {'id': None, 'aluno_id' : None, 'nota' : None, 'laudo' : None, 'curso' : None}

        # pegando laudo respectivamente
        laudo = {'id' : None, 'aluno' : None,'condicao' : None,'descricao' : None, 'validade' : None}
        if req.get(URL + '/laudos/%s' %aluno['aluno']['id']).json() == None:
            laudo['descricao'] = 'Sem laudo'
        else:
            laudo = req.get(URL + '/laudos/%s' %aluno['aluno']['id']).json()


        detalhes_aluno['id'] = aluno['id']
        detalhes_aluno['aluno_nome'] = aluno['aluno']['nome']
        detalhes_aluno['curso'] = aluno['aluno']['curso']
        detalhes_aluno['nota'] = aluno['nota']
        detalhes_aluno['laudo'] = laudo['descricao']

        alunos_ids.append(detalhes_aluno['id'])
        alunos_nomes.append(detalhes_aluno['aluno_nome'])
        alunos_notas.append(detalhes_aluno['nota'])
        alunos_cursos.append(detalhes_aluno['curso'])
        alunos_laudos.append(detalhes_aluno['laudo'])



    df = pd.DataFrame({
        'Indice' : alunos_ids,
        'Nome' : alunos_nomes,
        'Curso' : alunos_cursos,
        'Laudo' : alunos_laudos,
        'Nota' : alunos_notas
    })
    original_df = df.copy()
    # Exiba o editor de dataframe
    edited_df = st.data_editor(df, disabled=["Indice","Nome", "Curso", "Laudo"], hide_index= True, width=900, key= 'df_copy', column_config={"Indice" : None})

    # Crie um botão para enviar alterações
    if st.button('Enviar Alterações'):
        # Encontre as diferenças entre os dataframes original e editado
        diff = pd.concat([original_df, edited_df]).drop_duplicates(keep=False)

        # Imprima as alterações
        counter = 0
        for index, row in diff.iterrows():
            counter += 1
            if counter == 2:
                print(f"Índice: {row['Indice']}, Nome: {row['Nome']}, Nota: {row['Nota']}")
                url = URL + "/alunos/%s/nota" %row['Indice']
                headers = {"Content-Type": "application/json"}
                data_json = row['Nota']
                response = req.put(url, headers=headers, json=float(data_json))
                if response.status_code != 200:
                    print(f"Error: Failed to update nota for aluno_id {row['Indice']}. Status code: {response.status_code}")


def start():
    welcome()
    st.divider()
    table_alunos()
    
start()