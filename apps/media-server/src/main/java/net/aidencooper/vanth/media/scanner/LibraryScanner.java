package net.aidencooper.vanth.media.scanner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aidencooper.vanth.media.library.Library;

@Slf4j
@Service 
@RequiredArgsConstructor 
public class LibraryScanner {
    private final MediaWalker mediaWalker;

    private final Set<Long> running = ConcurrentHashMap.newKeySet();

    @Async 
    public void scan(Library library) {
        if(!this.running.add(library.id())) {
            log.info("Library {} is already being scanned", library.id());
            return;
        }

        try {
            log.info("Scanning library {} ({})", library.id(), library.type());

            for(Library.Folder folder : library.folders()) {
                try {
                    List<Path> files = this.mediaWalker.walk(Path.of(folder.path()));
                    log.info("Found {} files in {}", files.size(), folder.path());
                } catch(IOException exception) {
                    log.warn("Could not scan {}: {}", folder.path(), exception.toString());
                } catch(RuntimeException exception) {
                    // A bug, not a missing drive: keep the stack trace, and still scan the other folders.
                    log.error("Scan of {} failed", folder.path(), exception);
                }
            }
        } finally {
            this.running.remove(library.id());
        }
    }
}
