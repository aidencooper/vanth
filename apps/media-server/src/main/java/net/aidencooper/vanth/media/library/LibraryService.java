package net.aidencooper.vanth.media.library;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.aidencooper.vanth.media.common.exception.exceptions.LibraryNotFoundException;
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
            .orElseThrow(() -> new LibraryNotFoundException(id));
    }

    @Transactional 
    public LibraryResponse createLibrary(LibraryRequest request) {
        return this.libraryMapper.toResponse(
            this.libraryRepository.save(
                this.libraryMapper.toEntity(request)
            )
        );
    }

    @Transactional
    public LibraryResponse updateLibrary(Long id, LibraryRequest request) {
        return this.libraryMapper.toResponse(
            this.libraryRepository.save(
                this.libraryMapper.updateLibrary(
                    this.libraryRepository.findById(id)
                        .orElseThrow(() -> new LibraryNotFoundException(id)), 
                    request
                )
            )
        );
    }

    @Transactional 
    public void deleteLibrary(Long id) {
        if(!this.libraryRepository.existsById(id)) throw new LibraryNotFoundException(id);
        
        this.libraryRepository.deleteById(id);
    }
}
