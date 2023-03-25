package com.hackernews.entity;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
 *  @author Pratik Hajare
 *  
 * */
@Data
@Table(name = "story")
@Entity
@SuperBuilder
@NoArgsConstructor
public class Story {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "story_id")
	private String id;

	@Column(name = "username")
	private String by;

	@Column(name = "descendants")
	private Integer descendants;

	@Column(name = "title")
	private String title;
	
	@Column(name = "score")
	private Integer score;

	@Column(name = "submit_time")
	private BigInteger time;

	@Column(name = "url")
	private String url;

	@Column(name = "type")
	private String type;

	@ElementCollection
//	@Column(name = "kids")
	private List<String> kids;
}
