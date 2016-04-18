package coap;

import components.CaliforniumServer;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import resources.CaliforniumServerResource;

@SpringBootApplication
@ComponentScan
public class CoAPApplication extends CoapServer {

    @Autowired
    CaliforniumServerResource sResource;

    @Autowired
    CaliforniumServer server;


    public static void main(String[] args) {
        SpringApplication.run(CoAPApplication.class, args);
    }

    @Bean
    CaliforniumServerResource getServeresource() {
        return new CaliforniumServerResource("hello");
    }

    @Bean
    CaliforniumServer getServer() {
        server = new CaliforniumServer();
        server.addEndpoints();
        server.start();
        return server;
    }



}
