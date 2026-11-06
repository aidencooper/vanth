package net.aidencooper.vanth.media.library;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor 
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/{id}")
    public LibraryDTO getLibrary(@PathVariable Long id) {
        return LibraryDTO.from(this.libraryService.getLibrary(id));
    }
}
