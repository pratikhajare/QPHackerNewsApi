package com.hackernews.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hackernews.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

	@Query(value = "SELECT c1.*, cast(COUNT(c2.comment_id) as char) AS child_comment_count FROM comments c1 LEFT JOIN comments c2 \r\n"
			+ "ON c1.comment_id = c2.parent_comment_id WHERE c1.story_id = ?1 \r\n"
			+ "ORDER BY child_comment_count DESC LIMIT 10", nativeQuery = true)
	List<Object[]> fetchTopCommentForStory(String storyIdentifier);
}
