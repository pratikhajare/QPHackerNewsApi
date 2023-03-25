package com.hackernews.service;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hackernews.dto.CommentDto;

/*
 *  @author Pratik Hajare
 *  
 * */

public interface CommentService {

	List<CommentDto> fetchTopComments(Integer storyIdentifier) throws JsonMappingException, JsonProcessingException;

}
