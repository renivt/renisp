package com.renisp.service.db;

import java.util.ArrayList;
import java.util.List;

import com.renisp.model.User;

public class UserServiceDAO {

	public User getUser(String userEmail) {
		// TODO: Select from User and child tables Friends, subscriptions, blocks
		return null;
	}

	public void updateUser(String userEmail, String friendEmail, String subscription, String block) {
		// TODO: update input values to child tables Friends, subscriptions, blocks
		
	}

	public void insertUser(String userEmail, String friendEmail, String subscription, String block) {
		// TODO: insert values into User and child tables Friends, subscriptions, blocks

	}

	public List<String> getFriends(String userEmail) {
		// TODO: select * from Friends where user='userEmail';
		return null;
	}

	public ArrayList<String> getValidEmails(String sender) {
		// TODO: implement the below logic
		
		/*select user from Friends where friend='userEmail';
		  UNION
		  select user from Subscriptions where subscription='userEmail';
		  MINUS
		  select user from blocks where block='userEmail';
		  */
		return null;
	}

}
