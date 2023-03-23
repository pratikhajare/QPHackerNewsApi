/**************************************************
 * Copyright (c) 2021 RIA Advisory LLC.
 * All rights reserved.
 * 
 * @author PratikHajare
 * 
 * Change History:
 * Date Changed		Author			  	Description
 * 2021/00/00      	PratikHajare 		initial
 *  
 **************************************************/
package com.hackernews.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryComment extends CommentDto{
	
	private Integer totalChildComments;
	
}
