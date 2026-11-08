package net.aidencooper.vanth.media.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class InvalidFolderException extends ErrorResponseException {
    public InvalidFolderException(String path, String reason) {
        super(HttpStatus.BAD_REQUEST, ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Folder '%s' %s".formatted(path, reason)), null);
    }
}
