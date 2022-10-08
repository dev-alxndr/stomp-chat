package me.alxndr.stompchat.interfaces.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class WebsocketHandler extends TextWebSocketHandler {

	@Override protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
		final var payload = message.getPayload();
		log.info("payload : {}", payload);
		final var textMessage = new TextMessage("Welcome Alxndr's Chatting");
		session.sendMessage(textMessage);

	}
}
