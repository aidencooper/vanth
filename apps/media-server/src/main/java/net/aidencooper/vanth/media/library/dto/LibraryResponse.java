package net.aidencooper.vanth.media.library.dto;

import java.util.Set;

import net.aidencooper.vanth.media.library.Library;

public record LibraryResponse(
    Long id,
    Library.Type type,
    Set<String> folders
) {} 
