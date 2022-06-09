package co.edu.uco.app.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class CustomerDTO {
	
	private int id;
	private String idNumber;
	private IdTypeDTO idType;
	private String name;
	private String location;
	private DispositiveDTO dispositive;
	
	public CustomerDTO() {
		super();
		setIdNumber(UtilText.EMPTY);
		setIdType(new IdTypeDTO());
		setName(UtilText.EMPTY);
		setLocation(UtilText.EMPTY);	
		setDispositive(new DispositiveDTO());
	}
	
	public CustomerDTO(int id, String idNumber, IdTypeDTO idType, String name, String location, DispositiveDTO dispositive) {
		super();
		setId(id);
		setIdNumber(idNumber);
		setIdType(idType);
		setName(name);
		setLocation(location);
		setDispositive(dispositive);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = UtilText.getDefault(idNumber);
	}
	public IdTypeDTO getIdType() {
		return idType;
	}
	public void setIdType(IdTypeDTO idType) {
		this.idType = idType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = UtilText.getDefault(name);
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = UtilText.getDefault(location);
	}
	public DispositiveDTO getDispositive() {
		return dispositive;
	}
	public void setDispositive(DispositiveDTO dispositive) {
		this.dispositive = dispositive;
	}
	
	public void validateName(List<String> validationMessages) {
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		if(UtilText.isEmpty(getName())) {
			validationMessages.add("Name of id type is required!!!");		
		} else if (UtilText.getDefault(getName()).length() > 50) {
			validationMessages.add("Length of name of id type must be less or equals to 50 characters!!!");		
		} else if(!UtilText.getDefault(getName()).matches("^[a-zA-ZÒ—·¡È…ÌÕÛ”˙⁄ ]*$")) {
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