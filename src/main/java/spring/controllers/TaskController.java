package spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.models.*;
import spring.services.TaskServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping({"","/"})
    public String showTaskList(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/index";
    }

    @GetMapping("/create")
    public String displayCreateTaskForm(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("taskDto", taskDto);
        return "tasks/CreateTask";
    }

    @PostMapping("/create")
    public String createTask(@Valid @ModelAttribute TaskDto taskDto, BindingResult result) {
        if(result.hasErrors()) {
            return "tasks/CreateTask";
        }

        taskService.save(taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/updateTodo")
    @ResponseBody
    public String updateTodo(@RequestBody ToDoUpdateRequest request) {
        Task task = taskService.findById(request.getId());

        if(request.getCompleted() && task.getStatus().equalsIgnoreCase("complete")) {
            task.setCompletedAt(null);
            task.setStatus("Incomplete");
            taskService.save(task);
        } else if(request.getCompleted() && task.getStatus().equalsIgnoreCase("incomplete")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            task.setStatus("Complete");
            task.setCompletedAt(dtf.format(now));
            taskService.save(task);
        }
        return "success";
    }

    @GetMapping("/analytics")
    public String showAnalytics(Model model) {
        int completedTasksFromPastSevenDays =
                taskService.findAllCompletedTasksFromPastSevenDays("Complete").size();
        int allCreatedTasks = taskService.findAllTasksFromPastSevenDays().size();
        Analytics analytics = new Analytics(allCreatedTasks, completedTasksFromPastSevenDays);
        model.addAttribute("analytics", analytics);
        return "tasks/Analytics";
    }

    @GetMapping("/filterAnalytics")
    public String analytics(Model model) {
        TaskFilterDto taskFilterDto = new TaskFilterDto();
        model.addAttribute("taskFilterDto", taskFilterDto);
        return "tasks/FilterSearch";
    }

    @PostMapping("/filteredAnalytics")
    public String filteredAnalytic(@Valid@ModelAttribute TaskFilterDto taskFilterDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/tasks/analytics";
        }
        int createdTasks = taskService.createdTasks(taskFilterDto).size();
        int completedTasks = taskService.completedTasks(taskFilterDto).size();
        Analytics analytics = new Analytics(createdTasks,completedTasks);
        model.addAttribute("analytics",analytics);
        return "tasks/Analytics";
    }
}

