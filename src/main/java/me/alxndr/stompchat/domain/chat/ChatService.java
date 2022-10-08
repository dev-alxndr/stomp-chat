package me.alxndr.stompchat.domain.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

	private final ObjectMapper objectMapper;
	private Map<String, ChatRoom> chatRooms;

	@PostConstruct
	private void init() {
		chatRooms = new LinkedHashMap<>();
	}

	public List<ChatRoom> findAllRoom() {
		return new ArrayList<>(chatRooms.values());
	}

	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}

	public ChatRoom createRoom(String name) {
		final var randomRoomId = UUID.randomUUID().toString();
		final var newChatRoom = ChatRoom.builder()
				.roomId(randomRoomId)
				.name(name)
				.build();
		chatRooms.put(randomRoomId, newChatRoom);

		return newChatRoom;
	}

	public <T> void sendMessage(final WebSocketSession session, final T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}


}
