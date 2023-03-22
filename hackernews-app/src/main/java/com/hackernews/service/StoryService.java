package com.hackernews.service;

import java.util.List;

import javax.validation.Valid;

import com.hackernews.dto.StoryDto;

public interface StoryService {

	List<StoryDto> addStories(@Valid List<StoryDto> stories);

	List<StoryDto> fetchTopStories();

	List<StoryDto> fetchPastStories();

}
