package me.alxndr.stompchat.infrastructure;

import java.util.List;
import me.alxndr.stompchat.domain.chat.ChatRoom;
import org.springframework.data.redis.listener.ChannelTopic;

public interface ChatRoomRepository {

	List<ChatRoom> findAllRoom();

	ChatRoom findRoomById(String roomId);

	ChatRoom createChatRoom(String name);

	void enterChatRoom(String roomId);

	ChannelTopic getTopic(String roomId);
}
