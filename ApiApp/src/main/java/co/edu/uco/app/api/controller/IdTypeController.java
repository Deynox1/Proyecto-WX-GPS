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

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.app.api.controller.response.Response;
import co.edu.uco.app.api.controller.validators.Validator;
import co.edu.uco.app.api.controller.validators.idtype.CreateIdTypeValidator;
import co.edu.uco.app.api.controller.validators.idtype.DeleteIdTypeValidator;
import co.edu.uco.app.api.controller.validators.idtype.FindIdTypeValidator;
import co.edu.uco.app.api.controller.validators.idtype.UpdateIdTypeValidator;
import co.edu.uco.app.businesslogic.facade.IdTypeFacade;
import co.edu.uco.app.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.app.dto.IdTypeDTO;

@RestController
@RequestMapping("api/wxgps/idtype")
public class IdTypeController {
	
	@GetMapping("/dummy")
	public IdTypeDTO getDummy() {
		return new IdTypeDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<IdTypeDTO>> create(@RequestBody IdTypeDTO dto) {
		
		Validator<IdTypeDTO> validator = new CreateIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.create(dto);
				messages.add("Id type was created succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new id Type information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new id Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> update(@PathVariable("id") int id, @RequestBody IdTypeDTO dto) {
		
		Validator<IdTypeDTO> validator = new UpdateIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				IdTypeDTO updateDTO = new IdTypeDTO(id, dto.getName());
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.update(updateDTO);
				messages.add("Id type was updated succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update Id Type information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to update the id Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> delete(@PathVariable("id") int id) {
		
		IdTypeDTO dto = new IdTypeDTO(id, "");
		Validator<IdTypeDTO> validator = new DeleteIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.delete(dto.getId());
				messages.add("IdType was deleted successfully");
				statusCode = HttpStatus.OK;
				
			} catch (AppException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the id type Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> findByid(@PathVariable("id") int id) {
		
		IdTypeDTO dto = new IdTypeDTO(id, "");
				
		Validator<IdTypeDTO> validator = new FindIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				
				List<IdTypeDTO> found = facade.find(dto);
				if (found.isEmpty()) {
					messages.add("Id Types not found!");
				} else {
					response.setData(found);
					messages.add("Id Type by Id found succesfully!");
					statusCode = HttpStatus.OK;
				}
				
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new id Type information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new id Type information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}	

	
	@GetMapping
	public ResponseEntity<Response<IdTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		try {
			IdTypeFacade facade = new IdTypeFacadeImpl();
			response.setData(facade.find(new IdTypeDTO()));
			messages.add("Id types were found succesfully!");
			statusCode = HttpStatus.OK;
		} catch (AppException exception) {
			if(ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the id Types information. Please, try again...");
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
			messages.add("There was an unexpected problem trying to find the id Types information. Please, try again...");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
		
}
