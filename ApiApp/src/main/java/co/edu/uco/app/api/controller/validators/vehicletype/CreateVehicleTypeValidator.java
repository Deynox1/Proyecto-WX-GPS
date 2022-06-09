package co.edu.uco.app.api.controller.validators.vehicletype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.VehicleTypeDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class CreateVehicleTypeValidator implements Validator<VehicleTypeDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(VehicleTypeDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible validate Id Type data");
		}
		
		dto.validateVehicleType(validationMessages);	
		return validationMessages;
	}
	
}
