package me.alxndr.stompchat.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RedisChatRoomRepository implements ChatRoomRepository {

	private final RedisMessageListenerContainer redisMessageListenerContainer;
	private final MessageSubscriber redisMessageSubscriber;
	private final static String CHAT_ROOMS = "CHAT_ROOM";
	private final RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, ChatRoom> opsHashChatRoom;

	private Map<String, ChannelTopic> topics;

	@PostConstruct
	public void init() {
		opsHashChatRoom = redisTemplate.opsForHash();
		topics = new HashMap<>();
	}

	@Override
	public List<ChatRoom> findAllRoom() {
		return opsHashChatRoom.values(CHAT_ROOMS);
	}

	@Override
	public ChatRoom findRoomById(final String roomId) {
		return opsHashChatRoom.get(CHAT_ROOMS, roomId);
	}

	@Override
	public ChatRoom createChatRoom(final String name) {
		final var newChatRoom = ChatRoom.create(name);
		opsHashChatRoom.put(CHAT_ROOMS, newChatRoom.getRoomId(), newChatRoom);
		return newChatRoom;
	}

	@Override
	public void enterChatRoom(String roomId) {
		final var channelTopic = topics.get(roomId);
		if (Objects.isNull(channelTopic)) {
			final var newTopic = new ChannelTopic(roomId);
			redisMessageListenerContainer.addMessageListener(redisMessageSubscriber, newTopic);
			topics.put(roomId, newTopic);
		}
	}

	@Override
	public ChannelTopic getTopic(String roomId) {
		return topics.get(roomId);
	}

}
