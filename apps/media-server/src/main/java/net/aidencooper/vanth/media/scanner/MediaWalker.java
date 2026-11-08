package net.aidencooper.vanth.media.scanner;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aidencooper.vanth.media.scanner.filter.ScanFilter;

@Component 
@Slf4j
@RequiredArgsConstructor
public class MediaWalker {
    private final List<ScanFilter> filters;

    public List<Path> walk(Path root) throws IOException {
        List<Path> files = new ArrayList<>();

        Files.walkFileTree(root, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
                if(directory.equals(root) || !isRejected(ScanEntry.of(directory, attributes))) return FileVisitResult.CONTINUE;
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                if(attributes.isRegularFile() && !isRejected(ScanEntry.of(file, attributes))) files.add(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exception) throws IOException {
                if(file.equals(root)) throw exception;
                log.warn("Skipping unreadable path {}: {}", file, exception.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path directory, IOException exception) {
                if(exception != null) log.warn("Stopped reading {} partway: {}", directory, exception.toString());
                return FileVisitResult.CONTINUE;
            }
        });

        return files;
    }

    private boolean isRejected(ScanEntry entry) {
        for(ScanFilter filter : this.filters) {
            Optional<String> reason = filter.reject(entry);
            if(reason.isPresent()) {
                log.debug("Skipping {} ({})", entry.path(), reason.get());
                return true;
            }
        }

        return false;
    }
}
