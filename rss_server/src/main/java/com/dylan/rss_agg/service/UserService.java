package com.dylan.rss_agg.service;

import com.dylan.rss_agg.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}