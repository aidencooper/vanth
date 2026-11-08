package net.aidencooper.vanth.media.library;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.aidencooper.vanth.media.library.dto.LibraryRequest;
import net.aidencooper.vanth.media.library.exception.LibraryNotFoundException;

@Service 
@RequiredArgsConstructor  
public class LibraryService {
    private final LibraryMapper libraryMapper;
    private final LibraryRepository libraryRepository;

    public List<Library> getLibraries() {
        return this.libraryRepository.findAllByOrderByIdAsc();
    }

    public Library getLibrary(Long id) {
        return this.libraryRepository.findById(id)
            .orElseThrow(() -> new LibraryNotFoundException(id));
    }

    @Transactional 
    public Library createLibrary(LibraryRequest request) {
        return this.libraryRepository.save(this.libraryMapper.toEntity(request));
    }

    @Transactional 
    public Library updateLibrary(Long id, LibraryRequest request) {
        Library library = this.getLibrary(id);
        return this.libraryRepository.save(this.libraryMapper.updateLibrary(library, request));
    }

    @Transactional 
    public void deleteLibrary(Long id) {
        if(!this.libraryRepository.existsById(id)) throw new LibraryNotFoundException(id);
        
        this.libraryRepository.deleteById(id);
    }
}
