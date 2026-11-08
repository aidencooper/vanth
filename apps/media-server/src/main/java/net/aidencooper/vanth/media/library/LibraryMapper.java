package net.aidencooper.vanth.media.library;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import net.aidencooper.vanth.media.library.dto.LibraryRequest;
import net.aidencooper.vanth.media.library.dto.LibraryResponse;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    @Mapping(target = "id", ignore = true)
    Library toEntity(LibraryRequest request);

    LibraryResponse toResponse(Library library);

    List<LibraryResponse> toResponses(List<Library> libraries);

    default Library updateLibrary(Library library, LibraryRequest request) {
        Set<Library.Folder> folders = request.folders().stream()
            .map(Library.Folder::parse)
            .map(folder -> library.folders().contains(folder) ? folder : folder.requireDirectory())
            .collect(Collectors.toSet());

        return new Library(
            library.id(), 
            request.type(), 
            folders
        );
    }

    default Set<Library.Folder> toFolderEntities(Set<String> paths) {
        return paths.stream()
            .map(path -> Library.Folder.parse(path).requireDirectory())
            .collect(Collectors.toSet());
    }

    default Set<String> toFolderPaths(Set<Library.Folder> folders) {
        if(folders == null) return Set.of();

        return folders.stream()
            .map(folder -> folder.path())
            .collect(Collectors.toSet());
    }
} 
