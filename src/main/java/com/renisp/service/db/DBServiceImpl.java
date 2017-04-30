/* This class is not used. Its only partially implemented. Its created for Demo purpose of an alternate implementation */

package com.renisp.service.db;

import java.util.*;

import org.springframework.stereotype.Service;

import com.renisp.interfaces.UserService;
import com.renisp.model.Result;
import com.renisp.model.User;

/* This class is another implementation of the UserService which stores user records in the DB.
It accesses USER, FRIENDS, SUBSCRIBER, BLOCK tables to persist the required data.
*/
@Service("DBServiceImpl")
public class DBServiceImpl implements UserService {
	
	UserServiceDAO dao;
	
	public Result addFriend(User requestUser)
	{
		String userEmail=requestUser.getFriends().get(0);
		String friendEmail=requestUser.getFriends().get(1);
		Result result = new Result();
		
		if(userEmail==null || friendEmail==null)
		{// If input validation fails
			result.setSuccess(false);
		}
		else
		{// If input validation is successful
			User user = dao.getUser(userEmail); 
			//If user exists add to its friends list else add new user.
			if(user!=null && !user.getFriends().contains(friendEmail))
				dao.updateUser(userEmail,friendEmail,null,null);
			else
				dao.insertUser(userEmail,friendEmail,null,null);

			result.setSuccess(true);
		}
		
		return result;			

	}

	public Result retrieveFriends(User requestUser)
	{
		String userEmail=requestUser.getEmail();
		Result result = new Result();
		
		if(userEmail==null)
		{// Input Validation
			result.setSuccess(false);
		}
		else
		{// Processing if Valid Input
			List<String> friends = dao.getFriends(userEmail);
			if(friends!=null)
			{
				result.setFriends(friends);
				result.setSuccess(true);
				result.setCount(friends.size());
			}
			else
				result.setSuccess(false);				
		}
			
		return result;
	}
	
	public Result retrieveCommonFriends(User requestUser)
	{
		String userEmail=requestUser.getFriends().get(0);
		String friendEmail=requestUser.getFriends().get(1);
		Result result = new Result();
		
		if(userEmail==null || friendEmail==null)
		{// Input Validation
			result.setSuccess(false);
			
		}
		else
		{// Processing if Valid Input
			User user = dao.getUser(userEmail);;
			User friend = dao.getUser(friendEmail);;
			if(user!=null && friend!=null && user.getFriends()!=null && friend.getFriends()!=null)
			{
				List<String> common = user.getFriends();
				common.retainAll(friend.getFriends());
				result.setFriends(common);
				result.setSuccess(true);
				result.setCount(user.getFriends().size());
			}
			else
				result.setSuccess(false);				
		}
			
		return result;
	}
	
	public Result subscribe(User requestUser)
	{
		String userEmail=requestUser.getRequestor();
		String subscribedEmail=requestUser.getTarget();
		Result result = new Result();
		
		if(userEmail==null || subscribedEmail==null)
		{// Input Validation
			result.setSuccess(false);
		}
		else
		{// Processing if Valid Input

			User user = dao.getUser(userEmail); 
			//If user exists add to its friends list else add new user.
			if(user!=null && !user.getFriends().contains(subscribedEmail))
				dao.updateUser(userEmail,null,subscribedEmail,null);
			else
				dao.insertUser(userEmail,null,subscribedEmail,null);
			result.setSuccess(true);				
		}
			
		return result;
	}
	
	public Result block(User requestUser)
	{
		String userEmail=requestUser.getRequestor();
		String block=requestUser.getTarget();
		Result result = new Result();
		
		if(userEmail==null || block==null)
		{// Input Validation
			result.setSuccess(false);
		}
		else
		{// Processing if Valid Input
			User user = dao.getUser(userEmail); 
			//If user exists add to its friends list else add new user.
			if(user!=null && !user.getFriends().contains(block))
				dao.updateUser(userEmail,null,null,block);
			else
				dao.insertUser(userEmail,null,null,block);
			result.setSuccess(true);				
		}
			
		return result;
	}
	
	public Result validEmails(User requestUser)
	{
		String sender=requestUser.getSender();
		String text=requestUser.getText();
		Result result = new Result();
		result.setRecipients(new ArrayList<String>());
		
		if(sender==null || text==null)
		{// Input Validation
			result.setSuccess(false);
		}
		else
		{// Processing if Valid Input

			
			result.getRecipients().addAll(dao.getValidEmails(sender));
			
			//Add @User found in the text
			String[] arrStr = text.split(" ");
		    for(String val: arrStr)
		    {
		    	if(val.contains("@"))
		    		result.getRecipients().add(val);
		    }

				result.setSuccess(true);
				result.setCount(result.getRecipients().size());
		}
		
		
		
		
			
		return result;
	}
	
}
