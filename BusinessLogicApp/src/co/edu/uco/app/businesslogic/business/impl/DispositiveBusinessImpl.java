package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.DispositiveBusiness;
import co.edu.uco.app.businesslogic.facade.DispositiveFacade;
import co.edu.uco.app.businesslogic.facade.impl.DispositiveFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.DispositiveDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class DispositiveBusinessImpl implements DispositiveBusiness {
	
	private DAOFactory daoFactory;
	DispositiveFacade facade = new DispositiveFacadeImpl();
	
	public DispositiveBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(DispositiveDTO dto) {
		daoFactory.getDispositiveDAO().create(dto);	
		
	}

	@Override
	public void update(DispositiveDTO dto) {
		daoFactory.getDispositiveDAO().update(dto);	
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getDispositiveDAO().delete(id);
		
	}

	@Override
	public List<DispositiveDTO> find(DispositiveDTO dto) {
        return daoFactory.getDispositiveDAO().find(dto);
	}
	
}
		
