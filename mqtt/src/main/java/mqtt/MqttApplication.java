package mqtt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mqtt.model.*;
import mqtt.repo.KodiRepository;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.ArrayList;
import java.util.List;

@IntegrationComponentScan
@SpringBootApplication
public class MqttApplication {

    private static final Logger LOGGER = Logger.getLogger(MqttApplication.class);

    @Autowired
    private KodiRepository kodiRepo;

    /**
     * Load the Spring Integration Application Context
     *
     * @param args - command line arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(MqttApplication.class, args);
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://localhost:1883");
        return factory;
    }

    // publisher

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
                e -> e.poller(Pollers.fixedDelay(1000)))
                .transform(p -> p)
                .handle(mqttOutbound())
                .get();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("Publisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("/outTopic");
        return messageHandler;
    }

    // consumer

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883", "SpringClient",
                        "/inTopic/#", "/kodi/status/title");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                // System.out.println(message.getHeaders().get("mqtt_topic") + "  " + message.getPayload());
                KodiTitle t = new KodiTitle();
                StreamDetails sd = new StreamDetails();
                Subtitle s = new Subtitle();

                String payload = message.getPayload().toString();
                // System.out.println(payload);
                JsonParser parser = new JsonParser();
                try {
                    List<Audio> alist = new ArrayList<Audio>();
                    List<Video> vlist = new ArrayList<Video>();

                    JsonObject o = parser.parse(payload).getAsJsonObject();
                    JsonObject details = o.getAsJsonObject("kodi_details");
                    JsonObject stream = details.getAsJsonObject("streamdetails");

                    // build the audio, video and subtitle objects
                    JsonArray audio = stream.getAsJsonArray("audio");
                    for (JsonElement aj : audio) {
                        //System.out.println(aj.toString());
                        JsonObject j = aj.getAsJsonObject();
                        Audio a = new Audio();
                        a.setChannels(j.get("channels").getAsInt());
                        a.setCodec(j.get("codec").getAsString());
                        a.setLanguage(j.get("language").getAsString());
                        alist.add(a);
                    }

                    JsonArray video = stream.getAsJsonArray("video");
                    for (JsonElement vj : video) {
                        //System.out.println(vj.toString());
                        JsonObject j = vj.getAsJsonObject();
                        Video v = new Video();
                        v.setCodec(j.get("codec").getAsString());
                        v.setAspect(j.get("aspect").getAsDouble());
                        v.setDuration(j.get("duration").getAsInt());
                        v.setHeight(j.get("height").getAsInt());
                        v.setStereomode(j.get("stereomode").getAsString());
                        v.setWidth(j.get("width").getAsInt());
                        vlist.add(v);
                    }

                    // set the stream details
                    sd.setAudio(alist);
                    sd.setSubtitle(s);
                    sd.setVideo(vlist);

                    t.setType(details.get("type").getAsString());
                    t.setTitle(details.get("title").getAsString());
                    t.setFanart(details.get("fanart").getAsString());
                    t.setFile(details.get("file").getAsString());
                    t.setLabel(details.get("label").getAsString());
                    t.setThumbnail(details.get("thumbnail").getAsString());
                    t.setVal("");
                    t.setStreamdetails(sd);

                    kodiRepo.save(t);

                } catch(Exception e) {
                    // do nothing for now
                    // e.printStackTrace();
                }

            }

        };
    }
}