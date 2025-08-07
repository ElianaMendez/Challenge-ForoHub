-- Topic 1 - 2 respuestas (una es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('You can use Selenium with JUnit to run full browser tests efficiently.', 1, 2, false, NOW()),
('Headless mode helps you run tests in CI/CD pipelines. Works great with ChromeDriver.', 1, 3, true, NOW());

-- Topic 2 - 1 respuesta (no es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('Mocking services with Mockito is a great way to isolate your unit tests.', 2, 5, false, NOW());

-- Topic 3 - 2 respuestas (una es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('Spring Boot''s @WebMvcTest is useful for controller tests without loading the full context.', 3, 4, false, NOW()),
('Using @MockBean to mock dependencies can keep your tests isolated and clean.', 3, 1, true, NOW());

-- Topic 4 - sin respuestas

-- Topic 5 - 1 respuesta (no es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('For readable test scenarios, use Cucumber with Gherkin syntax. It helps non-tech team members too.', 5, 6, false, NOW());

-- Topic 6 - 3 respuestas (una es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('An in-memory database like H2 is ideal for integration tests.', 6, 7, false, NOW()),
('Make sure to reset data between tests using @DirtiesContext or custom SQL scripts.', 6, 8, true, NOW()),
('Use @Transactional to rollback changes automatically in your test methods.', 6, 9, false, NOW());

-- Topic 7 - 1 respuesta (no es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('Set up GitHub Actions with a Maven step and use the test profile for automated builds.', 7, 3, false, NOW());

-- Topic 8 - sin respuestas

-- Topic 9 - 1 respuesta (no es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('Cypress allows simulating full user interactions, including form inputs and route validations.', 9, 10, false, NOW());

-- Topic 10 - 1 respuesta (no es solución)
INSERT INTO answers (message, topic_id, user_id, solution, creation_date) VALUES
('Timeout issues can be reduced by increasing wait conditions or using custom commands.', 10, 2, false, NOW());
