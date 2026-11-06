package net.aidencooper.vanth.media.library;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor  
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public Library getLibrary(Long id) {
        return this.libraryRepository.findById(id).get();
    }
}
