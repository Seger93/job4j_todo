package ru.job4j.todo.utility;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.util.Collection;

public class TimeZoneGrabber {

    public static Collection<Task> getTimeZone(User user, Collection<Task> tasks) {
        for (Task task : tasks) {
                task.setCreated(task.getCreated()
                        .atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.of(user.getTimezone())).toLocalDateTime());
        }
        return tasks;
    }
}
