package spring.services;

import spring.models.Task;
import spring.models.TaskDto;
import spring.models.TaskFilterDto;

import java.util.List;

public interface TaskServiceInf {
    public List<Task> findAll();
    public void save(TaskDto taskDto);
    public void save(Task task);
    public void deleteTask(int id);
    public Task findById(int id);
    public List<Task> createdTasks(TaskFilterDto taskFilterDto);
    public List<Task> completedTasks(TaskFilterDto taskFilterDto);
    public List<Task> findAllTasksFromPastSevenDays();
    public List<Task> findAllCompletedTasksFromPastSevenDays(String status);
    public void updateTask(int id);
}