package hello.springs.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//채팅 화면(view) 용도로 분리
//html
//json -> js로 동적 표시
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;


    @GetMapping("/room") //get - dlfmf직접 사용자가 경로 접근, 링크 접속 시 기본 동작
    //채팅방 목록 화면
    public String rooms(Model model){
        return "/chat/room";
    }

    //모든 채팅방 목록 반환 - json(js 동적 표시 대상)
    @GetMapping("/rooms")
    @ResponseBody //HTTP 응답 본문에 json 포함
    public List<STOMPChatRoom> room(){
        return chatRoomRepository.findAllRoom();
    }

    //채팅방 생성
    @PostMapping("/room") //post - 직접 사용자가 /room으로 접근 x, 폼 제출의 경우 전송되는 정보에 대한 매핑
    @ResponseBody
    public STOMPChatRoom createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }

    //채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    //특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public STOMPChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }

}
