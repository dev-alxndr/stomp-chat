package me.alxndr.stompchat.interfaces.handler.controller;

import lombok.RequiredArgsConstructor;
import me.alxndr.stompchat.domain.chat.ChatMessage;
import me.alxndr.stompchat.domain.chat.ChatService;
import me.alxndr.stompchat.domain.chat.MessageType;
import me.alxndr.stompchat.infrastructure.MessagePublisher;
import me.alxndr.stompchat.infrastructure.RedisMessagePublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

	private final MessagePublisher redisMessagePublisher;
	private final ChatService chatService;

	@MessageMapping("/chat/message")
	public void message(ChatMessage chatMessage) {
		if (chatMessage.getType() == MessageType.ENTER) {
			chatService.enterChatRoom(chatMessage.getRoomId());
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
		}

		redisMessagePublisher.publish(chatService.getTopic(chatMessage.getRoomId()), chatMessage);
	}
}
