ALTER TABLE tasks ADD COLUMN users_id bigint;
ALTER TABLE tasks ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users (id);