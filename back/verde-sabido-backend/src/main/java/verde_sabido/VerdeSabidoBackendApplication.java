package verde_sabido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VerdeSabidoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerdeSabidoBackendApplication.class, args);
	}

	@GetMapping("/health")
	public String healthCheck() {
		return "OK - " + java.time.LocalDateTime.now();
	}
}