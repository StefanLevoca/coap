package sk.upjs.iot;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.Scanner;


public class SampleServer extends CoapServer {

    private static final int COAP_PORT = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);

    /*
     * Application entry point.
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        SampleServer server = new SampleServer();

        server.add(new UPJSResource());
        server.start();
    }

    private static class UPJSResource extends CoapResource {
        private int counter;

        public UPJSResource() {
            super("upjs");
            getAttributes().setTitle("UPJS Resource");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            counter++;
            exchange.respond("Hello " + counter);
        }
    }
}
