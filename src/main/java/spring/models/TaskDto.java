package spring.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotEmpty(message="Provide a name for the task")
    @Size(max=50,message="The task name cannot exceed 50 characters")
    private String name;
}
