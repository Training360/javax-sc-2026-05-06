package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration(proxyBeanMethods = false)
public class MessagingConfiguration {

    @Bean
    public Function<CreateEmployeeRequest, CreateEmployeeResponse>
        createEmployee(EmployeesService employeesService) {
            return request -> {
                var employee = employeesService.createEmployee(new EmployeeResource(request.name()));
                return new CreateEmployeeResponse(employee.getId(), employee.getName());
            };
    }

}
