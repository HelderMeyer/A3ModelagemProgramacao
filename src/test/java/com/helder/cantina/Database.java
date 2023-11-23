package com.helder.cantina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.swing.JOptionPane;

public class Database {
	
	public static String sqlCreate(String query) {
		Connection connection = null;
		try {
			// create a database connection ==================================
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds
			// ===============================================================
			
			//statement.executeUpdate("insert into student values(" + idNum + "," + "'" + nome + "')");
			
			statement.executeQuery(query);
			
			//ResultSet rs = statement.executeQuery(query);
			//rs.getString("" + getString + "");
			//System.out.println(rs.getString("" + getString + ""));
			//while (rs.next()) {
				// read the result set
			//	JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("name") +
			//			"\n id = " + rs.getInt("id"));
			//}
			
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
		return "Item adicionado!";
	}
	
	public static String sqlRead(String query, String getString) {
		String resultado = null;
		Connection connection = null;
		try {
			// create a database connection ==================================
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds
			// ===============================================================
			
			//statement.executeUpdate("insert into student values(" + idNum + "," + "'" + nome + "')");
			
			ResultSet rs = statement.executeQuery(query);
			if (rs.getObject(getString) instanceof String) {
			    resultado = rs.getString(getString);
			} else {
			    // Se não for uma String, tentamos obter um número
				resultado = String.valueOf(rs.getInt(getString));
			}
			//ResultSet rs = statement.executeQuery(query);
			//rs.getString("" + getString + "");
			//System.out.println(rs.getString("" + getString + ""));
			//while (rs.next()) {
				// read the result set
			//	JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("name") +
			//			"\n id = " + rs.getInt("id"));
			//}
			
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
		return resultado;
	}
	
	public static String sqlUpdate(String query) {
		Connection connection = null;
		try {
			// create a database connection ==================================
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds
			// ===============================================================
			
			//statement.executeUpdate("insert into student values(" + idNum + "," + "'" + nome + "')");
			
			statement.executeUpdate(query);
			
			//ResultSet rs = statement.executeQuery(query);
			//rs.getString("" + getString + "");
			//System.out.println(rs.getString("" + getString + ""));
			//while (rs.next()) {
				// read the result set
			//	JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("name") +
			//			"\n id = " + rs.getInt("id"));
			//}
			
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
		return "Item alterado!";
	}
	
	public static String sqlDelete(String query) {
		Connection connection = null;
		try {
			// create a database connection ==================================
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds
			// ===============================================================
			
			//statement.executeUpdate("insert into student values(" + idNum + "," + "'" + nome + "')");
			
			statement.executeQuery(query);
			
			//ResultSet rs = statement.executeQuery(query);
			//rs.getString("" + getString + "");
			//System.out.println(rs.getString("" + getString + ""));
			//while (rs.next()) {
				// read the result set
			//	JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("name") +
			//			"\n id = " + rs.getInt("id"));
			//}
			
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
		return "Item deletado!";
	}
	
}
