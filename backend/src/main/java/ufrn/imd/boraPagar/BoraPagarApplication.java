package ufrn.imd.boraPagar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BoraPagarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoraPagarApplication.class, args);
	}

}
