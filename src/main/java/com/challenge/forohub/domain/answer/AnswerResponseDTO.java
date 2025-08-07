package com.challenge.forohub.domain.answer;

import java.time.LocalDateTime;



public record AnswerResponseDTO(
        Long id,
        String message,
        LocalDateTime creationDate,
        String user,
        boolean solution
) {
}
