package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.VehicleBusiness;
import co.edu.uco.app.businesslogic.facade.VehicleFacade;
import co.edu.uco.app.businesslogic.facade.impl.VehicleFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.VehicleDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class VehicleBusinessImpl implements VehicleBusiness {
	
	private DAOFactory daoFactory;
	VehicleFacade facade = new VehicleFacadeImpl();
	
	public VehicleBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(VehicleDTO dto) {
		daoFactory.getVehicleDAO().create(dto);	
		
	}

	@Override
	public void update(VehicleDTO dto) {
		daoFactory.getVehicleDAO().update(dto);	
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getVehicleDAO().delete(id);
		
	}

	@Override
	public List<VehicleDTO> find(VehicleDTO dto) {
        return daoFactory.getVehicleDAO().find(dto);
	}
	
}
		
