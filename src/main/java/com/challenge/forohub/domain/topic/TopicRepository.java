package com.challenge.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    boolean existsByTitleAndMessageAndIdNot(@NotBlank String title, @NotBlank String message, Long id);
}
