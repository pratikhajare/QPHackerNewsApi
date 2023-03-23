package com.hackernews.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackernews.dao.StoryRepository;
import com.hackernews.dto.StoryDto;
import com.hackernews.entity.Story;
import com.hackernews.mapper.StoryMapper;
import com.hackernews.service.StoryService;

@CacheConfig(cacheNames = "topTenStories")
@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	StoryMapper storyMapper;

	@Override
	public List<StoryDto> addStories(@Valid List<StoryDto> stories) {
		if (stories != null && !stories.isEmpty()) {
			return storyMapper.storyEntityListToStoryDtoList(
					storyRepository.saveAll(storyMapper.storyDtoListToStoryEntityList(stories)));
		}
		return Collections.emptyList();
	}

	@Cacheable(key = "'topStories'")
	@Override
	public List<StoryDto> fetchTopStories() {
		List<Story> topStories = storyRepository.fetchTopTenStoriesOfLast15Mins();
		if (topStories != null && !topStories.isEmpty()) {
			updateViewedStories(topStories.stream().map(Story::getStoryId).collect(Collectors.toList()));
			return storyMapper.storyEntityListToStoryDtoList(topStories);
		}
		return Collections.emptyList();
	}

	//updates viewed status as 'Y' inorder to fetch paststories
	public void updateViewedStories(List<String> storyIndentifierList) {
		if (storyIndentifierList != null && !storyIndentifierList.isEmpty()) {
				storyRepository.updateViewedStories(storyIndentifierList);
		}
	}

	@Override
	public List<StoryDto> fetchPastStories() {
		List<Story> pastStories = storyRepository.fetchPastViewedStories();
		if (pastStories != null && !pastStories.isEmpty()) {
			return storyMapper.storyEntityListToStoryDtoList(pastStories);
		}
		return Collections.emptyList();
	}

}
