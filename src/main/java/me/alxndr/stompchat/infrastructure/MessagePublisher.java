package me.alxndr.stompchat.infrastructure;

import me.alxndr.stompchat.domain.chat.ChatMessage;
import org.springframework.data.redis.listener.ChannelTopic;

public interface MessagePublisher {

	void publish(ChannelTopic topic, ChatMessage chatMessage);
}
