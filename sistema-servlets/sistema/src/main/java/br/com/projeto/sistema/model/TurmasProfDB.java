package br.com.projeto.sistema.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class TurmasProfDB {

	private static final String URL = "jdbc:sqlite:C:\\Users\\leozi\\Desktop\\Faculdade\\Bases II\\Computação\\Java\\Workspace Projeto\\sistema\\src\\main\\java\\db.sqlite3";

	public static List<Turma> getAll() {
		try (Connection con = DriverManager.getConnection(URL)) {
			//turmas
			String sql = "SELECT Disciplina.nome AS disciplina_nome * FROM Turma JOIN Disciplina ON Turma.disciplina_id = Disciplina.id JOIN Sala ON Turma.sala_id = Sala.id";
			Statement stmt = con.createStatement();
			ResultSet rsTurmas = stmt.executeQuery(sql);
			
			List<Turma> turmas = new ArrayList<>();
			while (rsTurmas.next()) {
				//info geral
				String nome = rsTurmas.getString("nome");
				Sala sala = new Sala(rsTurmas.getInt("sala_id"), rsTurmas.getInt("numero"), rsTurmas.getInt("capacidade"));
				Disciplina disciplina = new Disciplina(rsTurmas.getInt("disciplina_id"), rsTurmas.getString("disciplina_nome"), rsTurmas.getString("descricao"), rsTurmas.getString("cargaHoraria"));
				
				//professor
				sql = "SELECT * FROM Professor WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rsTurmas.getInt("professor_id"));
				ResultSet rsProfessor = pstmt.executeQuery();
				Professor professor = new Professor(rsProfessor.getInt("id"), rsProfessor.getString("nome"),
						rsProfessor.getString("cpf"), rsProfessor.getString("senha"));
				
				//alunos
				sql = "SELECT * FROM Aluno JOIN Turma_Aluno ON Aluno.id = Turma_Aluno.aluno_id WHERE Turma_Aluno.turma_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rsTurmas.getInt("id"));
				ResultSet rsAlunos = pstmt.executeQuery();
				
				List<Aluno> alunos = new ArrayList<>();
				while (rsAlunos.next()) {
					alunos.add(new Aluno(rsAlunos.getInt("aluno_id"), rsAlunos.getString("nome"), rsAlunos.getString("cpf"),
							rsAlunos.getString("senha"), rsAlunos.getString("curso")));
				}
				
				turmas.add(new Turma(alunos, nome, professor, sala, disciplina));
			}

			return turmas;
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return null;
	}
	
	public static List<Turma> getFiltered(String filter) {
		try (Connection con = DriverManager.getConnection(URL)) {
			//professor
			String sql = "SELECT * FROM Professor WHERE cpf = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, filter);
			ResultSet rsProfessor = pstmt.executeQuery();
			Professor professor = new Professor(rsProfessor.getInt("id"), rsProfessor.getString("nome"), rsProfessor.getString("cpf"),
					rsProfessor.getString("senha"));
			
			//turmas
			sql = "SELECT Disciplina.nome AS disciplina_nome, * FROM Turma JOIN Disciplina ON Turma.disciplina_id = Disciplina.id JOIN Sala ON Turma.sala_id = Sala.id WHERE professor_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, professor.getId());
			ResultSet rsTurmas = pstmt.executeQuery();
			
			List<Turma> turmas = new ArrayList<>();
			while (rsTurmas.next()) {
				//info geral
				String nome = rsTurmas.getString("nome");
				Sala sala = new Sala(rsTurmas.getInt("sala_id"), rsTurmas.getInt("numero"), rsTurmas.getInt("capacidade"));
				Disciplina disciplina = new Disciplina(rsTurmas.getInt("disciplina_id"), rsTurmas.getString("disciplina_nome"), rsTurmas.getString("descricao"), rsTurmas.getString("cargaHoraria"));
				
				sql = "SELECT * FROM Aluno JOIN Turma_Aluno ON Aluno.id = Turma_Aluno.aluno_id WHERE Turma_Aluno.turma_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rsTurmas.getInt("id"));
				ResultSet rsAlunos = pstmt.executeQuery();
				
				//alunos
				List<Aluno> alunos = new ArrayList<>();
				while (rsAlunos.next()) {
					alunos.add(new Aluno(rsAlunos.getInt("aluno_id"), rsAlunos.getString("nome"), rsAlunos.getString("cpf"),
							rsAlunos.getString("senha"), rsAlunos.getString("curso")));
				}
				
				turmas.add(new Turma(alunos, nome, professor, sala, disciplina));
			}

			return turmas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
