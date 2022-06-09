package co.edu.uco.app.api.controller.validators.company;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.CompanyDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class CreateCompanyValidator implements Validator<CompanyDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(CompanyDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible validate Id Type data");
		}
		
		dto.validateName(validationMessages);	
		return validationMessages;
	}
	
}
