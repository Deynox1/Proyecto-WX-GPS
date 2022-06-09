package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.CustomerBusiness;
import co.edu.uco.app.businesslogic.facade.CustomerFacade;
import co.edu.uco.app.businesslogic.facade.impl.CustomerFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.CustomerDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class CustomerBusinessImpl implements CustomerBusiness {
	
	private DAOFactory daoFactory;
	CustomerFacade facade = new CustomerFacadeImpl();
	
	public CustomerBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(CustomerDTO dto) {
		daoFactory.getCustomerDAO().create(dto);	
		
	}

	@Override
	public void update(CustomerDTO dto) {
		daoFactory.getCustomerDAO().update(dto);	
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getCustomerDAO().delete(id);
		
	}

	@Override
	public List<CustomerDTO> find(CustomerDTO dto) {
        return daoFactory.getCustomerDAO().find(dto);
	}
	
}
		
