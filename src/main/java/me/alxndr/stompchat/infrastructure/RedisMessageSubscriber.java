package me.alxndr.stompchat.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.stompchat.domain.chat.ChatMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageSubscriber, MessageListener {

	private final ObjectMapper objectMapper;
	private final RedisTemplate<String, Object> redisTemplate;
	private final SimpMessageSendingOperations messageSendingOperations;


	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		try {
			final var publishedMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
			final var chatMessage = objectMapper.readValue(publishedMessage, ChatMessage.class);

			messageSendingOperations.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
