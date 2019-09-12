package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {
	private Connection connection = null;
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://localhost/projeto_1", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> checkLogin(User user){
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM user Where name=? AND password=?");
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		try {
			stmt.setString(1,user.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(2,user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
			User oldUser = new User();
			oldUser.setName(rs.getString("name"));
			oldUser.setPassword(rs.getString("password")); 
			users.add(oldUser);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public List<User> getLista(){
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM user");
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				User oldUser = new User();
				oldUser.setName(rs.getString("name"));
				oldUser.setPassword(rs.getString("password")); 
				users.add(oldUser);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public boolean exists(String username) {
		String sql = "SELECT name FROM user where name=?";
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
					stmt = connection.prepareStatement(sql);
					stmt.setString(1, username);
					rs = stmt.executeQuery();
					if (rs.next() == false) {
						stmt.close();
						return false;
					}else {
						stmt.close();
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	}
	public int adiciona(User user) {
		String sql = "INSERT INTO user" +
		"(name,password) values(?,?)";
		
		PreparedStatement stmt = null;
		try {
			if (!exists(user.getName())) {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getPassword());
				stmt.execute();
				stmt.close();
				return 0;
			}else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
	}

	public int createNote(Note note) {
		String sql = "INSERT INTO note" +
				"(title,label,text) values(?,?,?)";
				
				PreparedStatement stmt = null;
				try {
					stmt = connection.prepareStatement(sql);
					stmt.setString(1, note.getName());
					stmt.setString(2, note.getLabel());
					stmt.setString(3, note.getText());
					stmt.execute();
					stmt.close();
					return 0;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 1;
				}
	}
	public List<Note> getNotes(){
		List<Note> notas = new ArrayList<Note>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM note");
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				Note nota = new Note();
				nota.setText(rs.getString("text"));
				nota.setName(rs.getString("title"));
				nota.setLabel(rs.getString("label"));
				notas.add(nota);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notas;
	}
}
