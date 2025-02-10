package hello.springs.websocket.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigSTOMP implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/sub"); //메시지 구독 요청
        config.setApplicationDestinationPrefixes("/pub"); //메시지 발행 요청
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //stomp websocket : ws://localhost:8080/ws-stomp 접속
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*")
                .withSockJS();
    }
}
