package com.hackernews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hackernews.constants.ApiConstants;
import com.hackernews.constants.MessageConstants;
import com.hackernews.dto.CommentDto;
import com.hackernews.dto.ResponseDto;
import com.hackernews.dto.StoryDto;
import com.hackernews.service.CommentService;
import com.hackernews.service.StoryService;

/*
 *  @author Pratik Hajare
 *  
 * */

@RestController
public class HackerNewsController {

	@Autowired
	StoryService storyService;

	@Autowired
	CommentService commentService;

	/*
	 * Some additional Comments 1.Using Swagger and Postman for API testing. 
	 * 2. Using 3 tier Architecture - Controller, Service, Repository
	 * 3. Database Used - MYSQL
	 * 4. Hazelcast cache is Used.
	 * 
	 */
	
	/**
	 * Fetches list of top 10 stories ranked by the score in the last 15 minutes and
	 * returns same stories till 15 mins.
	 *
	 * @return StoryDtos List as data in ResponseDto
	 * @throws Exception 
	 */
	@GetMapping(ApiConstants.API_HACKER_NEWS_GET_TOP_STORIES)
	public ResponseDto fetchTopTenStories() throws Exception {
		List<StoryDto> stories = storyService.fetchTopTenStories();
		if (stories != null && !stories.isEmpty()) {
			return new ResponseDto(true, stories, MessageConstants.RECORD_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_DOES_NOT_EXISTS);
	}

	/**
	 * Fetches list of past stories served by /topStories i.e above api.
	 *
	 * @return StoryDtos List as data in ResponseDto
	 */
	@GetMapping(ApiConstants.API_HACKER_NEWS_GET_PAST_STORIES)
	public ResponseDto fetchPastStories() {
		List<StoryDto> pastStories = storyService.fetchPastStories();
		if (pastStories != null && !pastStories.isEmpty()) {
			return new ResponseDto(true, pastStories, MessageConstants.RECORD_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_DOES_NOT_EXISTS);
	}

	/**
	 * Fetches list of top 10 comments on a given story sorted by a total number of
	 * child comments.
	 *
	 * @return CommentDtos List as data in ResponseDto
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	@GetMapping(ApiConstants.API_HACKER_NEWS_GET_TOP_COMMENTS_FOR_STORY)
	public ResponseDto fetchTopComments(@RequestParam Integer storyIdentifier) throws JsonMappingException, JsonProcessingException {
		List<CommentDto> topComments = commentService.fetchTopComments(storyIdentifier);
		if (topComments != null && !topComments.isEmpty()) {
			return new ResponseDto(true, topComments, MessageConstants.RECORD_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_DOES_NOT_EXISTS);
	}
}
