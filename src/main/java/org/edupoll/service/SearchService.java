package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edupoll.model.dto.UserResponseData;
import org.edupoll.model.entity.User;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class SearchService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FollowRepository followRepository;

	/*
	 * public List<User> searchUser(String data) { List<User> foundUsers = new
	 * ArrayList<>();
	 * 
	 * List<User> users = userRepository.findAll(); for (User user : users) { if
	 * (user.getId().contains(data) || user.getNick().contains(data)) {
	 * Optional<User> foundUser = userRepository.findById(user.getId());
	 * foundUser.ifPresent(foundUsers::add); } }
	 * 
	 * return foundUsers; }
	 */
	@Transactional
	public List<UserResponseData> findBySearchUser(String data, String logonId, int pageNo) {
		
		Pageable page = PageRequest.of(pageNo - 1, 10);
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(data, data, page);
		List<UserResponseData> responseList = list.stream().map(t-> new UserResponseData(t)).toList();
		
		for(UserResponseData urd : responseList) {
			if(followRepository.existsByOwnerIdIsAndTargetIdIs(logonId, urd.getId())) {
				urd.setFollowed(true);
			}
		}
		return responseList;
		
	}

	public long countList(String data) {
		return userRepository.countByIdContainingOrNickContainingAllIgnoreCase(data, data);
	}

	// 특정단어로 유저 찾아주는 기능을 처리할 서비스 구현

	/*
	 * public List<User> getUsersMatchedQuery(String query) { // 특정단어를 포함하고 데이터를 찾아야
	 * 하는데, 기본 JpaRepository에는 없는 기능 return
	 * userRepository.findByIdContainingOrNickContainingAllIgnoreCase(query, query);
	 * }
	 */
}
