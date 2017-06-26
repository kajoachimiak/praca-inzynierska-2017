package pl.pracainzynierska.pracainzynierska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.pracainzynierska.pracainzynierska.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"pl.pracainzynierska.pracainzynierska"})
public class PracaInzynierskaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracaInzynierskaApplication.class, args);
	}
}
