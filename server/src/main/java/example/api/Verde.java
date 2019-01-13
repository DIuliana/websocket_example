package example.api;

import example.socket.server.VerdeSocketEndpoint;
import example.socket.server.VerdeSocketEndpointContainer;
import example.socket.message.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Verde {


    @RequestMapping(method = GET, value = "{potId}/status")
    public void getStatus(@PathVariable String potId) throws IOException {


        VerdeSocketEndpoint verdeSocketEndpoint = VerdeSocketEndpointContainer.getVerdeSocketEndpointByPotId(potId);

        verdeSocketEndpoint.sendMessage("Give me your status!");

    }


}
