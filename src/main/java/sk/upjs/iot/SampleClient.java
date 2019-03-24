package sk.upjs.iot;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;

import java.util.Scanner;


public class SampleClient {

    private static void showResponse(CoapResponse response) {
        System.out.println(response.getCode());
        System.out.println(response.getOptions());
        System.out.println(response.getResponseText());
        System.out.println(System.lineSeparator() + "ADVANCED" + System.lineSeparator());
        System.out.println(Utils.prettyPrint(response));
    }

    private static class Handler implements CoapHandler {

        @Override
        public void onLoad(CoapResponse response) {
            showResponse(response);
        }

        @Override
        public void onError() {
            System.err.println("Error");
        }
    }

    public static void main(String args[]) {
        CoapClient client = new CoapClient("coap://localhost/upjs");
        CoapResponse response = client.get();
        if (response != null) {
            showResponse(response);
        } else {
            System.out.println("No response received.");
        }

        client.get(new Handler());

        try (Scanner s = new Scanner(System.in)) {
            s.nextLine();
        }
    }
}