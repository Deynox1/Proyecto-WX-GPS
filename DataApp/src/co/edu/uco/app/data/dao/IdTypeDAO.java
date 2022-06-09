package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.IdTypeDTO;

public interface IdTypeDAO {
	
	void create(IdTypeDTO idType);
	
	void update(IdTypeDTO idType);
	
	void delete(int id);
	
	List<IdTypeDTO> find(IdTypeDTO idType);	
	
	List<IdTypeDTO> findAlreadyInUseIdTypes();
}