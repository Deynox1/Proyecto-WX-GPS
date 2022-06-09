package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.CompanyDTO;

public interface CompanyDAO {
	
	void create(CompanyDTO company);
	
	void update(CompanyDTO company);
	
	void delete(int id);
	
	List<CompanyDTO> find(CompanyDTO company);	
}
