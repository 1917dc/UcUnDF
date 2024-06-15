package org.un.undf.model;

import java.sql.*;
import java.util.ArrayList;

public abstract class AlunoDB {
    private static final String URL = "jdbc:sqlite3:db.sqlite3";

    public static ArrayList<Aluno> fetchAll() {
        var alunoList = new ArrayList<Aluno>();
        try(Connection conn = DriverManager.getConnection(URL)){
            String sql = "SELECT * FROM Aluno";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Aluno aluno = new Aluno(rs.getString("nome"), rs.getString("cpf"), rs.getString("senha"), rs.getString("curso"));
                alunoList.add(aluno);
                System.out.println("O aluno foi adicionado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunoList;
    }
}
