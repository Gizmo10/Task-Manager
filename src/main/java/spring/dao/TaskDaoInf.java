package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.models.Task;

import java.util.List;

@Repository("Task")
public interface TaskDaoInf extends JpaRepository<Task, Integer>, TaskDaoCustomRepo {

    @Query(value = "SELECT * FROM tasks WHERE created_at > (CURRENT_DATE - interval '7 days')",
            nativeQuery = true)
    List<Task> findAllTasksFromPastSevenDays();

    @Query(value = "SELECT * FROM tasks WHERE created_at > (CURRENT_DATE - interval '7 days') AND status=?1",
            nativeQuery = true)
    List<Task> findAllCompletedTasksFromPastSevenDays(String status);
}
