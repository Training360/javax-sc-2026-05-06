package employees;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaGateway {

    private final EmployeesService employeesService;

    private final KafkaTemplate<String, EmployeeHasBeenCreatedEvent> kafkaTemplate;

    @KafkaListener(topics = "employees-backend-request")
    public void listen(CreateEmployeeRequest request) {
        log.info("Received message: {}", request);
        employeesService.createEmployee(new EmployeeResource(request.name()));
    }

    @EventListener
    public void handleEvent(EmployeeHasBeenCreatedEvent event) {
        kafkaTemplate.send("employees-backend-events",
                event);
    }
}
