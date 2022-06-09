package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.VehicleTypeBusiness;
import co.edu.uco.app.businesslogic.facade.VehicleTypeFacade;
import co.edu.uco.app.businesslogic.facade.impl.VehicleTypeFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.VehicleTypeDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class VehicleTypeBusinessImpl implements VehicleTypeBusiness {
	
	private DAOFactory daoFactory;
	VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
	
	public VehicleTypeBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(VehicleTypeDTO dto) {
		daoFactory.getVehicleTypeDAO().create(dto);	
		
	}

	@Override
	public void update(VehicleTypeDTO dto) {
		daoFactory.getVehicleTypeDAO().update(dto);	
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getVehicleTypeDAO().delete(id);
		
	}

	@Override
	public List<VehicleTypeDTO> find(VehicleTypeDTO dto) {
        return daoFactory.getVehicleTypeDAO().find(dto);
	}
	
}
		
