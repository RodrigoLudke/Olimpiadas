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
	private ArrayList<Parametro> parametros;

	public MySQL() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String host = "jdbc:mysql://localhost/olimpiadas";
		String user = "root";
		String password = "";

		this.conn = null;
		this.parametros = new ArrayList<Parametro>();

		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(host, user, password);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Nao foi possivel encontrar o driver JDBC");
		} catch (SQLException se) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados");
		}
	}

	public void addParametro(String tipo, Object valor) {
		this.parametros.add(new Parametro(tipo, valor));
	}

	/*
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
	*/
	
	public ResultSet statement(String query) throws SQLException {
		PreparedStatement pstmt = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		if (!this.parametros.isEmpty()) {
            for (int i = 0; i < parametros.size(); i++) {
                Parametro param = parametros.get(i);

                switch (param.getTipo().toLowerCase()) {
                    case "int":
                        pstmt.setInt(i + 1, (Integer) param.getValor());
                        break;
                    case "string":
                        pstmt.setString(i + 1, (String) param.getValor());
                        break;
                    case "double":
                        pstmt.setDouble(i + 1, (Double) param.getValor());
                        break;
                    case "boolean":
                        pstmt.setBoolean(i + 1, (Boolean) param.getValor());
                        break;
                    default:
                        throw new SQLException("Tipo de parâmetro não suportado: " + param.getTipo());
                }
            }
        }
		
		if (query.trim().toLowerCase().startsWith("select")) {
			return pstmt.executeQuery();  // Executa SELECT
        } else {
            pstmt.executeUpdate(); // Executa INSERT, UPDATE ou DELETE
            return pstmt.getGeneratedKeys();
        }
	}
}
