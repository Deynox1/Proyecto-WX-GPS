package co.edu.uco.app.api.controller.validators.vehicle;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.VehicleDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class CreateVehicleValidator implements Validator<VehicleDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(VehicleDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible validate Id Type data");
		}
		
		dto.validateVehicle(validationMessages);	
		return validationMessages;
	}
	
}
