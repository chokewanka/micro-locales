package pe.com.chokewanka.springboot.micro.locales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroLocalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroLocalesApplication.class, args);
	}

}
