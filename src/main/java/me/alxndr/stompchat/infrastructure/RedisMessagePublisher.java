package me.alxndr.stompchat.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.stompchat.domain.chat.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisMessagePublisher implements MessagePublisher {

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public void publish(ChannelTopic topic, ChatMessage chatMessage) {
		redisTemplate.convertAndSend(topic.getTopic(), chatMessage);
	}

}
