package co.edu.uco.app.api.controller.validators.idtype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.IdTypeDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class UpdateIdTypeValidator implements Validator<IdTypeDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(IdTypeDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible validate Id Type data");
		}
		
		dto.validateName(validationMessages);
		
		return validationMessages;
	}
	
}