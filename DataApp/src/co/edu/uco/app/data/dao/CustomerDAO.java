package co.edu.uco.app.data.dao;

import java.util.List;

import co.edu.uco.app.dto.CustomerDTO;

public interface CustomerDAO {
	
	void create(CustomerDTO customer);
	
	void update(CustomerDTO customer);
	
	void delete(int id);
	
	List<CustomerDTO> find(CustomerDTO customer);	
}
