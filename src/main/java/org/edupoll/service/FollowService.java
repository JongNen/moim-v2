package org.edupoll.service;

import java.util.Date;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.model.entity.Follow;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FollowService {
	@Autowired
	FollowRepository followRepository;
	@Autowired
	UserRepository userRepository;

	// 누군가 누구를 팔로우하고자 할때 그걸 처리할 서비스 메서드
	@Transactional
	public FollowResponseData createFollow(String ownerId, String targetId) {
		if (followRepository.existsByOwnerIdIsAndTargetIdIs(ownerId, targetId)) {
			// 실패를 알리는 DTO 리턴 ... .. 바로 ResponseBody 로 나갈꺼니 객체로 리턴하는게 좋을 듯
			return new FollowResponseData(false);
		} else {
			Follow f = new Follow();
			f.setOwner(userRepository.findById(ownerId).get()); // 100 프로 있다고 가정했음
			f.setTarget(userRepository.findById(targetId).get()); // 오류의 원인이 될 가능성이 존재함
			f.setCreated(new Date());
			followRepository.save(f);
			return new FollowResponseData(true);
		}
	}

	// 누군가 누구를 언팔로우 하고자 할때 그걸 처리할 서비스 메서드
	@Transactional
	public FollowResponseData deleteFollow(String ownerId, String targetId) {
		followRepository.deleteByOwnerIdIsAndTargetIdIs(ownerId, targetId);
		return new FollowResponseData(true);
	}
}




