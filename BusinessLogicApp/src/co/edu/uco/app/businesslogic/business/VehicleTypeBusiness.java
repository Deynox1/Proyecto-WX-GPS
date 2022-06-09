package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.VehicleTypeDTO;

public interface VehicleTypeBusiness {
	
	void create (VehicleTypeDTO dto);
	
	void update (VehicleTypeDTO dto);
	
	void delete (int id);
	
	List<VehicleTypeDTO> find(VehicleTypeDTO dto);
}
