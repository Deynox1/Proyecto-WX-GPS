package co.edu.uco.app.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.VehicleTypeBusiness;
import co.edu.uco.app.businesslogic.business.impl.VehicleTypeBusinessImpl;
import co.edu.uco.app.businesslogic.facade.VehicleTypeFacade;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.VehicleTypeDTO;

public class VehicleTypeFacadeImpl implements VehicleTypeFacade {

	@Override
	public void create(VehicleTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			VehicleTypeBusiness VehicleTypeBusiness = new VehicleTypeBusinessImpl(daoFactory);
			VehicleTypeBusiness.create(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to create the new VehicleType on create method in VehicleTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();		
		}		
	}

	@Override
	public void update(VehicleTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			
			VehicleTypeBusiness VehicleTypeBusiness = new VehicleTypeBusinessImpl(daoFactory);
			VehicleTypeBusiness.update(dto);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to update the VehicleType on update method in VehicleTypeFacadeImpl";
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
			
			VehicleTypeBusiness VehicleTypeBusiness = new VehicleTypeBusinessImpl(daoFactory);
			VehicleTypeBusiness.delete(id);
			
			daoFactory.commitTransaction();
		} catch (AppException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "There was a problem trying to delete the VehicleType on delete method in VehicleTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
			
		}		
	}

	@Override
	public List<VehicleTypeDTO> find(VehicleTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
				
	   try {	
		   VehicleTypeBusiness VehicleTypeBusiness = new VehicleTypeBusinessImpl(daoFactory);
			return VehicleTypeBusiness.find(dto);
		} catch (AppException exception) {
			throw exception;
		} catch (Exception exception) {
			var message = "There was a problem trying to find the VehicleTypes on find method in VehicleTypeFacadeImpl";
			throw AppException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();	
	}
  }
}


