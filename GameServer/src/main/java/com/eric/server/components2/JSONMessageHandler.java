package com.eric.server.components2;

import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

public class JSONMessageHandler implements JsonDeserializer<Message>, MessageHandler {
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static final String MessagePakcage = "com.cagayan.message";

	public Message deserializer(String strMsg) {
		logger.trace("Message Deserializer : " + strMsg);
		JsonReader reader = new JsonReader(new StringReader(strMsg));
		reader.setLenient(true);
		return GsonAmpleMessageHelper.getGson().fromJson(reader, Message.class);
	}

	public String serializer(Message objMsg) {
		String ret = GsonAmpleMessageHelper.getGson().toJson(objMsg);
		logger.trace("Message Serializer : " + ret);
		return ret;
	}

	private static HashMap<String, Class<? extends Message>> messageMap = new HashMap<String, Class<? extends Message>>();

	@Override
	public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String op = json.getAsJsonObject().get("op").getAsString();
		if (op == null || op.isEmpty()) {
			logger.warn("Message Format Error");
			return null;
		}
		Class<? extends Message> type = getType(op);
		if (type != null && !type.getSimpleName().equalsIgnoreCase("NullMessageType")) {
			return context.deserialize(json, type);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private synchronized Class<? extends Message> getType(String op) {
		Class<? extends Message> mstType = messageMap.get(op);
		if (mstType == null) {
			Class<?> type = ClassUtilities.getClass(MessagePakcage, op);
			try {
				mstType = (Class<? extends Message>) type;
			} catch (ClassCastException ignore) {
			}
			if (mstType != null) {
				messageMap.put(op, mstType);
			}
		}
		return mstType;
	}

}
