package co.edu.uco.app.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.DispositiveBusiness;
import co.edu.uco.app.businesslogic.business.impl.DispositiveBusinessImpl;
import co.edu.uco.app.businesslogic.facade.DispositiveFacade;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.DispositiveDTO;

public class DispositiveFacadeImpl implements DispositiveFacade {

	@Override
	public void create(DispositiveDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			DispositiveBusiness DispositiveBusiness = new DispositiveBusinessImpl(daoFactory);
			DispositiveBusiness.create(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to create the new Dispositive on create method in DispositiveFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();		
		}		
	}

	@Override
	public void update(DispositiveDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			DispositiveBusiness DispositiveBusiness = new DispositiveBusinessImpl(daoFactory);
			DispositiveBusiness.update(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to update the Dispositive on update method in DispositiveFacadeImpl";
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
			
			DispositiveBusiness DispositiveBusiness = new DispositiveBusinessImpl(daoFactory);
			DispositiveBusiness.delete(id);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to delete the Dispositive on delete method in DispositiveFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public List<DispositiveDTO> find(DispositiveDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
				
	   try {	
		   DispositiveBusiness DispositiveBusiness = new DispositiveBusinessImpl(daoFactory);
			return DispositiveBusiness.find(dto);
		} catch (AppException exception) {
			throw exception;
		} catch (Exception exception) {
			var message = "There was a problem trying to find the Dispositives on find method in DispositiveFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();	
	}
  }
}


