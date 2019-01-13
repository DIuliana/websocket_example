package example.socket.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VerdeSocketEndpointContainer {


    private static Map<String, VerdeSocketEndpoint> potIdVerdeSocketEndpoints = new HashMap();

    public static void add(String potId, VerdeSocketEndpoint verdeSocketEndpoint) {
        potIdVerdeSocketEndpoints.put(potId, verdeSocketEndpoint);

    }

    public static void remove(VerdeSocketEndpoint verdeSocketEndpoint) {
        potIdVerdeSocketEndpoints.remove(verdeSocketEndpoint);
    }

    public static Map<String, VerdeSocketEndpoint> getPotIdVerdeSocketEndpoints() {
        return potIdVerdeSocketEndpoints;
    }

    public static VerdeSocketEndpoint getVerdeSocketEndpointByPotId(String potId) {

        Optional<String> first = potIdVerdeSocketEndpoints.keySet().stream().filter(key -> key.equalsIgnoreCase(potId)).findFirst();

        return potIdVerdeSocketEndpoints.get(first.get());
    }

}
