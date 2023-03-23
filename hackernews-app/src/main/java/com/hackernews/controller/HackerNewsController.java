package com.hackernews.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackernews.constants.ApiConstants;
import com.hackernews.constants.MessageConstants;
import com.hackernews.dto.CommentDto;
import com.hackernews.dto.ResponseDto;
import com.hackernews.dto.StoryDto;
import com.hackernews.dto.UserDto;
import com.hackernews.service.CommentService;
import com.hackernews.service.StoryService;
import com.hackernews.service.UserService;

/*
 *  @author Pratik Hajare
 *  
 * */

@RestController
@RequestMapping(value = ApiConstants.API_HACKER_NEWS)
public class HackerNewsController {

	@Autowired
	UserService userService;

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
	 * Adds a new User
	 *
	 * @param UserDto
	 * @return Saved UserDto as data in ResponseDto
	 */
	@PostMapping(ApiConstants.API_HACKER_NEWS_ADD_USER)
	public ResponseDto addUser(@RequestBody @Valid UserDto userDto) {
		UserDto savedUser = userService.addUser(userDto);
		if (savedUser != null) {
			return new ResponseDto(true, savedUser, MessageConstants.RECORD_ADDED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_ALREADY_EXISTS);
	}

	/**
	 * Adds a list of Stories
	 *
	 * @param List<StoryDto>
	 * @return Saved StoryDtos List as data in ResponseDto
	 */
	@PostMapping(ApiConstants.API_HACKER_NEWS_ADD_STORIES)
	public ResponseDto addStories(@RequestBody @Valid List<StoryDto> stories) {
		List<StoryDto> savedStories = storyService.addStories(stories);
		if (savedStories != null && !savedStories.isEmpty()) {
			return new ResponseDto(true, savedStories, MessageConstants.RECORD_ADDED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_ALREADY_EXISTS);
	}

	/**
	 * Adds a list of Comments
	 *
	 * @param List<CommentDto>
	 * @return Saved CommentDtos List as data in ResponseDto
	 */
	@PostMapping(ApiConstants.API_HACKER_NEWS_ADD_COMMENTS)
	public ResponseDto addComments(@RequestBody @Valid List<CommentDto> comments) {
		List<CommentDto> savedComments = commentService.addComments(comments);
		if (savedComments != null && !savedComments.isEmpty()) {
			return new ResponseDto(true, savedComments, MessageConstants.RECORD_ADDED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_ALREADY_EXISTS);
	}

	/**
	 * Fetches list of top 10 stories ranked by the score in the last 15 minutes and
	 * returns same stories till 15 mins.
	 *
	 * @return StoryDtos List as data in ResponseDto
	 */
	@GetMapping(ApiConstants.API_HACKER_NEWS_GET_TOP_STORIES)
	public ResponseDto fetchTopTenStories() {
		List<StoryDto> stories = storyService.fetchTopStories();
		if (stories != null && !stories.isEmpty()) {
			return new ResponseDto(true, stories, MessageConstants.RECORD_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_DOES_NOT_EXISTS);
	}

	/**
	 * Fetches list of past stories served by /topStories above api.
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
	 */
	@GetMapping(ApiConstants.API_HACKER_NEWS_GET_TOP_COMMENTS_FOR_STORY)
	public ResponseDto fetchTopComments(@RequestParam String storyIdentifier) {
		List<CommentDto> topComments = commentService.fetchTopComments(storyIdentifier);
		if (topComments != null && !topComments.isEmpty()) {
			return new ResponseDto(true, topComments, MessageConstants.RECORD_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORD_DOES_NOT_EXISTS);
	}
}
