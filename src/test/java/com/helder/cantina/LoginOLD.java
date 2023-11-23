package com.helder.cantina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginOLD {
	
	public void logar() {
	    System.out.println("Cantina Virtual - Tela de Login");
	    String usuarioString = JOptionPane.showInputDialog("Usu�rio: ");
	    int usuario = Integer.parseInt(usuarioString);

	    Connection connection = null;
	    try {
	        // Estabelece a conex�o com o banco de dados
	        connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
	        Statement statement = connection.createStatement();
	        statement.setQueryTimeout(30); // Define um limite de tempo para as consultas
	        
	        // Consulta se o usu�rio existe no banco de dados
	        ResultSet rs = statement.executeQuery("SELECT aluno_nome, aluno_senha FROM student WHERE aluno_RA = " + usuario);
	        
	        if (!rs.next()) {
	            // Se o usu�rio n�o existir, avise ao usu�rio
	            JOptionPane.showMessageDialog(null, "Usu�rio n�o existe!");
	        } else {
	            // Se o usu�rio existe, solicita a senha
	            String senha = JOptionPane.showInputDialog("Senha: ");

	            // Verifica se a senha est� correta
	            String senhaDoBanco = rs.getString("aluno_senha");
	            if (!senha.equals(senhaDoBanco)) {
	                // Se a senha estiver incorreta, avise ao usu�rio
	                JOptionPane.showMessageDialog(null, "Senha incorreta!");
	            } else {
	                // Se o usu�rio e a senha estiverem corretos, loga e mostra o nome do aluno
	                String nomeDoAluno = rs.getString("aluno_nome");
	                JOptionPane.showMessageDialog(null, "Bem-vindo, " + nomeDoAluno);
	            }
	        }
	    } catch (SQLException e) {
	        // Em caso de erro no banco de dados
	        System.err.println(e.getMessage());
	    } finally {
	        try {
	            if (connection != null)
	                connection.close(); // Fecha a conex�o com o banco de dados
	        } catch (SQLException e) {
	            System.err.println(e.getMessage());
	        }
	    }

	}

	
}
