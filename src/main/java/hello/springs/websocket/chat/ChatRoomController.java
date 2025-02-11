package hello.springs.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//채팅 화면(view) 용도로 분리
//html
//json -> js로 동적 표시
//@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;
    public ChatRoomController(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }
    //프론트에서의 경로(단수 room)
    //채팅방 목록 화면
    @GetMapping("/room") //get - 직접 사용자가 경로 접근, 링크 접속 시 기본 동작
    public String chatRoomsPage(Model model){
        return "chat/room";
    }

    //rest api 엔드포인트(rooms) - 모든 채팅방 목록 조회
    @GetMapping("/rooms")
    @ResponseBody //HTTP 응답 본문에 json 포함(데이터 전달 후 동적 표시 대상)
    public List<STOMPChatRoom> getChatRooms(){
        return chatRoomRepository.findAllRoom();
    }

    //채팅방 생성 - 전체 채팅방 컬렉션에 포함하는 개념(rooms)
    //사용자가 폼 제출 시 post 동작
    @PostMapping("/rooms")
    @ResponseBody
    public STOMPChatRoom createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }

    //채팅방 입장
    //실제로 사용자가 채팅방에 들어가는 입장 로직 포함(세션에 사용자 등록 등) : enter을 엔드포인트에 포함
    @GetMapping("/room/enter/{roomId}")
    public String chatRoomDetailPage(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }

    //특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public STOMPChatRoom getChatRoomById(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }

}
