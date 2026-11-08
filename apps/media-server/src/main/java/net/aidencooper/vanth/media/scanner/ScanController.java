package net.aidencooper.vanth.media.scanner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.aidencooper.vanth.media.library.LibraryService;

@RestController 
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor 
public class ScanController {
    private final LibraryService libraryService;
    private final LibraryScanner libraryScanner;

    @PostMapping("/scan")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void scanLibraries() {
        this.libraryService.getLibraries().forEach(this.libraryScanner::scan);
    }

    @PostMapping("/{id}/scan")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void scanLibrary(@PathVariable Long id) {
        this.libraryScanner.scan(this.libraryService.getLibrary(id));
    }
}
