package hello.springs.websocket.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
//Stomp - pub, sub 방식 활용
//일일이 클라이언트에게 메세지 발송 x
//웹소켓 세션 관리 x
public class ChatRoom2 {
    private String roomId;
    private String name;

    public static ChatRoom2 create(String name){
        ChatRoom2 chatRoom2 = new ChatRoom2();
        chatRoom2.roomId = UUID.randomUUID().toString();
        chatRoom2.name = name;
        return chatRoom2;
    }
}
