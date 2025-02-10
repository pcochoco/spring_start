package hello.springs.websocket.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

//WebSocket 활용시
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper; //
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    //여러 채팅방 보관용
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){
        String randomId = UUID.randomUUID().toString(); //채팅방 식별 랜덤 id
        ChatRoom chatRoom = ChatRoom.builder() //채팅방 생성 - 빌더 활용
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom); //모든 채팅방 목록으로 생성한 채팅방 추가
        return chatRoom;
    }

    //전송 - 발송하는 세션, 메시지
    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            //ChatMessage 객체를 직렬화한 값으로 TextMessage 생성
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message))); //
        } catch(IOException e){
            log.error(e.getMessage(), e);
        }
    }

}
