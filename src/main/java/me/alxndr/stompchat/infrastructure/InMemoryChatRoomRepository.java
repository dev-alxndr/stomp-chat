package me.alxndr.stompchat.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;


public class InMemoryChatRoomRepository implements ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;

	@PostConstruct
	private void init() {
		chatRoomMap = new LinkedHashMap<>();
	}

	@Override
	public List<ChatRoom> findAllRoom() {
		final var chatRooms = new ArrayList<ChatRoom>(chatRoomMap.values());
		Collections.reverse(chatRooms);
		return chatRooms;
	}

	@Override
	public ChatRoom findRoomById(String roomId) {
		return chatRoomMap.get(roomId);
	}

	@Override
	public ChatRoom createChatRoom(String name) {
		final var newChatRoom = ChatRoom.create(name);
		chatRoomMap.put(newChatRoom.getRoomId(), newChatRoom);
		return newChatRoom;
	}

	@Override
	public void enterChatRoom(final String roomId) {
		// Do Nothing
	}

	@Override public ChannelTopic getTopic(final String roomId) {
		return null;
	}


}
