package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.CompanyDTO;

public interface CompanyBusiness {
	
	void create (CompanyDTO dto);
	
	void update (CompanyDTO dto);
	
	void delete (int id);
	
	List<CompanyDTO> find(CompanyDTO dto);
}
