package net.aidencooper.vanth.media.common.exception.exceptions;

public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(Long id) {
        super("Library %d not found".formatted(id));
    }
}
