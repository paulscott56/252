package components;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.springframework.stereotype.Component;
import resources.CaliforniumServerResource;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by paul on 2016/04/18.
 */
@Component
public class CaliforniumServer extends CoapServer {

    private static final int COAP_PORT = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);

    public CaliforniumServer() {
       add(new CaliforniumServerResource());
       addEndpoints();
       start();

    }

    public CaliforniumServer(int... ports) {
        super(ports);
    }

    public CaliforniumServer(NetworkConfig config, int... ports) {
        super(config, ports);
    }

    /**
     * Add individual endpoints listening on default CoAP port on all IPv4 addresses of all network interfaces.
     */
    public void addEndpoints() {
        for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
            // only binds to IPv4 addresses and localhost
            if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
                InetSocketAddress bindToAddress = new InetSocketAddress(addr, COAP_PORT);
                addEndpoint(new CoapEndpoint(bindToAddress));
            }
        }
    }




}
