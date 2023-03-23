package com.hackernews.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackernews.dao.CommentRepository;
import com.hackernews.dto.CommentDto;
import com.hackernews.mapper.CommentMapper;
import com.hackernews.service.CommentService;

/*
 *  @author Pratik Hajare
 *  
 * */

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentMapper commentMapper;

	//Adding comments
	@Override
	public List<CommentDto> addComments(@Valid List<CommentDto> comments) {
		if (comments != null && !comments.isEmpty()) {
			return commentMapper.commentEntityListToCommentDtoList(
					commentRepository.saveAll(commentMapper.commentDtoListToCommentEntityList(comments)));
		}
		return Collections.emptyList();
	}

	/**
	 * Fetches top 10 comments sorted by total child comments using query
	 */
	@Override
	public List<CommentDto> fetchTopComments(String storyIdentifier) {
		if (storyIdentifier != null && !storyIdentifier.isEmpty()) {
			List<CommentDto> comments = commentRepository.fetchTopCommentForStory(storyIdentifier).stream()
					.map(CommentDto::new).collect(Collectors.toList());
			if (comments != null && !comments.isEmpty()) {
				return comments;
			}
		}
		return Collections.emptyList();
	}

}
