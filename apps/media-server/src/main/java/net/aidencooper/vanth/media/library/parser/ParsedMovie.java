package net.aidencooper.vanth.media.library.parser;

import java.util.Map;

public record ParsedMovie(
    String title,
    Integer year,
    Map<Provider, String> ids
) {}
