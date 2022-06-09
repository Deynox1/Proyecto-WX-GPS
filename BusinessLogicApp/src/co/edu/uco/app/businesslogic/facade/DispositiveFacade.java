package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.DispositiveDTO;

public interface DispositiveFacade{
	
	void create (DispositiveDTO dto);
	
	void update (DispositiveDTO dto);
	
	void delete (int id);
	
	List<DispositiveDTO> find(DispositiveDTO dto);
	
}