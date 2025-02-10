package hello.springs.websocket.chat.config;
//WebSocket 활성화 Config

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@EnableWebSocket //WebSocket 활성화
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        //WebSocket 접속 endpoint : /ws/chat
        //ws://localhost:8080/ws/chat 으로 연결하도록
        //CORS : 도메인이 다른 서버에서도 접속이 가능하도록 설정(첫 handshake에서 http이므로)
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
