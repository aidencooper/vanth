package net.aidencooper.vanth.media.scanner;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public record ScanEntry(
    Path path,
    boolean directory,
    Instant lastModified
) {
    public static ScanEntry of(Path path, BasicFileAttributes attributes) {
        return new ScanEntry(
            path, 
            attributes.isDirectory(), 
            attributes.lastModifiedTime().toInstant()
        );
    }

    public String name() {
        Path fileName = this.path().getFileName();
        return fileName == null ? "" : fileName.toString();
    }
}
