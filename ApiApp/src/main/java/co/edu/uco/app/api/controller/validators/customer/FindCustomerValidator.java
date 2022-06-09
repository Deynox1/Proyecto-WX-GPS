package co.edu.uco.app.api.controller.validators.customer;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.dto.CustomerDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;

public class FindCustomerValidator implements Validator<CustomerDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(CustomerDTO dto) {
		if(!UtilNumeric.getUtilNumeric().isGreaterThan(dto.getId(), 0)) {	
			validationMessages.add("Is not possible validate the id of Id Type data");
		}
		
		dto.validateId(validationMessages);
		return validationMessages;
	}
}
