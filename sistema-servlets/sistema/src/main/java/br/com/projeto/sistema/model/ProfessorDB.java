package br.com.projeto.sistema.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDB {
private static final String URL = "jdbc:sqlite:C:\\Users\\leozi\\Desktop\\Faculdade\\Bases II\\Computação\\Java\\Workspace Projeto\\sistema\\src\\main\\java\\db.sqlite3";
	
	public static List<Professor> getAll() {
		try(Connection con = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM professor";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Professor> professores = new ArrayList<>();
            
			while (rs.next()) {
				professores.add(new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("senha")));
			}
			
			return professores;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
	}
	
	public static Professor getFiltered(String filter) {
		try(Connection con = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM professor WHERE cpf = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, filter);
            ResultSet rs = pstmt.executeQuery();
			
            Professor professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("senha"));
			
			return professor;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
	}
}
