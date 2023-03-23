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
package com.hackernews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;

@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config cacheConfig() {
		return new Config().setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("topTenStories").setTimeToLiveSeconds(900)
						.setEvictionConfig(new EvictionConfig().setSize(200)
								.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE).setEvictionPolicy(EvictionPolicy.LRU)));
	}

}
