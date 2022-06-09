package co.edu.uco.app.businesslogic.facade;

import java.util.List;

import co.edu.uco.app.dto.CustomerDTO;

public interface CustomerFacade{
	
	void create (CustomerDTO dto);
	
	void update (CustomerDTO dto);
	
	void delete (int id);
	
	List<CustomerDTO> find(CustomerDTO dto);
	
}
