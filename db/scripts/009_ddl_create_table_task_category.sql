CREATE TABLE task_category (
                             id serial PRIMARY KEY,
                              tasks_id int not null REFERENCES tasks(id),
                              category_id int not null REFERENCES category(id),
                              UNIQUE (category_id, tasks_id)
);