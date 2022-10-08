package me.alxndr.stompchat.domain.chat;

import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChatRoom implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roomId;

	private String name;

	public static ChatRoom create(String name) {
		return ChatRoom.builder()
				.roomId(UUID.randomUUID().toString())
				.name(name)
				.build();
	}

}
