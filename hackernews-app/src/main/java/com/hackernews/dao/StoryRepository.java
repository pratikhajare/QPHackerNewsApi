package com.hackernews.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.hackernews.entity.Story;

/*
 *  @author Pratik Hajare
 *  
 * */
@Transactional
public interface StoryRepository extends JpaRepository<Story, String> {

	@Query(value = "SELECT s.* FROM story s\r\n"
			+ "WHERE s.submission_time >= now() - interval '15' minute order by s.score DESC limit 10", nativeQuery = true)
	List<Story> fetchTopTenStoriesOfLast15Mins();

	@Modifying
	@Query(value = "update story set viewed = 'Y' where story_id in (?1)", nativeQuery = true)
	void updateViewedStories(List<String> storyIndentifierList);

	@Query(value = "select s from Story s where s.isViewed = 'Y'")
	List<Story> fetchPastViewedStories();
}
