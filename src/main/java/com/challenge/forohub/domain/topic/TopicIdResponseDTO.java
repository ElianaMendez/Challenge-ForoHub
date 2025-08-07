package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.answer.AnswerResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TopicIdResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        TopicStatus status,
        String userName,
        String courseName,
        List<AnswerResponseDTO> answers
) {
}
