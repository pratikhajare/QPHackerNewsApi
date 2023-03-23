package com.hackernews.dao;

/*
 *  @author Pratik Hajare
 *  
 * */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hackernews.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

	@Query(value = "SELECT c1.*, cast(COUNT(c2.parent_comment_id) as char) AS child_comment_count FROM (SELECT c.* FROM comments c where c.story_id = ?1 \r\n"
			+ "and c.parent_comment_id = '' or c.parent_comment_id is null) c1 LEFT JOIN comments c2 ON c1.comment_id = c2.parent_comment_id \r\n"
			+ "group by c1.comment_id ORDER BY child_comment_count DESC LIMIT 10", nativeQuery = true)
	List<Object[]> fetchTopCommentForStory(String storyIdentifier);
}
