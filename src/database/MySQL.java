package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL {
	public Connection conn;

	public MySQL() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String host = "jdbc:mysql://localhost/olimpiadas";
		String user = "root";
		String password = "";

		this.conn = null;

		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(host, user, password);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Nao foi possivel encontrar o driver JDBC");
		} catch (SQLException se) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados");
		}
	}

	protected void fechaResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void fechaStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void fechaPreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet statement(String query, ArrayList<String> a) throws SQLException {
		PreparedStatement pstmt = this.conn.prepareStatement(query);

	    if (a != null) {
	        for (int i = 0; i < a.size(); i++) {
	            pstmt.setString(i + 1, a.get(i));
	        }
	    }

	    return pstmt.executeQuery();
	}
}
