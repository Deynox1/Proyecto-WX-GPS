package co.edu.uco.app.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class VehicleDTO {
	
	private int id;
	private String vehicle;
	private VehicleTypeDTO vehicletype;
	private DispositiveDTO dispositive;
	
	public VehicleDTO() {
		super();
		setVehicle(UtilText.EMPTY);
		setVehicleType(new VehicleTypeDTO());
		setDispositive(new DispositiveDTO());

	}
	
	public VehicleDTO(int id, String vehicle, VehicleTypeDTO vehicletype, DispositiveDTO dispositive) {
		super();
		setId(id);
		setVehicle(vehicle);
		setVehicleType(vehicletype);
		setDispositive(dispositive);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	public VehicleTypeDTO getVehicleType() {
		return vehicletype;
	}

	public void setVehicleType(VehicleTypeDTO vehicletype) {
		this.vehicletype = vehicletype;
	}
	
	public DispositiveDTO getDispositive() {
		return dispositive;
	}

	public void setDispositive(DispositiveDTO dispositive) {
		this.dispositive = dispositive;
	}
	
	public void validateVehicle(List<String> validationMessages) {
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		if(UtilText.isEmpty(getVehicle())) {
			validationMessages.add("Name of id type is required!!!");		
		} else if (UtilText.getDefault(getVehicle()).length() > 50) {
			validationMessages.add("Length of name of id type must be less or equals to 50 characters!!!");		
		} else if(!UtilText.getDefault(getVehicle()).matches("^[a-zA-ZÒ—·¡È…ÌÕÛ”˙⁄ ]*$")) {
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
