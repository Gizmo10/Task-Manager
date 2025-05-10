package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.TaskDaoInf;
import spring.models.Task;
import spring.models.TaskDto;
import spring.models.TaskFilterDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskServiceInf{
    @Autowired
    private TaskDaoInf taskDao;

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public Task findById(int id) {
        return taskDao.findById(id).get();
    }

    @Override
    public void save(TaskDto taskDto) {
        Task task = new Task();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        task.setName(taskDto.getName());
        task.setCreatedAt(dtf.format(now));
        task.setStatus("Incomplete");
        taskDao.save(task);
    }

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteTask(int id) {
        try {
            Task task = taskDao.findById(id).get();
            taskDao.delete(task);
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public List<Task> createdTasks(TaskFilterDto taskFilterDto) {
        return taskDao.findAllTasksCreated(taskFilterDto);
    }

    @Override
    public List<Task> completedTasks(TaskFilterDto taskFilterDto) {
        return taskDao.findAllCompletedTasks(taskFilterDto);
    }

    @Override
    public List<Task> findAllTasksFromPastSevenDays() {
        return taskDao.findAllTasksFromPastSevenDays();
    }

    @Override
    public List<Task> findAllCompletedTasksFromPastSevenDays(String status) {
        return taskDao.findAllCompletedTasksFromPastSevenDays(status);
    }

    @Override
    public void updateTask(int id) {
        Task task = findById(id);
        task.setCompletedAt(null);
        task.setStatus("Incomplete");
        taskDao.save(task);
    }
}