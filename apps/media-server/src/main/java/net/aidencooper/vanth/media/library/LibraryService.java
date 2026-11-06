package net.aidencooper.vanth.media.library;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import net.aidencooper.vanth.media.library.dto.LibraryRequest;
import net.aidencooper.vanth.media.library.dto.LibraryResponse;

@Service 
@RequiredArgsConstructor  
public class LibraryService {
    private final LibraryMapper libraryMapper;
    private final LibraryRepository libraryRepository;

    public LibraryResponse getLibraryById(Long id) {
        return this.libraryRepository.findById(id)
            .map(libraryMapper::toResponse)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Library not found"));
    }

    @Transactional 
    public LibraryResponse createLibrary(LibraryRequest request) {
        return this.libraryMapper.toResponse(
            this.libraryRepository.save(
                this.libraryMapper.toEntity(request)
            )
        );
    }

    public LibraryResponse updateLibrary(Long id, LibraryRequest request) {
        return this.libraryMapper.toResponse(
            this.libraryRepository.save(
                this.libraryMapper.updateLibrary(
                    this.libraryRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Library not found")), 
                    request
                )
            )
        );
    }

    @Transactional 
    public void deleteLibrary(Long id) {
        try {
            this.libraryRepository.deleteById(id);
        } catch(IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Library not found");
        }
    }
}
