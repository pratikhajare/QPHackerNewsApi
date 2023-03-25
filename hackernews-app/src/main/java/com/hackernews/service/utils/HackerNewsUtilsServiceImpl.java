package com.hackernews.service.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackernews.dto.CommentDto;
import com.hackernews.dto.StoryDto;

/*
 *  @author Pratik Hajare
 *  
 * */
@Service
public class HackerNewsUtilsServiceImpl {

	@Value("${hackernews.service.url}")
	private String hackernewsServiceUrl;

	@Autowired
	ObjectMapper objMapper;

	/*
	 * Fetches top ten stories from Hacker News
	 * 
	 * @URL -
	 * https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty&orderBy=%
	 * 22$priority%22&limitToFirst=10
	 */
	public List<Integer> fetchTopStories(Integer limit) throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");

		String fileListServiceUrl = hackernewsServiceUrl.concat("/topstories.json");
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(fileListServiceUrl).queryParam("print", "pretty")
				.queryParam("orderBy", "\"$priority\"").queryParam("limitToFirst", limit + "").build().toUriString();
		ResponseEntity<String> responseEntity = restTemplate.exchange(urlTemplate, HttpMethod.GET,
				new HttpEntity<>(httpHeaders), String.class);
		if (responseEntity.hasBody()) {
			return objMapper.readValue(responseEntity.getBody(), List.class);
		}
		return Collections.emptyList();
	}

	/*
	 * Fetches story or comment by its respective id from Hacker News
	 * 
	 * @URL - https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty
	 */
	public Object fetchItemById(Integer itemId) throws JsonMappingException, JsonProcessingException {
		if (itemId != null) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type", "application/json");

			String fileListServiceUrl = hackernewsServiceUrl.concat("/item/" + itemId + ".json");
			String urlTemplate = UriComponentsBuilder.fromHttpUrl(fileListServiceUrl).queryParam("print", "pretty")
					.build().toUriString();
			ResponseEntity<String> responseEntity = restTemplate.exchange(urlTemplate, HttpMethod.GET,
					new HttpEntity<>(httpHeaders), String.class);
			if (responseEntity.hasBody()) {
				return objMapper.readValue(responseEntity.getBody(), Object.class);
			}
		}
		return null;
	}

	public StoryDto convertToStory(Object story) {
		if (story != null) {
			return objMapper.convertValue(story, new TypeReference<StoryDto>() {
			});
		}
		return null;
	}

	public CommentDto convertToComment(Object comment) {
		if (comment != null) {
			return objMapper.convertValue(comment, new TypeReference<CommentDto>() {
			});
		}
		return null;
	}
}
