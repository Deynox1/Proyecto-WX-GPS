package co.edu.uco.app.data.dao.connection;

import java.sql.Connection;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.crosscutting.util.sql.UtilConnection;

public class ConnectionSQL {
	
	private Connection connection;
	
	protected ConnectionSQL(Connection connection) {
		if (UtilConnection.isClosed(connection)) {
			throw AppException.buildTechnicalDataException("It's not possible to create a specific DAO because connection is closed");					
		}
		
		setConnection(connection);
	}

	protected Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
