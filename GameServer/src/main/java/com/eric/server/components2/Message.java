package com.eric.server.components2;


public abstract class Message {
	
	protected String op = "";
	protected String sessionId;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Message(String op) {
		this.op = op;
	}
	
	public String getOp() {
		return op;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
