package co.edu.uco.app.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.app.businesslogic.business.CompanyBusiness;
import co.edu.uco.app.businesslogic.facade.CompanyFacade;
import co.edu.uco.app.businesslogic.facade.impl.CompanyFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.factory.DAOFactory;
import co.edu.uco.app.dto.CompanyDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class CompanyBusinessImpl implements CompanyBusiness {
	
	private DAOFactory daoFactory;
	CompanyFacade facade = new CompanyFacadeImpl();
	
	public CompanyBusinessImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw AppException.buildTechnicalBusinessLogicException("It's not possible to create an IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(CompanyDTO dto) {
		daoFactory.getCompanyDAO().create(dto);	
		
	}

	@Override
	public void update(CompanyDTO dto) {
		daoFactory.getCompanyDAO().update(dto);	
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getCompanyDAO().delete(id);
		
	}

	@Override
	public List<CompanyDTO> find(CompanyDTO dto) {
        return daoFactory.getCompanyDAO().find(dto);
	}
	
}
