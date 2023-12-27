package net.scanner.api.exceptional;

import net.scanner.api.modal.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> toResponse(DataNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage(),"www.toyscanner.net");
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}