package co.edu.uco.app.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.CustomerBusiness;
import co.edu.uco.app.businesslogic.business.impl.CustomerBusinessImpl;
import co.edu.uco.app.businesslogic.facade.CustomerFacade;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.CustomerDTO;

public class CustomerFacadeImpl implements CustomerFacade {

	@Override
	public void create(CustomerDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			CustomerBusiness CustomerBusiness = new CustomerBusinessImpl(daoFactory);
			CustomerBusiness.create(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to create the new Customer on create method in CustomerFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();		
		}		
	}

	@Override
	public void update(CustomerDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			CustomerBusiness CustomerBusiness = new CustomerBusinessImpl(daoFactory);
			CustomerBusiness.update(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to update the Customer on update method in CustomerFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public void delete(int id) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			CustomerBusiness CustomerBusiness = new CustomerBusinessImpl(daoFactory);
			CustomerBusiness.delete(id);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to delete the Customer on delete method in CustomerFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public List<CustomerDTO> find(CustomerDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
				
	   try {	
		   CustomerBusiness CustomerBusiness = new CustomerBusinessImpl(daoFactory);
			return CustomerBusiness.find(dto);
		} catch (AppException exception) {
			throw exception;
		} catch (Exception exception) {
			var message = "There was a problem trying to find the Customers on find method in CustomerFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();	
	}
  }
}

