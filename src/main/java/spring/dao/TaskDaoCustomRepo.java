package spring.dao;

import spring.models.Task;
import spring.models.TaskFilterDto;

import java.util.List;

public interface TaskDaoCustomRepo {
    public List<Task> findAllTasksCreated(TaskFilterDto taskFilterDto);
    public List<Task> findAllCompletedTasks(TaskFilterDto taskFilterDto);
}

