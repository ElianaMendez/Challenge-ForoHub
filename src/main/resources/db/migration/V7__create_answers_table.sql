CREATE TABLE answers (
    id SERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT,
    user_id BIGINT,
    solution BOOLEAN DEFAULT FALSE,
    creation_date TIMESTAMP NOT NULL,

    CONSTRAINT fk_answers_topic
        FOREIGN KEY (topic_id) REFERENCES topics(id),

    CONSTRAINT fk_answers_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);
