package components;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.network.interceptors.MessageTracer;
import org.eclipse.californium.core.network.interceptors.OriginTracer;
import org.springframework.stereotype.Component;
import resources.*;


/**
 * Created by paul on 2016/04/18.
 */
@Component
public class PlugTestServer extends CoapServer {

    // exit codes for runtime errors
    public static final int ERR_INIT_FAILED = 1;

    // allows port configuration in Californium.properties
    private static final int port = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);

    public void StartServer() {
        try {
            CoapServer server = new PlugTestServer();
            server.start();
            for (Endpoint ep : getEndpoints()) {
                ep.addInterceptor(new MessageTracer());
                // Eclipse IoT metrics
                ep.addInterceptor(new OriginTracer());
            }

            System.out.println(PlugTestServer.class.getSimpleName() + " listening on port " + port);
        } catch (Exception e) {
            System.err.printf("Failed to create "+PlugTestServer.class.getSimpleName()+": %s\n", e.getMessage());
            System.err.println("Exiting");
            System.exit(ERR_INIT_FAILED);
        }
    }

    /**
     * Add individual endpoints listening on default CoAP port on all IPv4 addresses of all network interfaces.
     */
    public void addEndpoints() {
        for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
            // only binds to IPv4 addresses and localhost
            if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
                InetSocketAddress bindToAddress = new InetSocketAddress(addr, port);
                addEndpoint(new CoapEndpoint(bindToAddress));
            }
        }
    }

    public PlugTestServer() {

        NetworkConfig.getStandard() // used for plugtest
                .setInt(NetworkConfig.Keys.MAX_MESSAGE_SIZE, 64)
                .setInt(NetworkConfig.Keys.PREFERRED_BLOCK_SIZE, 64)
                .setInt(NetworkConfig.Keys.NOTIFICATION_CHECK_INTERVAL_COUNT, 4)
                .setInt(NetworkConfig.Keys.NOTIFICATION_CHECK_INTERVAL_TIME, 30000)
                .setString(NetworkConfig.Keys.HEALTH_STATUS_PRINT_LEVEL, "INFO");

        // add resources to the server
        add(new DefaultTest());
        add(new LongPath());
        add(new Query());
        add(new Separate());
        add(new Large());
        add(new LargeUpdate());
        add(new LargeCreate());
        add(new LargePost());
        add(new LargeSeparate());
        add(new Observe());
        add(new ObserveNon());
        add(new ObserveReset());
        add(new ObserveLarge());
        add(new ObservePumping());
        add(new ObservePumping(Type.NON));
        add(new LocationQuery());
        add(new MultiFormat());
        add(new Link1());
        add(new Link2());
        add(new Link3());
        add(new Path());
        add(new Validate());
        add(new Create());
        add(new Shutdown());
    }


}
