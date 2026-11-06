package net.aidencooper.vanth.media.library;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.aidencooper.vanth.media.library.dto.LibraryRequest;
import net.aidencooper.vanth.media.library.dto.LibraryResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor 
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/{id}")
    public LibraryResponse getLibrary(@PathVariable Long id) {
        return this.libraryService.getLibraryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryResponse createLibrary(@RequestBody LibraryRequest request) {
        return this.libraryService.createLibrary(request);
    }

    @PutMapping("/{id}")
    public LibraryResponse updateLibrary(@PathVariable Long id, @RequestBody LibraryRequest request) {
        return this.libraryService.updateLibrary(id, request);
    } 

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibrary(@PathVariable Long id) {
        this.libraryService.deleteLibrary(id);
    }
}
