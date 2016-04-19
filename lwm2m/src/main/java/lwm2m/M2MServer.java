package lwm2m;

import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.leshan.core.node.LwM2mNode;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.core.request.ObserveRequest;
import org.eclipse.leshan.core.response.LwM2mResponse;
import org.eclipse.leshan.server.californium.LeshanServerBuilder;
import org.eclipse.leshan.server.californium.impl.LeshanServer;
import org.eclipse.leshan.server.client.Client;
import org.eclipse.leshan.server.client.ClientRegistryListener;
import org.eclipse.leshan.server.client.ClientUpdate;
import org.eclipse.leshan.server.observation.ObservationRegistryListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by paul on 2016/04/19.
 */
@Component
public class M2MServer {


    private LeshanServer lwServer;

    public M2MServer() {
        lwServer = new LeshanServerBuilder().setLocalAddress("localhost", 5683).build();

        lwServer.getObservationRegistry().addListener(new ObservationRegistryListener() {

            @Override
            public void newValue(Observation observation, LwM2mNode value) {

                observation.getPath().toString();
                //System.out.println("New notification from client " + observation.getClient().getEndpoint() + ": "
                //        + value);
            }

            @Override
            public void newObservation(Observation observation) {

                System.out.println("Observing resource " + observation.getPath() + " from client");
            }

            @Override
            public void cancelled(Observation observation) {
                //
            }

        });

        lwServer.getClientRegistry().addListener(new ClientRegistryListener() {
            @Override
            public void registered(Client client) {
                LwM2mResponse response = null;
                try {
                    response = lwServer.send(client, new ObserveRequest(3, 0, 13));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Observe response: " + response);
            }

            @Override
            public void updated(ClientUpdate clientUpdate, Client client) {

            }

            @Override
            public void unregistered(Client client) {
                //
            }
        });

        lwServer.start();
        System.out.println("Server started!");
    }

    public void addEndpoints() {
        for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
            // only binds to IPv4 addresses and localhost
            if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
                InetSocketAddress bindToAddress = new InetSocketAddress(addr, 5683);
            }
        }
    }
}
