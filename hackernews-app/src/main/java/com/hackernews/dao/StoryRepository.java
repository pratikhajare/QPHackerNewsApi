package com.hackernews.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackernews.entity.Story;

public interface StoryRepository extends JpaRepository<Story, String> {

}
