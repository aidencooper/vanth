package net.aidencooper.vanth.media.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class LibraryNotFoundException extends ErrorResponseException {
    public LibraryNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Library %d not found".formatted(id)), null);
    }
}
