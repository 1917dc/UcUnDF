package org.un.undf.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AlunoDB {
	private static final String URL = "jdbc:sqlite:resources/db.sqlite3";
	
	public static List<Aluno> fetchAllAlunos() {
		try(Connection con = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM aluno";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Aluno> alunos = new ArrayList<>();
            
			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("senha"), rs.getString("curso")));
			}
			
			return alunos;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
	}

	public static Aluno fetchAluno(String cpf){
		try(Connection conn = DriverManager.getConnection(URL)){
			PreparedStatement pstmt = conn.prepareStatement("SELECT nome, curso FROM aluno WHERE cpf = ?");
			pstmt.setString(1, cpf);
			ResultSet rs = pstmt.executeQuery();
			var aluno = new Aluno(rs.getString("nome"), rs.getString("curso"), cpf);

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return null;
    }

	public static void add(Aluno aluno) {
		String sql = "INSERT INTO Aluno (nome, cpf, senha, curso) VALUES (?, ?, ?, ?)";
		
		try (Connection con = DriverManager.getConnection(URL)) {
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, aluno.getNome());
				stmt.setString(2, aluno.getCpf());
				stmt.setString(3, aluno.getSenha());
				stmt.setString(4, aluno.getCurso());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
