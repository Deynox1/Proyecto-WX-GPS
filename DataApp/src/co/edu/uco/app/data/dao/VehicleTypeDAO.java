package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.VehicleTypeDTO;

public interface VehicleTypeDAO {
	
	void create(VehicleTypeDTO vehicleType);
	
	void update(VehicleTypeDTO vehicleType);
	
	void delete(int id);
	
	List<VehicleTypeDTO> find(VehicleTypeDTO vehicleType);	
}
