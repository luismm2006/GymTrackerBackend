package com.GymTrackerBackend.exception;

import java.util.List;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalControllerHandler {

	//400
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiError>handleElementBadRequestException(BadRequest e){
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),List.of(e.getMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

    //204 NO_CONTENT
    @ExceptionHandler(NoContent.class)
    public ResponseEntity<ApiError>handleElementNoContentException(NoContent e){
    ApiError apiError = new ApiError(HttpStatus.NO_CONTENT.value(),List.of(e.getMessage()));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);

    }
    
    //404
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiError>handleElementNotFoundException(NotFound e){
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(),List.of(e.getMessage()));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }
    
    //400 Generico
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError>handleException(BadRequestException e){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),List.of(e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }
    //409 Conflict
    @ExceptionHandler(Conflict.class)
    public ResponseEntity<ApiError>handleElementConflictException(Conflict e){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(),List.of(e.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);

    }
    
    //401 → CREDENCIALES INCORRECTAS 
    @ExceptionHandler(BadCredentialsException.class) 
    public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException e){ 
    	ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), List.of(e.getMessage())); 
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError); 
    } 
    @ExceptionHandler(UsernameNotFoundException.class) 
    public ResponseEntity<ApiError> handleUserNotFound(UsernameNotFoundException e){ 
    	ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), List.of(e.getMessage())); 
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError); 
    }
    
    //500 otros errores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(e.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

}
