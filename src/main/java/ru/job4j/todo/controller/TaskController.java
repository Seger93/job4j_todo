package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "errors/404";
        }
        model.addAttribute("tasks", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/new")
    public String findByNew(Model model) {
        model.addAttribute("tasks", taskService.findState(false));
        return "tasks/new";
    }

    @GetMapping("/old")
    public String newTask(Model model) {
        model.addAttribute("tasks", taskService.findState(true));
        return "tasks/old";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = taskService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Задание указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, @SessionAttribute User user) {
        task.setUser(user);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("error", "Задача не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/tasks";
    }

    @GetMapping("/updateDone/{id}")
    public String updateDone(Model model, @ModelAttribute Task task) {
        var isUpdated = taskService.updateState(task);
        if (!isUpdated) {
            model.addAttribute("message", "Задание не найдено");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
}