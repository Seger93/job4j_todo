CREATE table if not exists tasks (
                       id SERIAL PRIMARY KEY,
                       description TEXT,
                       title TEXT,
                       created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                       done BOOLEAN
);