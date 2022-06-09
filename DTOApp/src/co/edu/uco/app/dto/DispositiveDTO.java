package co.edu.uco.app.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class DispositiveDTO {
	
	private int id;
	private String serialNumber;
	private String coordinates;
	
	public DispositiveDTO() {
		super();
		setSerialNumber(serialNumber);
		setCoordinates(coordinates);
	}
	
	public DispositiveDTO(int id, String serialNumber, String coordinates) {
		super();
		setId(id);
		setSerialNumber(serialNumber);
		setCoordinates(coordinates);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = UtilText.getDefault(serialNumber);
	}
	
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = UtilText.getDefault(coordinates);
	}
	
	public void validateSerialNumber(List<String> validationMessages) {
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		if(UtilText.isEmpty(getSerialNumber())) {
			validationMessages.add("Name of id type is required!!!");		
		} else if (UtilText.getDefault(getSerialNumber()).length() > 50) {
			validationMessages.add("Length of name of id type must be less or equals to 50 characters!!!");		
		} else if(!UtilText.getDefault(getSerialNumber()).matches("^[a-zA-ZÒ—·¡È…ÌÕÛ”˙⁄ ]*$")) {
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