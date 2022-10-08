package me.alxndr.stompchat.interfaces.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.stompchat.domain.chat.ChatMessage;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import me.alxndr.stompchat.domain.chat.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebsocketHandler extends TextWebSocketHandler {

	private final ObjectMapper objectMapper;
	private final ChatService chatService;

	@Override protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
		final var payload = message.getPayload();
		log.info("payload : {}", payload);

		final var chatMessage = objectMapper.readValue(payload, ChatMessage.class);
		final var chatRoom = chatService.findRoomById(chatMessage.getRoomId());
		chatRoom.handleActions(session, chatMessage, chatService);
	}
}
