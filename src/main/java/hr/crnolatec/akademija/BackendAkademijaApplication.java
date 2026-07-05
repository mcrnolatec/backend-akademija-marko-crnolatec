package hr.crnolatec.akademija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendAkademijaApplication {

    static void main(String[] args) {
        SpringApplication.run(BackendAkademijaApplication.class, args);
    }

}
