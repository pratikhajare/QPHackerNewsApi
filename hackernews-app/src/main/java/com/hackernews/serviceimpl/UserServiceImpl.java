package com.hackernews.serviceimpl;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackernews.dao.UserRepository;
import com.hackernews.dto.UserDto;
import com.hackernews.mapper.UserMapper;
import com.hackernews.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDto addUser(@Valid UserDto userDto) {
		if (userDto != null && !userRepository.existsByUsername(userDto.getUsername())) {
			return userMapper.userEntityToUserDto(userRepository.save(userMapper.userDtoToUserEntity(userDto)));
		}
		return null;
	}
}
