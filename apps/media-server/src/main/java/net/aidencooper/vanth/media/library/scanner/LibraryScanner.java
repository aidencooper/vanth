package net.aidencooper.vanth.media.library.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.aidencooper.vanth.media.library.Library;

@Slf4j
@Service 
public class LibraryScanner {
    private final Set<Long> running = ConcurrentHashMap.newKeySet();

    @Async 
    public void scan(Library library) {
        if(!this.running.add(library.id())) {
            log.info("Library {} is already being scanned", library.id());
            return;
        }

        try {
            log.info("Scanning library {} ({})", library.id(), library.type());

            Set<Path> mediaIndex = new HashSet<>();
            for(Library.Folder folder : library.folders()) {
                try {
                    mediaIndex.addAll(this.indexMedia(Path.of(folder.path())));
                } catch(IOException exception) {
                    log.warn("Could not scan {}: {}", folder.path(), exception.toString());
                } catch(RuntimeException exception) {
                    log.error("Scan of {} failed", folder.path(), exception);
                }
            }
        } finally {
            this.running.remove(library.id());
        }
    }

    private Set<Path> indexMedia(Path root) throws IOException {
        return Files.walk(root, 1).filter(path -> !root.equals(path)).collect(Collectors.toSet());
    }
}
