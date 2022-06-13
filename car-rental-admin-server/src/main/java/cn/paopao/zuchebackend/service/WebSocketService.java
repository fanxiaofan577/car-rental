package cn.paopao.zuchebackend.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/bindingRecord")
@Component
public class WebSocketService {

    private Session session;
    private static CopyOnWriteArraySet<WebSocketService> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 新建webSocket配置类
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
    }

    /**
     * 断开连接
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
    }

    /**
     * 接收到信息
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message){
        for (WebSocketService webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
            }
        }
    }

}
