package net.aidencooper.vanth.media.common.exception.exceptions;

public class InvalidFolderException extends RuntimeException {
    public InvalidFolderException(String path, String reason) {
        super("Folder '%s' %s".formatted(path, reason));
    }
}
