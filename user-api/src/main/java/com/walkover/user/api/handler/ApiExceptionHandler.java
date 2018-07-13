package com.walkover.user.api.handler;
import com.walkover.user.api.exception.*;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.utils.commens.MessageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.util.Set;

import static com.walkover.user.api.models.commens.ApiStatus.error;

/**
 *
 * @author aman
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    @Autowired
    private MessageUtils messageUtils;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(text.toLowerCase());
            }
        });
    }
    
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse> alreadyExist(AlreadyExistException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFound(NotFoundException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.NOT_FOUND);
    }
    
}



