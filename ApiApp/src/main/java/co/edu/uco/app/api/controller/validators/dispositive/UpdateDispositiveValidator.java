package co.edu.uco.app.api.controller.validators.dispositive;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.DispositiveDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class UpdateDispositiveValidator implements Validator<DispositiveDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(DispositiveDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible validate Id Type data");
		}
		
		dto.validateSerialNumber(validationMessages);
		
		return validationMessages;
	}
	
}
