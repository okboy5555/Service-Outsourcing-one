package cn.czu.t1.controler;

import javax.servlet.http.HttpSession;  
import javax.websocket.HandshakeResponse;  
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
  
//配置类  将http中的session传入websocket中  
public class GetHttpSessionConfigurator extends  
        ServerEndpointConfig.Configurator {  
    @Override  
    public void modifyHandshake(ServerEndpointConfig config,  
            HandshakeRequest request, HandshakeResponse response) {  
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // ActionContext.getContext().getSession()  
        config.getUserProperties().put(HttpSession.class.getName(), httpSession);  
    }  
}