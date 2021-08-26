package com.ongvida.exception.exceptionhandler;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ongvida.exception.BusinessException;
@ControllerAdvice 
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleNegocio(BusinessException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var bug = new Bugs();
		bug.setStatus(status.value()); 
		bug.setTitulo(ex.getMessage());
		bug.setDataHora(LocalDateTime.now());
		return handleExceptionInternal(ex, bug, new HttpHeaders(), status, request);
		
	}
	
@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
	var fields = new ArrayList<Bugs.Campo>();
	for (ObjectError error : ex.getBindingResult().getAllErrors()) {
		String name = ((FieldError) error).getField();
		String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
		fields.add(new Bugs.Campo(name,message));
	}
	var bug = new Bugs();
	bug.setStatus(status.value());
	bug.setTitulo("Um ou mais campos estão inválidos.Por favor faça o preenchimemnto correto e tente novamente");
	bug.setDataHora(LocalDateTime.now());
	bug.setCampos(fields);
	return super.handleExceptionInternal(ex, bug, headers, status, request);	
			}
	
	
}
		