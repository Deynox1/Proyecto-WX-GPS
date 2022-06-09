package co.edu.uco.app.api.controller.validators;

import java.util.List;

public interface Validator<D> {
	
	List<String> validate(D dto);
	
}
