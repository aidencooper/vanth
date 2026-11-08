package net.aidencooper.vanth.media.scanner.filter;

import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import net.aidencooper.vanth.media.scanner.ScanEntry;

@Component 
@Order(10)
public class HiddenFileFilter implements ScanFilter {
    @Override
    public Optional<String> reject(ScanEntry entry) {
        return entry.name().startsWith(".") ? Optional.of("hidden") : Optional.empty();
    }
    
}
