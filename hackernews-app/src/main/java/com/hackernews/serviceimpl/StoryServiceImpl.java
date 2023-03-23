package com.hackernews.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hackernews.dao.StoryRepository;
import com.hackernews.dto.StoryDto;
import com.hackernews.entity.Story;
import com.hackernews.mapper.StoryMapper;
import com.hackernews.service.StoryService;

/*
 *  @author Pratik Hajare
 *  
 * */

@CacheConfig(cacheNames = "topTenStories")
@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	StoryMapper storyMapper;

	/**
	 * Adds Stories
	 */
	@Override
	public List<StoryDto> addStories(@Valid List<StoryDto> stories) {
		if (stories != null && !stories.isEmpty()) {
			return storyMapper.storyEntityListToStoryDtoList(
					storyRepository.saveAll(storyMapper.storyDtoListToStoryEntityList(stories)));
		}
		return Collections.emptyList();
	}

	/**
	 * Returns top 10 stories in the last 15 mins sorted by score. 
	 * Caching the response to view same stories for 15 mins. 
	 */
	@Cacheable(key = "'topStories'",unless="#result == null")
	@Override
	public List<StoryDto> fetchTopStories() {
		List<Story> topStories = storyRepository.fetchTopTenStoriesOfLast15Mins();
		if (topStories != null && !topStories.isEmpty()) {
			
			//updates all fetched stories viewed status to 'Y'
			updateViewedStories(topStories.stream().map(Story::getStoryId).collect(Collectors.toList()));
			return storyMapper.storyEntityListToStoryDtoList(topStories);
		}
		return null;
	}

	/*
	 *  updates viewed status as 'Y' of stories 
	 *  inorder to fetch paststories 
	 *  */
	public void updateViewedStories(List<String> storyIndentifierList) {
		if (storyIndentifierList != null && !storyIndentifierList.isEmpty()) {
				storyRepository.updateViewedStories(storyIndentifierList);
		}
	}

	/**
	 * Fetches all past stories viewed as top 10 stories 
	 * i.e having viewed switch = 'Y'
	 */
	@Override
	public List<StoryDto> fetchPastStories() {
		List<Story> pastStories = storyRepository.fetchPastViewedStories();
		if (pastStories != null && !pastStories.isEmpty()) {
			return storyMapper.storyEntityListToStoryDtoList(pastStories);
		}
		return Collections.emptyList();
	}

}
