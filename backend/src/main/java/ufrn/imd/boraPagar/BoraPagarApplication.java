package ufrn.imd.boraPagar;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RestController
public class BoraPagarApplication {

	@GetMapping
	public Principal getUser(Principal principal) {
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(BoraPagarApplication.class, args);
	}

}
