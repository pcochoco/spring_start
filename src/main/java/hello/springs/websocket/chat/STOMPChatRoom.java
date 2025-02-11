package hello.springs.websocket.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
//websocket, stomp 이용 채팅방
//pub, sub 을 이용한 구독자 관리
//웹소켓 세션 관리, 발송 구현 x
@Getter
@Setter
public class STOMPChatRoom {
    private String roomId;
    private String name;

    public static STOMPChatRoom create(String name){
        STOMPChatRoom stompChatRoom = new STOMPChatRoom();
        stompChatRoom.roomId = UUID.randomUUID().toString();
        stompChatRoom.name = name;
        return stompChatRoom;
    }
}
