package in.scalive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchEmpExistsException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchEmpExistsException(NoSuchEmpExistsException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler(value = EmpAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT );
	}
}
