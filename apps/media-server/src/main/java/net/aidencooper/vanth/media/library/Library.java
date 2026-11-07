package net.aidencooper.vanth.media.library;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import net.aidencooper.vanth.media.common.exception.exceptions.InvalidFolderException;

@Table("libraries")
public record Library(
    @Id Long id,
    Library.Type type,
    @MappedCollection(idColumn = "library_id") Set<Library.Folder> folders
) {
    public enum Type {
        MOVIES,
        SHOWS
    }

    @Table("library_folders")
    public record Folder(
        String path
    ) {
        public static Library.Folder of(String rawPath) {
            final Path path;

            try { path = Path.of(rawPath).normalize(); }
            catch(InvalidPathException exception) { throw new InvalidFolderException(rawPath, "is not a valid path"); }

            if(!path.isAbsolute()) throw new InvalidFolderException(rawPath, "must be absolute");
            if(!Files.isDirectory(path)) throw new InvalidFolderException(rawPath, "is not a directory");

            return new Folder(path.toString());
        }
    }
}
