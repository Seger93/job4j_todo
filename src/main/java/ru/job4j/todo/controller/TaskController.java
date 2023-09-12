package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
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
        model.addAttribute("tasks", taskService.findAllFalse());
        return "tasks/new";
    }

    @GetMapping("/old")
    public String newTask(Model model) {
        model.addAttribute("tasks", taskService.findAllTrue());
        return "tasks/old";
    }

    @PostMapping("/update")
    public String saveTask(@ModelAttribute Task task) {

        return "redirect:/tasks";
    }
}