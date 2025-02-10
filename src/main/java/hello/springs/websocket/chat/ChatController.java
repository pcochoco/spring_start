package hello.springs.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping //이름 받아 채팅방 생성
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

    @GetMapping //채팅방 목록 조회
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRooms();
    }

}
