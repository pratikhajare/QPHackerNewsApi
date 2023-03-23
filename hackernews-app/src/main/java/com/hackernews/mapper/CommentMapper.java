package com.hackernews.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.hackernews.dto.CommentDto;
import com.hackernews.entity.Comment;

/*
 *  @author Pratik Hajare
 *  
 * */
@Mapper(componentModel = "spring")
public interface CommentMapper {

	Comment commentDtoToCommentEntity(CommentDto commentDto);

	@InheritInverseConfiguration(name = "commentDtoToCommentEntity")
	CommentDto commentEntityToCommentDto(Comment comment);

	List<Comment> commentDtoListToCommentEntityList(List<CommentDto> commentDtos);

	@InheritInverseConfiguration(name = "commentDtoListToCommentEntityList")
	List<CommentDto> commentEntityListToCommentDtoList(List<Comment> stories);
}
