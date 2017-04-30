package com.renisp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Result {
	
	private Boolean success;
	private List<String> friends;
	private List<String> recipients;
	private Integer count;
	
	public Boolean isSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
