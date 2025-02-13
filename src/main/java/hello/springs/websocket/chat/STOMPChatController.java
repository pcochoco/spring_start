package hello.springs.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class STOMPChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message){
        if(ChatMessage.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "입장");
        //System.out.println("클라이언트가 준 메시지:" + message);
        //클라이언트가 구독한 경로 (`/sub/chat/room/{roomId}`)로 메시지 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        //System.out.println("메시지 전송 완료");
    }
}
