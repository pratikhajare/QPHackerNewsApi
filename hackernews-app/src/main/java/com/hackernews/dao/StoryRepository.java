package com.hackernews.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hackernews.entity.Story;

/*
 *  @author Pratik Hajare
 *  
 * */
@Transactional
public interface StoryRepository extends JpaRepository<Story, String> {

}
