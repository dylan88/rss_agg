package com.dylan.rss_agg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dylan.rss_agg.model.Feed;

@Service("feedService")
public class FeedServiceImpl implements FeedService{
	
	public void saveFeed(Feed feed) {
		feed.setUrl(feed.getUrl());
	}
}
