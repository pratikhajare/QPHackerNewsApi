package com.hackernews.serviceimpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hackernews.dao.StoryRepository;
import com.hackernews.dto.StoryDto;
import com.hackernews.mapper.StoryMapper;
import com.hackernews.service.StoryService;
import com.hackernews.service.utils.HackerNewsUtilsServiceImpl;

/*
 *  @author Pratik Hajare
 *  
 * */
@CacheConfig(cacheNames = "topTenStories")
@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	StoryMapper storyMapper;

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	HackerNewsUtilsServiceImpl hackerNewsUtilsServiceImpl;

	/**
	 * ̥ Returns top 10 stories in the last 15 mins sorted by score. Caching the
	 * response to view same stories for 15 mins.
	 */
	@Cacheable(key = "'topStories'", unless = "#result == null")
	@Override
	public List<StoryDto> fetchTopTenStories() throws JsonMappingException, JsonProcessingException {
		List<Integer> topStoriesIds = hackerNewsUtilsServiceImpl.fetchTopStories(10);
		if (topStoriesIds != null && !topStoriesIds.isEmpty()) {
			List<StoryDto> resultStories = new ArrayList<>();
			for (Integer storyId : topStoriesIds) {
				StoryDto story = hackerNewsUtilsServiceImpl
						.convertToStory(hackerNewsUtilsServiceImpl.fetchItemById(storyId));
				if (story != null) {
					resultStories.add(story);
				}
			}
			saveViewedStories(resultStories);
			return resultStories.stream().sorted(Comparator.comparing(StoryDto::getScore, Comparator.reverseOrder()))
					.collect(Collectors.toList());
		}
		return null;
	}

	/*
	 * saves all top stories accessed by user in database
	 */
	public void saveViewedStories(List<StoryDto> stories) {
		if (stories != null && !stories.isEmpty()) {
			// saving all top stories in database
			storyRepository.saveAll(storyMapper.storyDtoListToStoryEntities(stories));
		}
	}

	/**
	 * Fetches all past stories viewed as top 10 stories
	 *
	 */
	@Override
	public List<StoryDto> fetchPastStories() {
		return storyMapper.storyEntitiesToStoryDtos(storyRepository.findAll());
	}

}
