package microservicios.cloud_proyecto1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
public class CloudProyecto1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudProyecto1Application.class, args);
	}

}

@RestController
class HealthController {
    @GetMapping("/")
    public String healthCheck() {
        return "API Productos operativa 🚀";
    }
}