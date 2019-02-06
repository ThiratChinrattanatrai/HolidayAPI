package th.bluestone.holidayapi.HolidayAPI.exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception{
	
	String errorCode;
	String message;
	
	public BusinessException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
