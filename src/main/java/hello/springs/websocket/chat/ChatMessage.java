package hello.springs.websocket.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK //입장, 대화 상태
    }
    private MessageType type; //message type
    private String roomId; //채팅방 구별 id
    private String sender; //메시지 보낸 사람
    private String message; //메시지
}
