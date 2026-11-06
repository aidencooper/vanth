package net.aidencooper.vanth.media.exception;

public record ErrorResponse(
    int statusCode,
    String message
) {} 
