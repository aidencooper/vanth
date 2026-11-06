package net.aidencooper.vanth.media.library;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import net.aidencooper.vanth.media.library.dto.LibraryRequest;
import net.aidencooper.vanth.media.library.dto.LibraryResponse;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "folders", source = "folders")
    Library toEntity(LibraryRequest request);

    LibraryResponse toResponse(Library library);

    default Library updateLibrary(Library library, LibraryRequest request) {
        if(request == null) return library;

        return new Library(
            library.id(), 
            request.type() != null ? request.type() : library.type(), 
            request.folders() != null ? this.toFolderEntities(request.folders()) : library.folders()
        );
    }

    default Set<Library.Folder> toFolderEntities(Set<String> paths) {
        if(paths == null) return null;

        return paths.stream()
            .map(Library.Folder::new)
            .collect(Collectors.toSet());
    }

    default Set<String> toFolderPaths(Set<Library.Folder> folders) {
        if(folders == null) return null;

        return folders.stream()
            .map(folder -> folder.path())
            .collect(Collectors.toSet());
    }
} 
