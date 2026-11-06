package net.aidencooper.vanth.media.library;

import java.util.Set;
import java.util.stream.Collectors;

public record LibraryDTO(
    Library.Type type,
    Set<String> folders
) {
    public static LibraryDTO from(Library library) {
        return new LibraryDTO(
            library.type(), 
            library.folders().stream()
                .map(folder -> folder.path())
                .collect(Collectors.toUnmodifiableSet())
        );
    }
}
