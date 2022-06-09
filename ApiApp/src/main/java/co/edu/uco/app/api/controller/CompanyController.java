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
import co.edu.uco.app.api.controller.validators.company.CreateCompanyValidator;
import co.edu.uco.app.api.controller.validators.company.DeleteCompanyValidator;
import co.edu.uco.app.api.controller.validators.company.FindCompanyValidator;
import co.edu.uco.app.api.controller.validators.company.UpdateCompanyValidator;
import co.edu.uco.app.businesslogic.facade.CompanyFacade;
import co.edu.uco.app.businesslogic.facade.impl.CompanyFacadeImpl;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.app.dto.CompanyDTO;
import co.edu.uco.crosscutting.util.object.UtilObject;

@RestController
@RequestMapping("api/wxgps/company")
public class CompanyController {
	
	@GetMapping("/dummy")
	public CompanyDTO getDummy() {
		return new CompanyDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<CompanyDTO>> create(@RequestBody CompanyDTO dto) {
		
		Validator<CompanyDTO> validator = new CreateCompanyValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<CompanyDTO> response = new Response<>();
		ResponseEntity<Response<CompanyDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CompanyFacade facade = new CompanyFacadeImpl();
				facade.create(dto);
				messages.add("Company was created succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new company information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new company information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<CompanyDTO>> update(@PathVariable("id") int id, @RequestBody CompanyDTO dto) {
		
		Validator<CompanyDTO> validator = new UpdateCompanyValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<CompanyDTO> response = new Response<>();
		ResponseEntity<Response<CompanyDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CompanyDTO updateDTO = new CompanyDTO(id, dto.getName(), dto.getLocation(), dto.getCustomer());
				CompanyFacade facade = new CompanyFacadeImpl();
				facade.update(updateDTO);
				messages.add("Company was updated succesfully!");
				statusCode = HttpStatus.OK;
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update Company information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to update the company information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<CompanyDTO>> delete(@PathVariable("id") int id) {
		
		CompanyDTO dto = new CompanyDTO(id, "", "", null);
		Validator<CompanyDTO> validator = new DeleteCompanyValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<CompanyDTO> response = new Response<>();
		ResponseEntity<Response<CompanyDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				CompanyFacade facade = new CompanyFacadeImpl();
				facade.delete(dto.getId());
				messages.add("Company was deleted successfully");
				statusCode = HttpStatus.OK;
				
			} catch (AppException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the company info. Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the company info");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<CompanyDTO>> findByid(@PathVariable("id") int id) {
		
		CompanyDTO dto = new CompanyDTO(id, "", "", null);
				
		Validator<CompanyDTO> validator = new FindCompanyValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		
		Response<CompanyDTO> response = new Response<>();
		ResponseEntity<Response<CompanyDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			try {
				CompanyFacade facade = new CompanyFacadeImpl();
				
				List<CompanyDTO> found = facade.find(dto);
				if (found.isEmpty()) {
					messages.add("Companies not found!");
				} else {
					response.setData(found);
					messages.add("Company by Id found succesfully!");
					statusCode = HttpStatus.OK;
				}
				
			} catch (AppException exception) {
				if(ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new company information. Please, try again...");
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
				messages.add("There was an unexpected problem trying to register the new company information. Please, try again...");
				exception.printStackTrace();
		    }
	  }
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}	

	
	@GetMapping
	public ResponseEntity<Response<CompanyDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<CompanyDTO> response = new Response<>();
		ResponseEntity<Response<CompanyDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		try {
			CompanyFacade facade = new CompanyFacadeImpl();
			response.setData(facade.find(new CompanyDTO()));
			messages.add("Companies were found succesfully!");
			statusCode = HttpStatus.OK;
		} catch (AppException exception) {
			if(ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the companies information. Please, try again...");
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
			messages.add("There was an unexpected problem trying to find the companies information. Please, try again...");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);
		
		return responseEntity;
	}
		
}