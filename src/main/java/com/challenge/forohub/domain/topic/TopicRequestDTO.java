package com.challenge.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull TopicStatus status,
        @NotNull Long userId,
        @NotNull Long courseId,
        Long answerId
) {
}
