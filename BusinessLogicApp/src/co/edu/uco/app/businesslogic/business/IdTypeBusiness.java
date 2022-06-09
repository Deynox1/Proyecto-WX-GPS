package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.IdTypeDTO;

public interface IdTypeBusiness {
	
	void create (IdTypeDTO dto);
	
	void update (IdTypeDTO dto);
	
	void delete (int id);
	
	List<IdTypeDTO> findAlreadyInUseIdTypes();
	
	List<IdTypeDTO> find(IdTypeDTO dto);

}
