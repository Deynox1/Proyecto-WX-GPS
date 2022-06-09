package co.edu.uco.app.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class CompanyDTO {
	
	private int id;
	private String name;
	private String location;
	private CustomerDTO customer;
	
	public CompanyDTO() {
		super();
		setName(UtilText.EMPTY);
		setLocation(UtilText.EMPTY);	
		setCustomer(new CustomerDTO());
	}
	
	public CompanyDTO(int id, String name, String location, CustomerDTO customer) {
		super();
		setId(id);
		setName(name);
		setLocation(location);
		setCustomer(customer);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
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
