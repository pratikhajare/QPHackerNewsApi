package com.hackernews.service;

import java.util.List;

import javax.validation.Valid;

import com.hackernews.dto.CommentDto;

/*
 *  @author Pratik Hajare
 *  
 * */

public interface CommentService {

	List<CommentDto> addComments(@Valid List<CommentDto> comments);

	List<CommentDto> fetchTopComments(String storyIdentifier);

}
