package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.VehicleDTO;

public interface VehicleBusiness {
	
	void create (VehicleDTO dto);
	
	void update (VehicleDTO dto);
	
	void delete (int id);
	
	List<VehicleDTO> find(VehicleDTO dto);
}
