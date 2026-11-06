package net.aidencooper.vanth.media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.aidencooper.vanth.media.exception.exceptions.InvalidFolderException;

@ControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidFolderException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody 
    public ErrorResponse handleException(InvalidFolderException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
