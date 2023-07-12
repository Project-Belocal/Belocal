package kr.co.belocal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.service.ChatRoomService;

import java.util.Map;

@RestController("apiChatRoomController")
@RequestMapping("/api/chatRooms")
public class ChatRoomController {
    
    @Autowired
    private ChatRoomService service;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ChatRoom chatRoom) {
        int result = service.append(chatRoom);
        
        if(result == 1) 
            return new ResponseEntity<Object> (chatRoom, HttpStatus.OK);

        return new ResponseEntity<Object> (null, HttpStatus.BAD_REQUEST);
    }




}
