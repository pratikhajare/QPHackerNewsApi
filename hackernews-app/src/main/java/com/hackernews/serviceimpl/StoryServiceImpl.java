package com.hackernews.serviceimpl;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackernews.dao.StoryRepository;
import com.hackernews.dto.StoryDto;
import com.hackernews.mapper.StoryMapper;
import com.hackernews.service.StoryService;

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

	@Override
	public List<StoryDto> fetchTopStories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoryDto> fetchPastStories() {
		// TODO Auto-generated method stub
		return null;
	}

}
