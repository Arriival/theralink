package com.theralink.common.exception;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(new ErrorResponse(500, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ApplicationException.class)
	public ResponseEntity<?> applicationException(ApplicationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = UnAuthorizeException.class)
	public ResponseEntity<?> unAuthorizeException(UnAuthorizeException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exception(ApplicationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.valueOf(exception.getErrorCode()));
	}

	@ExceptionHandler(value = JpaSystemException.class)
	public ResponseEntity<ApplicationException> JpaSystemException(JpaSystemException exception) {
		return new ResponseEntity<ApplicationException>(new ApplicationException("خطای سیستمی", 500),  HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NoResultException.class)
	public ResponseEntity<?> noResultException(NoResultException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.valueOf(401));
	}

	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public ResponseEntity<?> emptyResultDataAccessException(EmptyResultDataAccessException exception) {
		return new ResponseEntity<>("", HttpStatus.valueOf(401));
	}

	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(SQLIntegrityConstraintViolationException exception) {
		return new ResponseEntity<>("رکورد تکراری", HttpStatus.valueOf(500));
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception) {
		return new ResponseEntity<>("رکورد تکراری", HttpStatus.valueOf(500));
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException exception) {
		return new ResponseEntity<>(new ErrorResponse(500, "Data Integrity Violation Exception"), HttpStatus.valueOf(500));
	}
}
