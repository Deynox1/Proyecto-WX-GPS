package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.CompanyDTO;

public interface CompanyFacade{
	
	void create (CompanyDTO dto);
	
	void update (CompanyDTO dto);
	
	void delete (int id);
	
	List<CompanyDTO> find(CompanyDTO dto);
	
}
