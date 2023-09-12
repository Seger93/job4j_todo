CREATE table if not exists tasks (
                       id SERIAL PRIMARY KEY,
                       description TEXT,
                       title TEXT,
                       created TIMESTAMP,
                       done BOOLEAN
);