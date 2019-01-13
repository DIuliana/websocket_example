package example.socket.server;


import example.socket.SessionIdPotId;
import example.socket.message.Message;
import example.socket.message.MessageDecoder;
import example.socket.message.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/socket/{potId}")
public class VerdeSocketEndpoint {

    private Session session;
    private SessionIdPotId sessionIdPotId;

    @OnOpen
    //when the client connects
    public void onOpen(Session session, @PathParam("potId") String potId) throws IOException {
        this.session = session;
        sessionIdPotId = new SessionIdPotId(session.getId(), potId);
        VerdeSocketEndpointContainer.add(potId, this);

//        Message message = new Message();
////        message.setFrom(potId);
//        message.setContent("Hello " + potId + "!" + "You are connected!");
        sendMessage("Hello " + potId + "!" + "You are connected!");
    }

    @OnMessage
    public void OnMessage(Session session, String message) throws IOException {
        System.out.println("Received message:" + message + " [from " + sessionIdPotId.getPotId() + "]");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        VerdeSocketEndpointContainer.remove(this);

//        Message message = new Message();
        String potId = sessionIdPotId.getSessionId();
//        message.setFrom(potId);
//        message.setContent("Bye " + potId + "!" + "You are disconnected!");
        sendMessage("Bye " + potId + "!" + "You are disconnected!");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }


    public void sendMessage(String message) throws IOException {

        this.session.getBasicRemote().sendText(message);

    }


}
