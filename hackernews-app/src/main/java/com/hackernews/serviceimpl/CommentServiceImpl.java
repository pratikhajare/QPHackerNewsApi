package com.hackernews.serviceimpl;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackernews.dao.CommentRepository;
import com.hackernews.dto.CommentDto;
import com.hackernews.mapper.CommentMapper;
import com.hackernews.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<CommentDto> addComments(@Valid List<CommentDto> comments) {
		if (comments != null && !comments.isEmpty()) {
			return commentMapper.commentEntityListToCommentDtoList(
					commentRepository.saveAll(commentMapper.commentDtoListToCommentEntityList(comments)));
		}
		return Collections.emptyList();
	}

	@Override
	public List<CommentDto> fetchTopComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
