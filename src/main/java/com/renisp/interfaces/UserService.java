package com.renisp.interfaces;

import java.util.*;

import org.springframework.stereotype.Service;

import com.renisp.model.Result;
import com.renisp.model.User;

public interface UserService {
	
	public Result addFriend(User requestUser);

	public Result retrieveFriends(User requestUser);
	
	public Result retrieveCommonFriends(User requestUser);
	
	public Result subscribe(User requestUser);
	
	public Result block(User requestUser);
	
	public Result validEmails(User requestUser);
	
}
