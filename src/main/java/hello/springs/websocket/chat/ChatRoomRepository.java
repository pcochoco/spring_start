package hello.springs.websocket.chat;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

//ChatService에 대한 대체
//채팅방 생성, 조회
@Repository
public class ChatRoomRepository {
    private Map<String, STOMPChatRoom>  chatRoomSTOMPMap; //채팅방 목록
    @PostConstruct
    private void init(){
        chatRoomSTOMPMap = new LinkedHashMap<>();
    }

    public List<STOMPChatRoom> findAllRoom(){
        List chatRooms = new ArrayList<>(chatRoomSTOMPMap.values());
        //채팅방 생성 순서 최근 순으로 반환
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public STOMPChatRoom findRoomById(String id){
        return chatRoomSTOMPMap.get(id);
    }

    public STOMPChatRoom createChatRoom(String name){
        //새 채팅방 생성
        STOMPChatRoom chatRoom = STOMPChatRoom.create(name);
        //전체 채팅방 목록으로 id, 채팅방 추가
        chatRoomSTOMPMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
