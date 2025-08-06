CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR(50),
    user_id BIGINT,
    course_id BIGINT,

    CONSTRAINT fk_topics_user
        FOREIGN KEY (user_id) REFERENCES users(id),

    CONSTRAINT fk_topics_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);
