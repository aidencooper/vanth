package net.aidencooper.vanth.media.library.dto;

import java.util.Set;

import net.aidencooper.vanth.media.library.Library;

public record LibraryRequest(
    Library.Type type,
    Set<String> folders
) {}
