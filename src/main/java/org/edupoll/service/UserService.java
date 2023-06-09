package org.edupoll.service;

import java.util.Optional;

import org.edupoll.model.dto.LoginRequestData;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.UserDetailRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	// 회원 상세 정보를 수정/변경 처리할 서비스 메서드
	public boolean modifySpecificUserDetail(String userId, UserDetail detail) {
		// 1. 특정 유저가 존재하는지 확인
		if (userRepository.findById(userId).isEmpty())
			return false;
		// 2. UserDetail 저장하고
		User foundUser = userRepository.findById(userId).get();
		if (foundUser.getUserDetail() != null)
			detail.setIdx(foundUser.getUserDetail().getIdx());

		UserDetail saved = userDetailRepository.save(detail);
		// 3. 특정 유저의 detail_idx에 방금 저장하며 부여받은 id 값을 설정해서 update
		foundUser.setUserDetail(saved);
		userRepository.save(foundUser);

		return true;
	}

	// 회원 가입
	public boolean createNewUser(User user) {
		if (userRepository.findById(user.getId()).isEmpty()) {
			// user.setJoinDate(new Date());
			userRepository.save(user);

			return true;
		}
		return false;
	}

	// 로그인 처리
	public boolean isValidUser(LoginRequestData data) {
		Optional<User> optional = userRepository.findById(data.getLoginId());
		if (optional.isPresent()) {
			String savedPass = optional.get().getPass();
			return savedPass.equals(data.getLoginPass());
		}
		return false;

	}

	// logonId로 유저 정보 찾아서
	// 그 유저의 detail_idx 찾아서
	// 그걸로 유저의 상세 정보를 찾아서 리턴
	public UserDetail findSpecificSavedDetail(String logonId) {

		return userRepository.findById(logonId).get().getUserDetail();

	}

	public void deleteUser(String id) {
		User foundUser = userRepository.findById(id).get();
		userRepository.deleteById(id);
		userDetailRepository.delete(foundUser.getUserDetail());
	}
	
	public User findSpecificUserById(String targetId) {
		return  userRepository.findById(targetId).orElse(null);
	}

}
