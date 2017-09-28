package cn.czu.t1.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ws/{serviceId}", configurator = GetHttpSessionConfigurator.class)
public class ChatAnnotation {

    private static final Map<String,Object> connections = new HashMap<String,Object>();

    private Session session;


    @OnOpen
    public void start(Session session, @PathParam("serviceId") String serviceId) {
        this.session = session;
        connections.put(serviceId.replace("serviceId=",""), this);

    }


    @OnClose
    public void end() {
        connections.remove(this);
    }


    /**
     * 消息发送触发方法
     * @param message
     */
    @OnMessage
    public void incoming(String message, @PathParam("serviceId") String serviceId) {
        // Never trust the client
        serviceId = serviceId.replace("serviceId=","");
        for (String key : connections.keySet()) {
            //           CS-->C                           C-->CS
            if(key.equals("cs" + serviceId) || serviceId.equals("cs"+key)){
                ChatAnnotation client = null ;
                try {
                    client = (ChatAnnotation) connections.get(key);
                    synchronized (client) {
                        client.session.getBasicRemote().sendText(message);
                    }
                } catch (IOException e) {
                    connections.remove(client);
                    try {
                        client.session.close();
                    } catch (IOException e1) {
                        // Ignore
                    }
                }
            }

        }
    }

    @OnError
    public void onError(Throwable t) throws Throwable {

    }
}

