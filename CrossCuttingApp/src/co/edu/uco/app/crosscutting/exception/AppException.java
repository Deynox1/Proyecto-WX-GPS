package co.edu.uco.app.crosscutting.exception;

import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;

import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionLocation;
import co.edu.uco.app.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.crosscutting.exception.GeneralException;

public class AppException extends GeneralException {

	private static final long serialVersionUID = -6398229720185593923L;
	private ExceptionType type;
	private ExceptionLocation location;
	
	private AppException(String userMessage, String technicalMessage, Exception rootException, ExceptionType type,
			ExceptionLocation location) {
		super(userMessage, technicalMessage, rootException);
		setType(type);
	    setLocation(location);
	}
	
	public static AppException buildUserException(String userMessage) {
		return new AppException(userMessage, userMessage, null, ExceptionType.BUSINESS, null);
		
	}
	
	public static AppException buildTechnicalException(String technicalMessage) {
		return new AppException(null, technicalMessage, null, ExceptionType.TECHNICAL, null);
	}
	
	public static AppException buildTechnicalException(String technicalMessage, Exception rootException, ExceptionLocation location) {
		return new AppException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, location);
		
	}

	public static AppException buildTechnicalException(String userMessage, String technicalMessage, Exception rootException, ExceptionType type, ExceptionLocation location) {
		return new AppException(userMessage, technicalMessage, rootException, type, location);
	}
	
	public static AppException buildTechnicalDataException(String technicalMessage) {
		return new AppException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}
	
	public static AppException buildTechnicalDataException(String technicalMessage, Exception rootException) {
		return new AppException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}
		
	public static AppException buildTechnicalBusinessLogicException(String technicalMessage) {
		return new AppException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static AppException buildBusinessLogicException(String businessMessage) {
		return new AppException(businessMessage, null, null, ExceptionType.BUSINESS, ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static AppException build(String userMessage, String technicalMessage,
			Exception rootException) {
		return new AppException(userMessage, technicalMessage, rootException, null, null);
	}
	
	public static AppException build(String userMessage, String technicalMessage) {
		return new AppException(userMessage, technicalMessage, null, null, null);
	}
	
	
	public ExceptionType getType() {
		return type;
	}
	private void setType(ExceptionType type) {
		this.type = getUtilObject().getDefault(type, ExceptionType.GENERAL);
	}
	public ExceptionLocation getLocation() {
		return location;
	}
	private void setLocation(ExceptionLocation location) {
		this.location = getUtilObject().getDefault(location,
				ExceptionLocation.GENERAL);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	

}
