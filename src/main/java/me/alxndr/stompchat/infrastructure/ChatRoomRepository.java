package me.alxndr.stompchat.infrastructure;

import java.util.List;
import me.alxndr.stompchat.domain.chat.ChatRoom;

public interface ChatRoomRepository {

	List<ChatRoom> findAllRoom();

	ChatRoom findRoomById(String roomId);

	ChatRoom createChatRoom(String name);
}
