package com.hackernews.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "user")
@Entity
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "user_id", columnDefinition = "CHAR(32)")
	private String userId;

	@Column(name = "username")
	private String username;

	@Column(name = "about")
	private String about;

	@Column(name = "karma")
	private String karma;
	
	@CreationTimestamp
	@Column(name = "created_time")
	private LocalDateTime createdTime;
}
