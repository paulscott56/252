package za.co.paulscott.person;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class PersonApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(PersonApplication.class)
                .run(args);
    }
}