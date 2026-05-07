package training.employeesgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dummy-employees")
public class EmployeeController {

    @GetMapping
    public Flux<EmployeeDto> getEmployees() {
        return Flux.just(
                new EmployeeDto(1L, "John Doe"),
                new EmployeeDto(2L, "Jane Smith"),
                new EmployeeDto(3L, "Alice Johnson")
        );
    }
}
