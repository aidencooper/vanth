package net.aidencooper.vanth.media.library.parser;

import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class LibraryParser {
    private static final Pattern YEAR_PATTERN = Pattern.compile("[\\[({]((?:19|20)\\d{2})[\\])}]");
    private static final Pattern PROVIDER_PATTERN = Pattern.compile("[\\[({](tmdb|tvdb|imdb)(?:id)?[-=](tt\\d+|\\d+)[\\])}]", Pattern.CASE_INSENSITIVE);
}
