package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.VehicleTypeDTO;

public interface VehicleTypeFacade{
	
	void create (VehicleTypeDTO dto);
	
	void update (VehicleTypeDTO dto);
	
	void delete (int id);
	
	List<VehicleTypeDTO> find(VehicleTypeDTO dto);
	
}