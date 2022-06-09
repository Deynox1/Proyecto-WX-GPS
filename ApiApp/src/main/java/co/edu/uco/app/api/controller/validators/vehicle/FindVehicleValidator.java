package co.edu.uco.app.api.controller.validators.vehicle;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.VehicleDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;

public class FindVehicleValidator implements Validator<VehicleDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(VehicleDTO dto) {
		if(!UtilNumeric.getUtilNumeric().isGreaterThan(dto.getId(), 0)) {	
			validationMessages.add("Is not possible validate the id of Id Type data");
		}
		
		dto.validateId(validationMessages);
		return validationMessages;
	}
}
