package mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@ImportResource("/integration.xml")
public class MqttApplication {

    private String consumerKey = "so5cCgtLyhRoTCBWHA3eZ99xR";

    private String consumerSecret = "Q1TbhbxF7eC4CXIJ8OJbt629U7DzFYB9pQB7wdWO1z3xbX9bqi";

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(MqttApplication.class);
    }

    @Bean
    public String newline() {
        return System.getProperty("line.separator");
    }

    @Bean
    public String tstamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date());
    }

    @Bean
    public Twitter twitterTemplate() {
        System.out.println(consumerKey);
        return new TwitterTemplate(consumerKey, consumerSecret);
    }

    @Bean
    public DefaultMqttPahoClientFactory clientFactory() {
        // could define username and password for broker connection here
        return new DefaultMqttPahoClientFactory();
    }


}
