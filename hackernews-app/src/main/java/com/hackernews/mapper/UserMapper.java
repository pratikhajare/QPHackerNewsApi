package com.hackernews.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.hackernews.dto.UserDto;
import com.hackernews.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User userDtoToUserEntity(UserDto userDto);
	
	@InheritInverseConfiguration(name = "userDtoToUserEntity")
	UserDto userEntityToUserDto(User user);
}
