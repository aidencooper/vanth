package net.aidencooper.vanth.media.library.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import net.aidencooper.vanth.media.library.Library;

public record LibraryRequest(
    @NotNull Library.Type type,
    @NotEmpty Set<@NotBlank String> folders
) {}
