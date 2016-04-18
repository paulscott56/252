package coap;

import components.CaliforniumServer;
import components.PlugTestServer;
import org.eclipse.californium.core.CoapServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import resources.CaliforniumServerResource;

import java.net.SocketException;

@SpringBootApplication
@ComponentScan
public class CoAPApplication extends CoapServer {

    // @Autowired
    // PlugTestServer ptServer;

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

    /*@Bean
    PlugTestServer getPlugTestServer() {
            ptServer = new PlugTestServer();
            ptServer.StartServer();
            return ptServer;
    }*/


}
