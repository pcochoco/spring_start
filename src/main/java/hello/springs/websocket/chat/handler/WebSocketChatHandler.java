package hello.springs.websocket.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springs.websocket.chat.ChatMessage;
import hello.springs.websocket.chat.ChatRoom;
import hello.springs.websocket.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//Socket 통신 - 서버와 클라이언트 1:N
//한 서버에 여러 클라이언트 접속
//Handler - 서버에 발송된 여러 클라이언트의 메시지 처리
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler{
    private final ObjectMapper objectMapper; //
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        String payload = message.getPayload();
        log.info("payload {}", payload);
        //TextMessage textMessage = new TextMessage("welcome to the chatting server");
        //session.sendMessage(textMessage);

        //웹소켓 클라이언트로부터 받은 채팅 메시지를 채팅 메시지 객체 (DTO)로 변환
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class); //
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId()); //전달받은 메시지의 id로 멧지ㅣ 전송
        room.handleActions(session, chatMessage, chatService); //
    }

}
