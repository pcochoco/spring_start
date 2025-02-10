package hello.springs.websocket.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

//only websocket
@Getter
public class ChatRoom {
    private String roomId;
    private String name; //채팅방의 이름
    private Set<WebSocketSession> sessions = new HashSet<>(); //채팅방 참여 클라이언트의 정보 목록

    @Builder //객체 속성 설정하는 빌더 클래스에서 새 객체 생성, 반환
    public ChatRoom(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        //메시지 타입 - ENTER, TALK의 기능
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session); //채팅방 입장 시 클라이언트의 session 추가
            chatMessage.setMessage(chatMessage.getSender() + "입장");
        }
        sendMessage(chatMessage, chatService); //TALK : 메시지 전송
    }
    public <T> void sendMessage(T message, ChatService chatService){
        //병렬 처리 : parallelStream
        //채팅방에 메시지 전송 시 모든 session에 대해 해당 세션, 메시지 발송되도록 함
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
