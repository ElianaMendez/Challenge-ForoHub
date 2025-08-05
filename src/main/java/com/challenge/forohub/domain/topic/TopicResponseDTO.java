package com.challenge.forohub.domain.topic;

import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String user,
        String course,
        String answer
) {
}
