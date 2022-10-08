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
import me.alxndr.stompchat.infrastructure.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

	private final ChatRoomRepository chatRoomRepository;
	public List<ChatRoom> findAllRoom() {
		return chatRoomRepository.findAllRoom();
	}

	public ChatRoom findRoomById(String roomId) {
		return chatRoomRepository.findRoomById(roomId);
	}

	public ChatRoom createRoom(String name) {
		return chatRoomRepository.createChatRoom(name);
	}


}
