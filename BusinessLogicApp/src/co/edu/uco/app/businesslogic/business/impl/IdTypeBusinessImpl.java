package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.app.businesslogic.business.IdTypeBusiness;
import co.edu.uco.app.businesslogic.facade.IdTypeFacade;
import co.edu.uco.app.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.IdTypeDTO;

public class IdTypeBusinessImpl implements IdTypeBusiness {
	
	private DAOFactory daoFactory;
	IdTypeFacade facade = new IdTypeFacadeImpl();
	
	public IdTypeBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(IdTypeDTO dto) {
		validateIfIdTypeDoesNotExistsWithSameName(dto);
		daoFactory.getIdTypeDAO().create(dto);	
	}
	
	private void validateIfIdTypeDoesNotExistsWithSameName (IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An Id Type with the same name already exists!";
			throw AppException.buildBusinessLogicException(message);
		}	
	}

	@Override
	public void update(IdTypeDTO dto) {
		validateIfIdTypeIdDoesNotExistsWithSameName(dto);
		daoFactory.getIdTypeDAO().update(dto);		
	}
	
	private void validateIfIdTypeIdDoesNotExistsWithSameName (IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "Another Id already uses that Id Type name!";
			throw AppException.buildBusinessLogicException(message);
		}	
	}
	

	@Override
	public void delete(int id) {
		validateIfIdTypeIdExistsToDelete(id);
		daoFactory.getIdTypeDAO().delete(id);
		
	}
	
	private void validateIfIdTypeIdExistsToDelete (int id) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setId(id);
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(list.isEmpty()) {
			var message = "An Id Type with that ID does not exists!";
			throw AppException.buildBusinessLogicException(message);
		}		
	}
			
	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		validateIfIdTypeIdExists(dto);
        return daoFactory.getIdTypeDAO().find(dto);
        
	}
	
	public void validateIfIdTypeIdExists (IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setId(dto.getId());
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(list.isEmpty()) {
			var message = "An Id Type with that ID does not exists!";
			throw AppException.buildBusinessLogicException(message);
		}
	}
		
	@Override
	public List<IdTypeDTO> findAlreadyInUseIdTypes() {
		return daoFactory.getIdTypeDAO().findAlreadyInUseIdTypes(); 
	}	

}
