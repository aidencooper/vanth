package net.aidencooper.vanth.media.library;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import net.aidencooper.vanth.media.exception.exceptions.InvalidFolderException;

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
        public static Library.Folder of(String path) {
            Path realPath;
            try {
                realPath = Path.of(path).normalize();
            } catch(InvalidPathException exception) {
                throw new InvalidFolderException(path + " is not a valid path");
            }

            if(!realPath.isAbsolute()) throw new InvalidFolderException(path + " is not an absolute path");
            if(!Files.isDirectory(realPath)) throw new InvalidFolderException(path.toString() + " is not a directory");

            return new Library.Folder(realPath.toString());
        }
    }
}
