package br.com.projeto.sistema.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AlunosDB {
	private static final String URL = "jdbc:sqlite:C:\\Users\\leozi\\Desktop\\Faculdade\\Bases II\\Computação\\Java\\Workspace Projeto\\sistema\\src\\main\\java\\db.sqlite3";
	
	public static List<Aluno> getAll() {
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
	
	public static List<Aluno> getFiltered(String cpf) {
		try(Connection con = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM aluno WHERE cpf = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
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
