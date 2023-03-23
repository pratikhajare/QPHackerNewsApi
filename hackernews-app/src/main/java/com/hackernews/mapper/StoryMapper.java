package com.hackernews.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.hackernews.dto.StoryDto;
import com.hackernews.entity.Story;

/*
 *  @author Pratik Hajare
 *  
 * */
@Mapper(componentModel = "spring")
public interface StoryMapper {

	Story storyDtoToStoryEntity(StoryDto storyDto);

	@InheritInverseConfiguration(name = "storyDtoToStoryEntity")
	StoryDto storyEntityToStoryDto(Story story);

	List<Story> storyDtoListToStoryEntityList(List<StoryDto> storyDtos);

	@InheritInverseConfiguration(name = "storyDtoListToStoryEntityList")
	List<StoryDto> storyEntityListToStoryDtoList(List<Story> stories);
}
