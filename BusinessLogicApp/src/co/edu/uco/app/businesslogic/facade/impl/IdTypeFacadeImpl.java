package co.edu.uco.app.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.IdTypeBusiness;
import co.edu.uco.app.businesslogic.business.impl.IdTypeBusinessImpl;
import co.edu.uco.app.businesslogic.facade.IdTypeFacade;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.IdTypeDTO;

public class IdTypeFacadeImpl implements IdTypeFacade {

	@Override
	public void create(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.create(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to create the new IdType on create method in IdTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();		
		}		
	}

	@Override
	public void update(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.update(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to update the IdType on update method in IdTypeFacadeImpl";
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
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.delete(id);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to delete the IdType on delete method in IdTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
				
	   try {	
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			return idTypeBusiness.find(dto);
		} catch (AppException exception) {
			throw exception;
		} catch (Exception exception) {
			var message = "There was a problem trying to find the IdTypes on find method in IdTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();	
	}
  }

	@Override
	public List<IdTypeDTO> findAlreadyInUseIdTypes() {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			return idTypeBusiness.findAlreadyInUseIdTypes();
			
		} catch (AppException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the used IdTypes on 'findAlreadyInUseIdTypes' method of IdTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();		
		}
	}
	
}
