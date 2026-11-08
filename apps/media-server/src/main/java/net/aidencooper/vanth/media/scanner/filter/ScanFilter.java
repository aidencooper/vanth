package net.aidencooper.vanth.media.scanner.filter;

import java.util.Optional;

import net.aidencooper.vanth.media.scanner.ScanEntry;

public interface ScanFilter {
    Optional<String> reject(ScanEntry entry);
}
