package sg.nus.iss.team9ad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import sg.nus.iss.team9ad.model.Staff;
import sg.nus.iss.team9ad.repo.StaffRepo;

@SpringBootApplication
public class WetBulbGlobeTemperatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetBulbGlobeTemperatureApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner loadData(StaffRepo staffRepo) {
		return (args) -> {
			// Add a few staff records
			Staff staff1 = new Staff("John", "password1", "staff", "john@gmail.com");
			staffRepo.save(staff1);

			Staff staff2 = new Staff("Jane", "password2", "admin", "jane@gmail.com");
			staffRepo.save(staff2);
			
//			Staff staff3 = new Staff("Jame", "password2", "admin", "jame@gmail.com");
//			staffRepo.save(staff3);
		};
	}
}

