package com.hackernews.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
 *  @author Pratik Hajare
 *  
 * */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String by;

	private String parent;

	private String text;

	private String type;

	private BigInteger time;
	
	private List<String> kids;

	private Integer totalChildComments;

}
