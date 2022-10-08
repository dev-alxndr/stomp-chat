package me.alxndr.stompchat.config;

import lombok.RequiredArgsConstructor;
import me.alxndr.stompchat.interfaces.handler.WebsocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

	private final WebsocketHandler websocketHandler;

	@Override
	public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
		registry.addHandler(websocketHandler, "/ws/chat")
				.setAllowedOrigins("*");
	}
}
