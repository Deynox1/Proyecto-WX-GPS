package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.DispositiveDTO;

public interface DispositiveBusiness {
	
	void create (DispositiveDTO dto);
	
	void update (DispositiveDTO dto);
	
	void delete (int id);
	
	List<DispositiveDTO> find(DispositiveDTO dto);
}

