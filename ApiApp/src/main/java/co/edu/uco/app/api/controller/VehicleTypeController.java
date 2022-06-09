package co.edu.uco.app.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.app.api.controller.response.Response;
import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.api.controller.validators.vehicletype.CreateVehicleTypeValidator;
import co.edu.uco.app.api.controller.validators.vehicletype.DeleteVehicleTypeValidator;
import co.edu.uco.app.api.controller.validators.vehicletype.FindVehicleTypeValidator;
import co.edu.uco.app.api.controller.validators.vehicletype.UpdateVehicleTypeValidator;
import co.edu.uco.app.businesslogic.facade.VehicleTypeFacade;
import co.edu.uco.app.businesslogic.facade.impl.VehicleTypeFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.app.dto.VehicleTypeDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

@RestController
@RequestMapping("api/wxgps/vehicletype")
public class VehicleTypeController {
	
	@GetMapping("/dummy")
	public VehicleTypeDTO getDummy() {
		return new VehicleTypeDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<VehicleTypeDTO>> create(@RequestBody VehicleTypeDTO dto) {
		
		Validator<VehicleTypeDTO> validator = new CreateVehicleTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<VehicleTypeDTO> response = new Response<>();
		ResponseEntity<Response<VehicleTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
				facade.create(dto);
				messages.add("Vehicle Type was created succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new Vehicle Type information. Please, try again...");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				} 
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to register the new Vehicle Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<VehicleTypeDTO>> update(@PathVariable("id") int id, @RequestBody VehicleTypeDTO dto) {
		
		Validator<VehicleTypeDTO> validator = new UpdateVehicleTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<VehicleTypeDTO> response = new Response<>();
		ResponseEntity<Response<VehicleTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				VehicleTypeDTO updateDTO = new VehicleTypeDTO(id, dto.getType());
				VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
				facade.update(updateDTO);
				messages.add("Vehicle Type was updated succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update Vehicle Type information. Please, try again...");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				} 
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to update the Vehicle Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<VehicleTypeDTO>> delete(@PathVariable("id") int id) {
		
		VehicleTypeDTO dto = new VehicleTypeDTO(id, "");
		Validator<VehicleTypeDTO> validator = new DeleteVehicleTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<VehicleTypeDTO> response = new Response<>();
		ResponseEntity<Response<VehicleTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
				facade.delete(dto.getId());
				messages.add("VehicleType was deleted successfully");
				statusCode = HttpStatus.OK;
				
			} catch (AppException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the Vehicle Type Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to delete the Vehicle Type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<VehicleTypeDTO>> findByid(@PathVariable("id") int id) {
		
		VehicleTypeDTO dto = new VehicleTypeDTO(id, "");
				
		Validator<VehicleTypeDTO> validator = new FindVehicleTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		
		Response<VehicleTypeDTO> response = new Response<>();
		ResponseEntity<Response<VehicleTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
				
				List<VehicleTypeDTO> found = facade.find(dto);
				if (found.isEmpty()) {
					messages.add("Vehicle Types not found!");
				} else {
					response.setData(found);
					messages.add("Vehicle Type by Id found succesfully!");
					statusCode = HttpStatus.OK;
				}
				
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new Vehicle Type information. Please, try again...");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				} 
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to register the new Vehicle Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}	

	
	@GetMapping
	public ResponseEntity<Response<VehicleTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<VehicleTypeDTO> response = new Response<>();
		ResponseEntity<Response<VehicleTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		try {
			VehicleTypeFacade facade = new VehicleTypeFacadeImpl();
			response.setData(facade.find(new VehicleTypeDTO()));
			messages.add("Vehicle Types were found succesfully!");
			statusCode = HttpStatus.OK;
		} catch (AppException exception) {
			if(ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the Vehicle Types information. Please, try again...");
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getTechnicalMessage());
				exception.getRootException().printStackTrace();
			} else {
				messages.add(exception.getUserMessage());
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getUserMessage());
				exception.getRootException().printStackTrace();
			} 
		} catch (Exception exception) {
			messages.add("There was an unexpected problem trying to find the Vehicle Types information. Please, try again...");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
		
}
