package com.renisp.model;

import java.util.List;

public class User {
	
	private String name;
	private String email;
	private String requestor;
	private String target;
	private String sender;
	private String text;
	private List<String> friends;
	private List<String> subscriptions;
	private List<String> blocks;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	public List<String> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<String> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public List<String> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<String> blocks) {
		this.blocks = blocks;
	}
	
	
	
}
