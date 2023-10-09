package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static ru.job4j.todo.utility.TimeZoneGrabber.getTimeZone;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("tasks", getTimeZone(user, taskService.getAll()));
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "errors/404";
        }
        model.addAttribute("priorities", priorityService.getAll());
        model.addAttribute("tasks", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/new")
    public String findByNew(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("tasks", getTimeZone(user, taskService.findState(false)));
        return "tasks/new";
    }

    @GetMapping("/old")
    public String newTask(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("tasks", getTimeZone(user, taskService.findState(true)));
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
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, @SessionAttribute User user,
                         @RequestParam List<Integer> categoryIds) {
        task.setUser(user);
        task.getCategories().addAll(categoryService.findAllById(categoryIds));
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
        model.addAttribute("priorities", priorityService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, @SessionAttribute User user,
                         @RequestParam List<Integer> categoryIds) {
        task.setUser(user);
        task.getCategories().addAll(categoryService.findAllById(categoryIds));
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