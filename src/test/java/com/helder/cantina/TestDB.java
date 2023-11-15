package com.helder.cantina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestDB {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			String nome = JOptionPane.showInputDialog("Qual é o nome da 7º pessoa: ");
			String id = JOptionPane.showInputDialog("Qual é o id da 7º pessoa: ");
			int idNum = Integer.parseInt(id);
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("insert into student values(" + idNum + "," + "'" + nome + "')");
			ResultSet rs = statement.executeQuery("select * from student");
			
			while (rs.next()) {
				// read the result set
				JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("name") +
						"\n id = " + rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
}