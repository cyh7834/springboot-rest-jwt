package yh.simplejwt.restjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestJwtApplication.class, args);
    }

}
