package com.eric.server.components2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonAmpleMessageHelper {
	// private static org.slf4j.Logger logger = org.slf4j.LoggerFactory
	// .getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static Gson gson;

	public static synchronized Gson getGson() {
		if (gson != null) {
			return gson;
		}

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Message.class, new JSONMessageHandler());

		gson = builder.create();
		return gson;
	}
}
