package spring.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @Column(name="unique_id")
    private int unique_id;
    @Column(name="name")
    private String name;
    @Column(name="created_at")
    private String createdAt;
    @Column(name="completed_at")
    private String completedAt;
    @Column(name="status")
    private String status;
}
