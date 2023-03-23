package com.hackernews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *  @author Pratik Hajare
 *  
 * */
@Data
@AllArgsConstructor
public class ResponseDto {

	private boolean isSuccess;

	private Object responseBody;

	private String message;
}
