package me.alxndr.stompchat.domain.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

	private MessageType type;

	private String roomId;

	private String sender;

	private String message;

}
