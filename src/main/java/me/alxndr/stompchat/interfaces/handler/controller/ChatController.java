package me.alxndr.stompchat.interfaces.handler.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import me.alxndr.stompchat.domain.chat.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;

	@PostMapping
	public ChatRoom createRoom(@RequestParam String name) {
		return chatService.createRoom(name);
	}

	@GetMapping
	public List<ChatRoom> findAllRoom() {
		return chatService.findAllRoom();
	}

}
