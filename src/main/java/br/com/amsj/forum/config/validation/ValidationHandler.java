package br.com.amsj.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Validation Interceptor
 * @author ailtonmsj
 */


@RestControllerAdvice
public class ValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<ValidationError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
		
		List<ValidationError> validationErrors = new ArrayList<ValidationError>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			validationErrors.add(new ValidationError(e.getField(), message));
		});
		
		return validationErrors;
	}

}
