package com.hackernews.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hackernews.dto.CommentDto;
import com.hackernews.dto.StoryDto;
import com.hackernews.service.CommentService;
import com.hackernews.service.utils.HackerNewsUtilsServiceImpl;

/*
 *  @author Pratik Hajare
 *  
 * */

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	HackerNewsUtilsServiceImpl hackerNewsUtilsServiceImpl;

	/**
	 * Fetches top 10 comments sorted by total child comments
	 * 
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@Override
	public List<CommentDto> fetchTopComments(Integer storyIdentifier)
			throws JsonMappingException, JsonProcessingException {
		if (storyIdentifier != null) {
			StoryDto story = hackerNewsUtilsServiceImpl
					.convertToStory(hackerNewsUtilsServiceImpl.fetchItemById(storyIdentifier));
			if (story != null && story.getKids() != null && !story.getKids().isEmpty()) {
				List<CommentDto> topComments = new ArrayList<>();
				List<String> filteredCommentIds = story.getKids().stream().limit(10).collect(Collectors.toList());
				for (String commentId : filteredCommentIds) {
					CommentDto comments = hackerNewsUtilsServiceImpl
							.convertToComment(hackerNewsUtilsServiceImpl.fetchItemById(Integer.valueOf(commentId)));
					if (comments != null) {
						comments.setTotalChildComments((comments.getKids() != null && !comments.getKids().isEmpty())
								? comments.getKids().size()
								: 0);
						topComments.add(comments);
					}
				}
				return topComments.stream()
						.sorted(Comparator.comparing(CommentDto::getTotalChildComments, Comparator.reverseOrder()))
						.collect(Collectors.toList());
			}
		}
		return Collections.emptyList();
	}

}
