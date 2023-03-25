package com.hackernews.service;

import java.util.List;

import com.hackernews.dto.StoryDto;

/*
 *  @author Pratik Hajare
 *  
 * */
public interface StoryService {

	List<StoryDto> fetchPastStories();
	
	public List<StoryDto> fetchTopTenStories() throws Exception; 

}
