package com.renisp;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.renisp.model.Result;
import com.renisp.model.User;
import com.renisp.interfaces.UserService;

@SpringBootApplication(scanBasePackages = { "com.renisp" })
@RestController
public class Application {
	
	@Autowired
	@Qualifier("HTServiceImpl")
	UserService userService;

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }
    
	@RequestMapping(value="/test", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Result test() {
		Result result = new Result();
	    result.setSuccess(true);
    
	    return result;
	}
	
	@RequestMapping(value="/createFriends", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result createFriends(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	userService.addFriend(user);
	    	result.setSuccess(true);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}

	
	@RequestMapping(value="/retrieveFriends", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result retrieveFriends(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	result = userService.retrieveFriends(user);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}
	
	@RequestMapping(value="/retrieveCommonFriends", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result retrieveCommonFriends(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	result = userService.retrieveCommonFriends(user);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}
	
	@RequestMapping(value="/subscribe", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result subscribe(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	result = userService.subscribe(user);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}
	
	@RequestMapping(value="/block", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result block(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	result = userService.block(user);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}
	
	@RequestMapping(value="/validEmails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Result validEmails(@RequestBody String json) {
		Result result = new Result();
	    try
	    {
	    	User user = new ObjectMapper().readValue(json, User.class);
	    	result = userService.validEmails(user);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	result.setSuccess(false);
	    }
	    
	    return result;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
