package me.alxndr.stompchat.interfaces.handler.controller;

import lombok.RequiredArgsConstructor;
import me.alxndr.stompchat.domain.chat.ChatMessage;
import me.alxndr.stompchat.domain.chat.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

	private final SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/chat/message")
	public void message(ChatMessage chatMessage) {
		if (chatMessage.getType() == MessageType.ENTER) {
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
		}
		messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
	}
}
