package co.edu.uco.app.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.CompanyBusiness;
import co.edu.uco.app.businesslogic.business.impl.CompanyBusinessImpl;
import co.edu.uco.app.businesslogic.facade.CompanyFacade;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.CompanyDTO;

public class CompanyFacadeImpl implements CompanyFacade {

	@Override
	public void create(CompanyDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			CompanyBusiness CompanyBusiness = new CompanyBusinessImpl(daoFactory);
			CompanyBusiness.create(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to create the new company on create method in CompanyFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();		
		}		
	}

	@Override
	public void update(CompanyDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			CompanyBusiness CompanyBusiness = new CompanyBusinessImpl(daoFactory);
			CompanyBusiness.update(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to update the company on update method in CompanyFacadeImpl";
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
			
			CompanyBusiness CompanyBusiness = new CompanyBusinessImpl(daoFactory);
			CompanyBusiness.delete(id);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to delete the company on delete method in CompanyFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public List<CompanyDTO> find(CompanyDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
				
	   try {	
		   CompanyBusiness CompanyBusiness = new CompanyBusinessImpl(daoFactory);
			return CompanyBusiness.find(dto);
		} catch (AppException exception) {
			throw exception;
		} catch (Exception exception) {
			var message = "There was a problem trying to find the companies on find method in CompanyFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();	
	}
  }
}


