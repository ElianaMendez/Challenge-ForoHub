package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.topic.TopicStatus;
import com.challenge.forohub.domain.topic.Topic;
import com.challenge.forohub.domain.topic.TopicStatus;

import java.time.LocalDateTime;

public record TopicDetailUpdatedDTO(
        Long id,
        String title,
        String message,
        TopicStatus status,
        LocalDateTime creationDate,
        String user,
        String courseName
) {
    public TopicDetailUpdatedDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getUser().getName(),
                topic.getCourse().getCourseName()
        );
    }
}

