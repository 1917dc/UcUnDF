class Aluno:
    def __init__(self, id, nome, cpf, senha, curso):
        self.id = id
        self.nome = nome
        self.cpf = cpf
        self.senha = senha
        self.curso = curso

    def __repr__(self):
        return f"Aluno(id={self.id}, nome={self.nome}, cpf={self.cpf}, senha={self.senha}, curso={self.curso})"
