package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.DispositiveDTO;

public interface DispositiveDAO {
	
	void create(DispositiveDTO dispositive);
	
	void update(DispositiveDTO dispositive);
	
	void delete(int id);
	
	List<DispositiveDTO> find(DispositiveDTO dispositive);	
}