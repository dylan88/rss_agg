package com.dylan.rss_agg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dylan.rss_agg.model.Feed;
import com.dylan.rss_agg.model.User;
import com.dylan.rss_agg.service.UserService;

@Controller
public class ApiController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/api/register", method = RequestMethod.GET)
	public @ResponseBody String register(@RequestParam Map<String,String> requestParams) {
	   String userName=requestParams.get("email");
	   String password=requestParams.get("password");
	   User userExists = userService.findUserByEmail(userName);
	   if (userExists != null) {
		   return "false";
	   }
	   User user = new User();
	   user.setEmail(userName);
	   user.setPassword(password);
	   userService.saveUser(user);
	   return Integer.toString(user.getId());
	}
	
	@RequestMapping(value = "/api/login", method = RequestMethod.GET)
	public @ResponseBody String login(@RequestParam Map<String,String> requestParams) {
	   String userName=requestParams.get("email");
	   String password=requestParams.get("password");
	   User user = userService.findUserByEmail(userName);
	   if (user == null) {
		   return "false";
	   }
	   return Integer.toString(user.getId());
	}
	
	@RequestMapping(value = "/api/feed/add", method = RequestMethod.GET)
	public @ResponseBody String addFeed(@RequestParam Map<String,String> requestParams) {
	   String userId=requestParams.get("user_id");
	   String url=requestParams.get("url");
	   Feed feed = new Feed();
	   feed.setUrl(url);
	   return "true";
	}
}
