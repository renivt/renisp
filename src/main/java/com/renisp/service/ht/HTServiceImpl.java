package com.renisp.service.ht;

import java.util.*;

import org.springframework.stereotype.Service;

import com.renisp.interfaces.UserService;
import com.renisp.model.Result;
import com.renisp.model.User;

@Service("HTServiceImpl")
public class HTServiceImpl implements UserService {
	
	private static HashMap<String, User> userRecords = new HashMap<String, User>();
	
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
			User user = userRecords.get(userEmail);
			//If user exists and friend exists then add to its friends list 
			if(user!=null && !user.getFriends().contains(friendEmail))
				user.getFriends().add(friendEmail);
			//If user exists and friends list does not exist then initialize and add to its friends list
			else if(user!=null && user.getFriends()==null){
				user.setFriends(new ArrayList<String>());
				user.getFriends().add(friendEmail);
			}
			// else add new user with friends list 
			else
			{
				User freshUser = new User();
				freshUser.setEmail(userEmail);
				freshUser.setFriends(new ArrayList<String>());
				freshUser.getFriends().add(friendEmail);
				userRecords.put(userEmail, freshUser);
			}
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
			//If user exists retrieve the friends list.
			User user = userRecords.get(userEmail);
			if(user!=null)
			{
				result.setFriends(user.getFriends());
				result.setSuccess(true);
				result.setCount(user.getFriends().size());
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
			//Retrieve the friends list of both users and find the common friends
			User user = userRecords.get(userEmail);
			User friend = userRecords.get(friendEmail);
			if(user!=null && friend!=null && user.getFriends()!=null && friend.getFriends()!=null)
			{
				List<String> common = new ArrayList<String>(user.getFriends());
				common.retainAll(friend.getFriends());
				result.setFriends(common);
				result.setSuccess(true);
				result.setCount(common.size());
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
			User user = userRecords.get(userEmail);
			//If user exists and subscription exist then add to its subscription list
			if(user!=null && user.getSubscriptions()!=null && !user.getSubscriptions().contains(subscribedEmail)){
				user.getSubscriptions().add(subscribedEmail);
			}
			//If user exists and subscription does not exist then initialize and add to its subscription list
			else if(user!=null && user.getSubscriptions()==null){
				user.setSubscriptions(new ArrayList<String>());
				user.getSubscriptions().add(subscribedEmail);
			}
			// else add new user with subscriptions
			else
			{
				User freshUser = new User();
				freshUser.setEmail(userEmail);
				freshUser.setSubscriptions(new ArrayList<String>());
				freshUser.getSubscriptions().add(subscribedEmail);
				userRecords.put(userEmail, freshUser);
			}
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
			User user = userRecords.get(userEmail);
			//If user exists and blocks exist then add to its block list
			if(user!=null && user.getBlocks()!=null && !user.getBlocks().contains(block)){
				user.getBlocks().add(block); 
			}
			//If user exists and block list does not exist then initialize and add to its block list
			else if(user!=null && user.getBlocks()==null){
				user.setBlocks(new ArrayList<String>());
				user.getBlocks().add(block);
			}
			else
			{
				User freshUser = new User();
				freshUser.setEmail(userEmail);
				freshUser.setBlocks(new ArrayList<String>());
				freshUser.getBlocks().add(block);
				userRecords.put(userEmail, freshUser);
			}
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
		    Set<String> keys = userRecords.keySet();
		    
		    //Obtaining iterator over set entries
		    Iterator<String> itr = keys.iterator();
		    
		    //Remove users who have blocked and add users who have subscribed
			while(itr.hasNext())
			{
				
				String userMail = itr.next();
				User user = (User)userRecords.get(userMail);
				if(user.getBlocks()!=null && user.getBlocks().contains(sender)) continue;
				if(user.getSubscriptions()!=null && user.getSubscriptions().contains(sender)) 
					result.getRecipients().add(userMail);
				if(user.getFriends()!=null && user.getFriends().contains(sender)) 
					result.getRecipients().add(userMail);
			}
			//Add @User found in the text
			String[] arrStr = text.split(" ");
		    for(String val: arrStr)
		    {
		    	if(val.contains("@"))
		    		result.getRecipients().add(val);
		    }

				result.setSuccess(true);
				//result.setCount(result.getRecipients().size());
		}
		
		
		
		
			
		return result;
	}
	
}
