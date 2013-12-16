package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Account;
import model.Document;

/**
 * 
 * @author bryant zhang
 * 
 */
public class DataBaseDao {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/";
	private String username = "";
	private String password = "";

	private Connection dbConnection;

	public DataBaseDao() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void connectDB() {
		try {
			if (this.dbConnection == null) {
				this.dbConnection = DriverManager.getConnection(url, username,
						password);
			} else if (this.dbConnection.isClosed()) {
				this.dbConnection = DriverManager.getConnection(url, username,
						password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			if (this.dbConnection != null) {
				if (!this.dbConnection.isClosed()) {
					this.dbConnection.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getIdByUsername(String username) {
		String sql = "select * from user where username = '" + username + "';";
		int userId = 0;

		try {
			this.connectDB();
			if (!this.dbConnection.isClosed()) {
				Statement st = dbConnection.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					userId = rs.getInt("id");
				}
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return userId;
	}

	public String getPasswordByUsername(String username) {
		String sql = "select * from user where username = '" + username + "';";
		String password = null;

		try {
			this.connectDB();
			if (!this.dbConnection.isClosed()) {
				Statement st = dbConnection.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					password = rs.getString("password");
				}
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return password;
	}

	public void addUser(Account user) {
		String sql = "insert into user(id,username,password,name,authority) values(?,?,?,?,?)";

		try {
			this.connectDB();
			if (this.dbConnection.isClosed()) {
				PreparedStatement st = this.dbConnection.prepareStatement(sql);
				st.setInt(1, user.getId());
				st.setString(2, user.getUsername());
				st.setString(3, user.getPassword());
				st.setString(4, user.getName());
				st.setInt(5, user.getAuthority());

				st.executeUpdate();
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteUser(Account user) {

	}

	public void updateUser(Account user) {

	}

	public int getIdByDocTypeName(String type) {
		String sql = "select * from DocumentType where name = '" + type + "';";
		int docTypeId = 0;

		try {
			this.connectDB();
			if (!this.dbConnection.isClosed()) {
				Statement st = dbConnection.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					docTypeId = rs.getInt("id");
				}
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return docTypeId;
	}

	public Document getDocumentByTitle(String title) {
		return null;
	}

	public ArrayList<Document> getDocumentsByUser(Account user) {
		return null;
	}

	public ArrayList<Document> getAllDocuments() {
		return null;
	}

	public void addDocument(Document doc) {
		String sql = "insert into document(id,title,author,year,pages,abstract,keywords,url,createTime,docTypeId,userId) values(?,?,?,?,?,?,?,?,?,?,?)";
		int userId = this.getIdByUsername(doc.getUsername());
		int docTypeId = this.getIdByDocTypeName(doc.getDocType().toString());

		try {
			this.connectDB();
			if (this.dbConnection.isClosed()) {
				PreparedStatement st = this.dbConnection.prepareStatement(sql);
				st.setInt(1, doc.getId());
				st.setString(2, doc.getTitle());
				st.setString(3, doc.getAuthor());
				st.setString(4, doc.getYear());
				st.setInt(5, doc.getPages());
				st.setString(6, doc.getAbstracts());
				st.setString(7, doc.getKeywords());
				st.setString(8, doc.getUrl());
				st.setString(9, doc.getCreateTime());
				st.setInt(10, docTypeId);
				st.setInt(11, userId);

				st.executeUpdate();
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateDocument(Document doc) {

	}

	public void deleteDocument(Document doc) {

	}

}
