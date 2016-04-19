package lwm2m;

import org.eclipse.leshan.server.californium.LeshanServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Lwm2mApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lwm2mApplication.class, args);
	}

    @Bean
    M2MServer getServer() {
        return new M2MServer();
    }
}
