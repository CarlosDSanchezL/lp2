package pe.edu.cibertec.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = {" pe.edu.cibertec.web.controller","pe.edu.cibertec.web.service"})
//@PropertySource("classpath:application.properties")
//@EntityScan("pe.company.model")
//@EnableJpaRepositories("pe.company.repository")

public class SustitutorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SustitutorioApplication.class, args);
	}

}
