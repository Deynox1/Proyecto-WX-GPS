package co.edu.uco.app.data.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.IdTypeDAO;
import co.edu.uco.app.data.dao.CompanyDAO;
import co.edu.uco.app.data.dao.DispositiveDAO;
import co.edu.uco.app.data.dao.VehicleDAO;
import co.edu.uco.app.data.dao.VehicleTypeDAO;
import co.edu.uco.app.data.dao.CustomerDAO;
import co.edu.uco.app.data.dao.azuresql.IdTypePostgreSqlDAO;
import co.edu.uco.app.data.dao.azuresql.CompanyPostgreSqlDAO;
import co.edu.uco.app.data.dao.azuresql.DispositivePostgreSqlDAO;
import co.edu.uco.app.data.dao.azuresql.CustomerPostgreSqlDAO;
import co.edu.uco.app.data.dao.azuresql.VehiclePostgreSqlDAO;
import co.edu.uco.app.data.dao.azuresql.VehicleTypePostgreSqlDAO;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.crosscutting.util.sql.UtilConnection;

public class PostgreSqlDAOFactory extends DAOFactory {
	
	private Connection connection;
	
	private PostgreSqlDAOFactory () {
		openConnection();
	}
	
	public static DAOFactory create() {
		return new PostgreSqlDAOFactory();
	}
	
	@Override
	protected void openConnection() {
		
		String url = "jdbc:postgresql://localhost/wxgps";
		String user = "postgres";
		String password = "samay";
		
	try {
		connection = DriverManager.getConnection(url, user, password);
	} catch (SQLException exception) {
		throw AppException.buildTechnicalException("There was a problem trying to get the connection with sql server at jdbc:postgresql://localhost/wxgps");
	} catch (Exception exception) {
		throw AppException.buildTechnicalException("An unexpected problem has ocurred trying to get the connection with sql server at jdbc:postgresql://localhost/wxgps");
	}
  }
		
	@Override
	protected Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnection() {
		 if (!UtilConnection.isOpen(connection)) {
			throw AppException.buildTechnicalException("It's not possible to close a connection because it's already closed", null, null);
	    }
		 
		try {
			getConnection().close();
		} catch (SQLException exception) {
			throw AppException.buildTechnicalException("There was a problem trying to close the connection with sql server at jdbc:postgresql://localhost/wxgps");
		} catch (Exception exception) {
			throw AppException.buildTechnicalException("An unexpected problem has ocurred trying to close the connection with sql server at jdbc:postgresql://localhost/wxgps");
		}
	}
	
	public void initTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw AppException.buildTechnicalException("It's not possible to commit a transaction because connection is closed", null, null);
		}
		try {
			if(!getConnection().getAutoCommit()) {
				throw AppException.buildTechnicalException("It's not possible to init the transaction because it was already initiated", null, null);
			}
			getConnection().setAutoCommit(false);
		} catch (SQLException exception) {
			throw AppException.buildTechnicalException("There was a problem trying to init the connection with sql server at jdbc:postgresql://localhost/wxgps");
		} catch (Exception exception) {
			throw AppException.buildTechnicalException("An unexpected problem has ocurred trying to init the connection with sql server at jdbc:postgresql://localhost/wxgps");
		}
	}
  
	@Override
	public void commitTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw AppException.buildTechnicalException("It's not possible to commit a transaction because connection is closed", null, null);
		} 
		try {
			if(getConnection().getAutoCommit()) {
				throw AppException.buildTechnicalException("It's not possible to commit a transaction because the database is managing the transaction", null, null);
			}
			getConnection().commit();
		} catch (SQLException exception) {
			throw AppException.buildTechnicalException("There was a problem trying to commit the connection with sql server at jdbc:postgresql://localhost/wxgps");
		} catch (Exception exception) {
			throw AppException.buildTechnicalException("An unexpected problem has ocurred trying to commit the connection with sql server at jdbc:postgresql://localhost/wxgps");
		}
	}

	@Override
	public void rollbackTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw AppException.buildTechnicalException("It's not possible to rollback the transaction because the connection is closed", null, null);
		}
		try {
			if(getConnection().getAutoCommit()) {
				throw AppException.buildTechnicalException("It's not possible to rollback a transaction because the database is managing the transaction", null, null);
			}
			getConnection().rollback();
		} catch (SQLException exception) {
			throw AppException.buildTechnicalException("There was a problem trying to rollback the connection with sql server at jdbc:postgresql://localhost/wxgps");
		} catch (Exception exception) {
			throw AppException.buildTechnicalException("An unexpected problem has ocurred trying to rollback the connection with sql server at jdbc:postgresql://localhost/wxgps");
		}
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return CustomerPostgreSqlDAO.build(getConnection());
	}

	@Override
	public IdTypeDAO getIdTypeDAO() {
		return IdTypePostgreSqlDAO.build(getConnection());
	}
	
	@Override
	public CompanyDAO getCompanyDAO() {
		return CompanyPostgreSqlDAO.build(getConnection());
	}

	@Override
	public DispositiveDAO getDispositiveDAO() {
		return DispositivePostgreSqlDAO.build(getConnection());
	}

	@Override
	public VehicleDAO getVehicleDAO() {
		return VehiclePostgreSqlDAO.build(getConnection());
	}

	@Override
	public VehicleTypeDAO getVehicleTypeDAO() {
		return VehicleTypePostgreSqlDAO.build(getConnection());
	}

}
