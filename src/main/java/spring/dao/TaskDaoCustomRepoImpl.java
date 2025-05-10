package spring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.models.Task;
import spring.models.TaskFilterDto;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskDaoCustomRepoImpl implements TaskDaoCustomRepo {
    private final EntityManager em;

    @Override
    public List<Task> findAllTasksCreated(TaskFilterDto taskFilterDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root root = cq.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if(taskFilterDto.getDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), taskFilterDto.getDateFrom()));
        }

        if(taskFilterDto.getDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), taskFilterDto.getDateTo()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Task> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public List<Task> findAllCompletedTasks(TaskFilterDto taskFilterDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root root = cq.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if(taskFilterDto.getDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), taskFilterDto.getDateFrom()));
        }

        if(taskFilterDto.getDateTo() != null && taskFilterDto.getDateFrom() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), taskFilterDto.getDateTo()));
        }

        predicates.add(cb.like(root.get("status"), "Complete"));

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Task> query = em.createQuery(cq);

        return query.getResultList();
    }
}

