package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.VehicleDTO;

public interface VehicleFacade{
	
	void create (VehicleDTO dto);
	
	void update (VehicleDTO dto);
	
	void delete (int id);
	
	List<VehicleDTO> find(VehicleDTO dto);
	
}
