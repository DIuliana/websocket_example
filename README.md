# Web Socket Example


This is a java based example for websockets. The connection between the client and the server is done, and a rest endopint is available on the server for requesting the status of the client. 

Rest endpoint: <host>/{clientId}/status

### Building and running the Server

Build `example-server` project by running `mvn clean install`.  Take the resulted war: `server-1.0-SNAPSHOT.war`, rename it to `verde`, put it in <catcalina_base>/webapps folder and start Tomcat.
If the deployment is successful the server will be exposing the above mentioned `rest endpoint` and a `websocket server endpoint` `verde/socket/{clientId}`.


### Running the Client

The client is build with `org.java-websocket`: https://github.com/TooTallNate/Java-WebSocket

Build `example-client` prject by running `mvn clean install`. The client app is a stand-alone java application so you can run it directly. Go in `ExampleClient.java` and look for the `main` function. There you will have to add the server host adrress so the URI will be something like: `ws://<host>:8080/verde/socket/{clientId}`



### Testing

1. Run the Server
2. Run the client
    a. if the connection was successful you will see in the clients logs: `opened connection` 
                in the serverlogs: `Received message:Hello, it is me. 123 :) [from 123]`
3. call the status endpoint: `http://<host>:8080/verde/<clientId>/status`
    a. check the client logs for: `received: Give me your status!`
    b. check the server logs for:  `Received message:My status: humidity 100 [from 123]`




