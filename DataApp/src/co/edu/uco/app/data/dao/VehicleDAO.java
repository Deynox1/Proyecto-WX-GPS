package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.VehicleDTO;

public interface VehicleDAO {
	
	void create(VehicleDTO vehicle);
	
	void update(VehicleDTO vehicle);
	
	void delete(int id);
	
	List<VehicleDTO> find(VehicleDTO vehicle);
}
