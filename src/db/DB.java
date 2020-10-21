package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static void closeAll(Statement sT, ResultSet rS) {
		if (sT != null) {
			closeStatement(sT);
		}
		if (rS != null ) {
		    closeResultSet(rS);
		}
	}
	
	public static void closeAll(Statement sT, ResultSet rS, boolean CloseConnection) {
		if (sT != null) {
			closeStatement(sT);
		}
		if (rS != null ) {
		    closeResultSet(rS);
		}
		if (CloseConnection) {
			closeConnection();
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties info = loadProperties();
				String url = info.getProperty("dburl");
				conn = DriverManager.getConnection(url, info);
			}catch (SQLException msg) {
				throw new DbException(msg.getMessage());
			}
		}
		
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}catch (SQLException msg) {
				throw new DbException(msg.getMessage());
			}
		}
		
	}

	public static void closeStatement(Statement sT) {
		if ( sT != null ) {
			try {
				sT.close();
			} catch (SQLException msg) {
				throw new DbException(msg.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rS) {
		if ( rS != null ) {
			try {
				rS.close();
			} catch (SQLException msg) {
				throw new DbException(msg.getMessage());
			}
		}
	}
	private static Properties loadProperties() {
		try (FileInputStream fS = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fS);
			return props;
		} catch (IOException msg) {
			throw new DbException(msg.getMessage());
		}

	}
	
	

}
