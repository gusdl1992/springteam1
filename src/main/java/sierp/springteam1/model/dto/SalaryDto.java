package sierp.springteam1.model.dto;

import lombok.*;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor@Builder
public class SalaryDto {
    EmployeeDto employeeDto;
    double price;
}
