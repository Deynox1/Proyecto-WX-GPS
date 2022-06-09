package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.IdTypeDTO;

public interface IdTypeFacade {
	
	void create (IdTypeDTO dto);
	
	void update (IdTypeDTO dto);
	
	void delete (int id);
	
	List<IdTypeDTO> find(IdTypeDTO dto);
	
	List<IdTypeDTO> findAlreadyInUseIdTypes();
}

