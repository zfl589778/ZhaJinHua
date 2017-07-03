package com.eric.server.components2;

public interface MessageHandler {
	public Message deserializer(String strMsg);

	public String serializer(Message objMsg);
}
