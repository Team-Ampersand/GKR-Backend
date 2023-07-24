package Ampersand.GKR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class GkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(GkrApplication.class, args);
	}

}
