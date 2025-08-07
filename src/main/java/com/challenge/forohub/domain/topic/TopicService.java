package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.answer.AnswerResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicIdResponseDTO display(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        List<AnswerResponseDTO> answers = topic.getAnswers().stream()
                .map(answer -> new AnswerResponseDTO(
                        answer.getId(),
                        answer.getMessage(),
                        answer.getCreationDate(),
                        answer.getUser().getName(),
                        answer.isSolution()
                )).toList();

        return new TopicIdResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getUser().getName(),
                topic.getCourse().getCourseName(),
                answers
        );
    }
}
