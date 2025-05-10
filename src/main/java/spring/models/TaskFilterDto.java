package spring.models;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilterDto {
    @Size(max=10, message="Must be 10 characters")
    @Size(min=10,message="Must be 10 characters")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dateFrom;

    @Size(max=10, message="Must be 10 characters")
    @Size(min=10,message="Must be 10 characters")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dateTo;
}