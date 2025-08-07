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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private TopicService topicService;

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
                user, course, new ArrayList<>());

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

    @GetMapping("/{id}")
    public ResponseEntity<TopicIdResponseDTO> getTopicById(@PathVariable Long id) {
        TopicIdResponseDTO response = topicService.display(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO data) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (!optionalTopic.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar si ya existe un tópico con el mismo título y mensaje (y diferente ID)
        boolean duplicateExists = topicRepository.existsByTitleAndMessageAndIdNot(data.title(), data.message(), id);
        if (duplicateExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Validar existencia de usuario y curso
        Optional<User> userOptional = userRepository.findById(data.userId());
        Optional<Course> courseOptional = courseRepository.findById(data.courseId());

        if (!userOptional.isPresent() || !courseOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Usuario o curso no valido.");
        }

        // Actualizar los datos
        Topic topic = optionalTopic.get();
        //topic.getAnswers().clear(); // si quieres limpiar respuestas antes de actualizar (opcional)

        topic.setTitle(data.title());
        topic.setMessage(data.message());
        topic.setStatus(data.status());
        topic.setUser(userOptional.get());
        topic.setCourse(courseOptional.get());

        topicRepository.save(topic);
        return ResponseEntity.ok(new TopicDetailUpdatedDTO(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
