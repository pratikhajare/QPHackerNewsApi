package com.hackernews.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackernews.dto.StoryDto;
import com.hackernews.entity.Story;
/*
 *  @author Pratik Hajare
 *  
 * */
@Mapper(componentModel = "spring")
public interface StoryMapper {

	//@Mapping(source = "storyDto.kids", target = "kids", qualifiedByName = "convertKidsListToMap")
	Story storyDtoToStoryEntity(StoryDto storyDto);
	
	@InheritInverseConfiguration(name = "storyDtoToStoryEntity")
	//@Mapping(source = "story.kids", target = "kids", qualifiedByName = "convertKidsMapToList")
	StoryDto storyEntityToStoryDto(Story story);
	
	
	List<Story> storyDtoListToStoryEntities(List<StoryDto> stories);
	
	@InheritInverseConfiguration(name = "storyDtoListToStoryEntities")
	List<StoryDto> storyEntitiesToStoryDtos(List<Story> stories);
	
	@Named("convertKidsListToMap")
	public default Map<String, Object> convertKidsListToMap(List<String> kids){
		if(kids!=null && !kids.isEmpty())
		{
			Map<String, Object> map = new HashMap<>();
			map.put("kids", kids);
			return map;
		}
		return new HashMap<>();
	}
	
	@Named("convertKidsMapToList")
	public default List<String>  convertKidsListToMap(Map<String, Object> kids){
		if(kids!=null && !kids.isEmpty())
		{
			return (new ObjectMapper()).convertValue(kids.get("kids"), List.class);
		}
		return new ArrayList<>();
	}
}
