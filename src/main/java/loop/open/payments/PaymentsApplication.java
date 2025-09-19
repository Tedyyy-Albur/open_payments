package loop.open.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("loop.open.payments.entity") // <-- AÑADE ESTA LÍNEA
@EnableJpaRepositories("loop.open.payments.repository")
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

}
