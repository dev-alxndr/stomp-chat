package me.alxndr.stompchat.interfaces.handler.controller;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import me.alxndr.stompchat.domain.chat.ChatService;
import me.alxndr.stompchat.infrastructure.ChatRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

	private final ChatService chatService;


	// Chatting List View
	@GetMapping("/room")
	public String rooms() {
		return "/chat/room";
	}

	// All Chatting List API
	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> chatRooms() {
		return chatService.findAllRoom();
	}

	// Create Chatting Room API
	@PostMapping("/room")
	@ResponseBody
	public ResponseEntity<ChatRoom> createRoom(@RequestParam String name) {
		final var room = chatService.createRoom(name);
		return ResponseEntity.ok(room);
	}

	// Enter Chatting Room View
	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {
		model.addAttribute("roomId", roomId);
		return "/chat/roomDetail";
	}

	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ResponseEntity<ChatRoom> getRoom(@PathVariable String roomId) {
		final var chatRoom = chatService.findRoomById(roomId);
		return ResponseEntity.ok(chatRoom);
	}
}
