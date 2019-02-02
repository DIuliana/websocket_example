package example.api;

import example.socket.server.VerdeSocketEndpoint;
import example.socket.server.VerdeSocketEndpointContainer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Verde {

    @RequestMapping(method = GET, value = "{potId}/status")
    public String getStatus(@PathVariable String potId) throws IOException {

        VerdeSocketEndpoint verdeSocketEndpoint = VerdeSocketEndpointContainer.getVerdeSocketEndpointByPotId(potId);
        verdeSocketEndpoint.sendMessage("Give me your status!");


        //wait for the socket response
        verdeSocketEndpoint.setCountDownLatch(new CountDownLatch(1));
        try {
            verdeSocketEndpoint.getCountDownLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<String> messages = verdeSocketEndpoint.getPotMessages().get(potId);
        if(messages == null){
            messages = new ArrayList<>();
        }
        return  messages.toString();
    }


}
