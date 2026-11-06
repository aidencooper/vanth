package net.aidencooper.vanth.media.library;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

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
    ) {}
}
