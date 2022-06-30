package vn.elca.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vlp
 *
 */
@SpringBootApplication
public class ApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWebConfig.class, args);
    }
}
