package za.co.paulscott.person;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
@EnableHystrix
public class PersonApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(PersonApplication.class)
                .run(args);
    }
}