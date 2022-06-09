package co.edu.uco.app.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class VehicleTypeDTO {
	
	private int id;
	private String type;
	
	public VehicleTypeDTO() {
		super();
		setType(UtilText.EMPTY);	
	}
	
	public VehicleTypeDTO(int id, String type) {
		super();
		setId(id);
		setType(type);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = UtilText.getDefault(type);
	}
	
	public void validateVehicleType(List<String> validationMessages) {
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		if(UtilText.isEmpty(getType())) {
			validationMessages.add("Name of id type is required!!!");		
		} else if (UtilText.getDefault(getType()).length() > 50) {
			validationMessages.add("Length of name of id type must be less or equals to 50 characters!!!");		
		} else if(!UtilText.getDefault(getType()).matches("^[a-zA-ZÒ—·¡È…ÌÕÛ”˙⁄ ]*$")) {
			validationMessages.add("Name of id type contains invalid characters!!!");
		}
	}
	
	public void validateId(List<String> validationMessages) {
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		if(!UtilNumeric.getUtilNumeric().isGreaterThan(getId(), 0)) {
			
			validationMessages.add("The ID must be greater than zero");
		}
		
	}

}
