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
import co.edu.uco.app.api.controller.validators.customer.CreateCustomerValidator;
import co.edu.uco.app.api.controller.validators.customer.DeleteCustomerValidator;
import co.edu.uco.app.api.controller.validators.customer.FindCustomerValidator;
import co.edu.uco.app.api.controller.validators.customer.UpdateCustomerValidator;
import co.edu.uco.app.businesslogic.facade.CustomerFacade;
import co.edu.uco.app.businesslogic.facade.impl.CustomerFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.app.dto.CustomerDTO;

@RestController
@RequestMapping("api/wxgps/customer")
public class CustomerController {
	
	@GetMapping("/dummy")
	public CustomerDTO getDummy() {
		return new CustomerDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<CustomerDTO>> create(@RequestBody CustomerDTO dto) {
		
		Validator<CustomerDTO> validator = new CreateCustomerValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<CustomerDTO> response = new Response<>();
		ResponseEntity<Response<CustomerDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CustomerFacade facade = new CustomerFacadeImpl();
				facade.create(dto);
				messages.add("Customer was created succesfully!");
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
	public ResponseEntity<Response<CustomerDTO>> update(@PathVariable("id") int id, @RequestBody CustomerDTO dto) {
		
		Validator<CustomerDTO> validator = new UpdateCustomerValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<CustomerDTO> response = new Response<>();
		ResponseEntity<Response<CustomerDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CustomerDTO updateDTO = new CustomerDTO(id, dto.getIdNumber(), dto.getIdType(), dto.getName(), dto.getLocation(), dto.getDispositive());
				CustomerFacade facade = new CustomerFacadeImpl();
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
	public ResponseEntity<Response<CustomerDTO>> delete(@PathVariable("id") int id) {
		
		CustomerDTO dto = new CustomerDTO(id, "", null, "", "", null);
		Validator<CustomerDTO> validator = new DeleteCustomerValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<CustomerDTO> response = new Response<>();
		ResponseEntity<Response<CustomerDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				CustomerFacade facade = new CustomerFacadeImpl();
				facade.delete(dto.getId());
				messages.add("Customer was deleted successfully");
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
	public ResponseEntity<Response<CustomerDTO>> findByid(@PathVariable("id") int id) {
		
		CustomerDTO dto = new CustomerDTO(id, "", null, "", "", null);
				
		Validator<CustomerDTO> validator = new FindCustomerValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		
		Response<CustomerDTO> response = new Response<>();
		ResponseEntity<Response<CustomerDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CustomerFacade facade = new CustomerFacadeImpl();
				
				List<CustomerDTO> found = facade.find(dto);
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
	public ResponseEntity<Response<CustomerDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<CustomerDTO> response = new Response<>();
		ResponseEntity<Response<CustomerDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		try {
			CustomerFacade facade = new CustomerFacadeImpl();
			response.setData(facade.find(new CustomerDTO()));
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
