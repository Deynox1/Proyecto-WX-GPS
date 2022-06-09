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
import co.edu.uco.app.api.controller.validators.dispositive.CreateDispositiveValidator;
import co.edu.uco.app.api.controller.validators.dispositive.DeleteDispositiveValidator;
import co.edu.uco.app.api.controller.validators.dispositive.FindDispositiveValidator;
import co.edu.uco.app.api.controller.validators.dispositive.UpdateDispositiveValidator;
import co.edu.uco.app.businesslogic.facade.DispositiveFacade;
import co.edu.uco.app.businesslogic.facade.impl.DispositiveFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.app.dto.DispositiveDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

@RestController
@RequestMapping("api/wxgps/dispositive")
public class DispositiveController {
	
	@GetMapping("/dummy")
	public DispositiveDTO getDummy() {
		return new DispositiveDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<DispositiveDTO>> create(@RequestBody DispositiveDTO dto) {
		
		Validator<DispositiveDTO> validator = new CreateDispositiveValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<DispositiveDTO> response = new Response<>();
		ResponseEntity<Response<DispositiveDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				DispositiveFacade facade = new DispositiveFacadeImpl();
				facade.create(dto);
				messages.add("Dispositive was created succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new Dispositive information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new Dispositive information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<DispositiveDTO>> update(@PathVariable("id") int id, @RequestBody DispositiveDTO dto) {
		
		Validator<DispositiveDTO> validator = new UpdateDispositiveValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<DispositiveDTO> response = new Response<>();
		ResponseEntity<Response<DispositiveDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				DispositiveDTO updateDTO = new DispositiveDTO(id, dto.getSerialNumber(), dto.getCoordinates());
				DispositiveFacade facade = new DispositiveFacadeImpl();
				facade.update(updateDTO);
				messages.add("Dispositive was updated succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update Dispositive information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to update the Dispositive information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<DispositiveDTO>> delete(@PathVariable("id") int id) {
		
		DispositiveDTO dto = new DispositiveDTO(id, "", "");
		Validator<DispositiveDTO> validator = new DeleteDispositiveValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<DispositiveDTO> response = new Response<>();
		ResponseEntity<Response<DispositiveDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				DispositiveFacade facade = new DispositiveFacadeImpl();
				facade.delete(dto.getId());
				messages.add("Dispositive was deleted successfully");
				statusCode = HttpStatus.OK;
				
			} catch (AppException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the Dispositive Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the Dispositive");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<DispositiveDTO>> findByid(@PathVariable("id") int id) {
		
		DispositiveDTO dto = new DispositiveDTO(id, "", "");
				
		Validator<DispositiveDTO> validator = new FindDispositiveValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		
		Response<DispositiveDTO> response = new Response<>();
		ResponseEntity<Response<DispositiveDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				DispositiveFacade facade = new DispositiveFacadeImpl();
				
				List<DispositiveDTO> found = facade.find(dto);
				if (found.isEmpty()) {
					messages.add("Dispositives not found!");
				} else {
					response.setData(found);
					messages.add("Dispositive by Id found succesfully!");
					statusCode = HttpStatus.OK;
				}
				
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new Dispositive information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new Dispositive information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}	

	
	@GetMapping
	public ResponseEntity<Response<DispositiveDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<DispositiveDTO> response = new Response<>();
		ResponseEntity<Response<DispositiveDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		try {
			DispositiveFacade facade = new DispositiveFacadeImpl();
			response.setData(facade.find(new DispositiveDTO()));
			messages.add("Dispositives were found succesfully!");
			statusCode = HttpStatus.OK;
		} catch (AppException exception) {
			if(ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the Dispositives information. Please, try again...");
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
			messages.add("There was an unexpected problem trying to find the Dispositives information. Please, try again...");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
		
}