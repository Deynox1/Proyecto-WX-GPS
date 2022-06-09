package co.edu.uco.app.businesslogic.business;

import java.util.List;

import co.edu.uco.app.dto.CustomerDTO;

public interface CustomerBusiness {
	
	void create (CustomerDTO dto);
	
	void update (CustomerDTO dto);
	
	void delete (int id);
	
	List<CustomerDTO> find(CustomerDTO dto);
}
