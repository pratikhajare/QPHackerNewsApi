package com.hackernews.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackernews.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>{

}
