package example.springmvc.data;

/**
 * This class defines possible error conditions which can
 * appear during the registration process. Use this class
 * instead of throwing exceptions to make the program flow
 * more predictable.
 * @author Daniel
 *
 */
public class RegistrationError {

	public enum ErrorType {USER_ALREADY_EXISTS, UNKNOWN_ERROR};
	
	public RegistrationError() {
		this.errorType = ErrorType.UNKNOWN_ERROR;
		this.errorMsg = "";
	}
	
	public RegistrationError(ErrorType errorType) {
		this.errorType = errorType;
		this.errorMsg = "";
	}
	public RegistrationError(ErrorType errorType, String errorMsg) {
		this.errorType = errorType;
		this.errorMsg = errorMsg;
	}
	
	private ErrorType errorType = ErrorType.UNKNOWN_ERROR;
	
	private String errorMsg = "";

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
