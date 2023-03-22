package com.hackernews.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackernews.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	boolean existsByUsername(String username);
}