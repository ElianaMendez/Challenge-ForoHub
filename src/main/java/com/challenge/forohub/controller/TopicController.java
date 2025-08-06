package com.challenge.forohub.controller;

import com.challenge.forohub.domain.answer.Answer;
import com.challenge.forohub.domain.answer.AnswerRepository;
import com.challenge.forohub.domain.course.Course;
import com.challenge.forohub.domain.course.CourseRepository;
import com.challenge.forohub.domain.topic.*;
import com.challenge.forohub.domain.user.User;
import com.challenge.forohub.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> getAllTopics() {
        List<TopicResponseDTO> topics = topicRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topics);
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicRequestDTO request) {
        User user = userRepository.findById(request.userId()).orElseThrow();
        Course course = courseRepository.findById(request.courseId()).orElseThrow();
        Answer answer = request.answerId() != null
                ? answerRepository.findById(request.answerId()).orElse(null)
                : null;

        Topic topic = new Topic(null, request.title(), request.message(),
                LocalDateTime.now(),
                request.status() != null ? request.status() : TopicStatus.OPEN,
                user, course);

        Topic saved = topicRepository.save(topic);
        return ResponseEntity.ok(toDTO(saved));
    }

    private TopicResponseDTO toDTO(Topic topic) {
        return new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getUser().getName(),
                topic.getCourse().getCourseName()
        );
    }
}
