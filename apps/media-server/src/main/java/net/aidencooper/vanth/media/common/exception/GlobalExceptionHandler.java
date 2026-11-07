package net.aidencooper.vanth.media.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.aidencooper.vanth.media.common.exception.exceptions.InvalidFolderException;
import net.aidencooper.vanth.media.common.exception.exceptions.LibraryNotFoundException;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = LibraryNotFoundException.class)
    public ProblemDetail handleNotFound(LibraryNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidFolderException.class)
    public ProblemDetail handleInvalidFolder(InvalidFolderException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ProblemDetail handleException(Exception exception) {
        this.logger.error("Unhandled exception", exception);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
