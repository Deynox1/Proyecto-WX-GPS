package co.edu.uco.app.data.factory;

import java.sql.Connection;

import co.edu.uco.app.data.dao.IdTypeDAO;
import co.edu.uco.app.data.dao.CompanyDAO;
import co.edu.uco.app.data.dao.DispositiveDAO;
import co.edu.uco.app.data.dao.VehicleDAO;
import co.edu.uco.app.data.dao.VehicleTypeDAO;
import co.edu.uco.app.data.dao.CustomerDAO;
import co.edu.uco.app.data.factory.azuresql.PostgreSqlDAOFactory;

public abstract class DAOFactory {
	
	public static DAOFactory getDaoFactory() {
		     return PostgreSqlDAOFactory.create();
	}
	
	protected abstract void openConnection();
	 
	protected abstract Connection getConnection();
	
	public abstract void initTransaction();
	
	public abstract void closeConnection();
	
	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
	
	public abstract CustomerDAO getCustomerDAO();
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract CompanyDAO getCompanyDAO();
	
	public abstract DispositiveDAO getDispositiveDAO();
	
	public abstract VehicleDAO getVehicleDAO();
	
	public abstract VehicleTypeDAO getVehicleTypeDAO();
	

}
