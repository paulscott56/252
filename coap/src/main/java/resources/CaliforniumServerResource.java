package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

/**
 * Created by paul on 2016/04/18.
 */
@Component
public class CaliforniumServerResource extends CoapResource {

    public CaliforniumServerResource() {
        super("hello");
    }

    public CaliforniumServerResource(String name) {
        super(name);
        getAttributes().setTitle(name);
    }

    public CaliforniumServerResource(String name, boolean visible) {
        super(name, visible);
        getAttributes().setTitle(name);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("yo");
    }
}
