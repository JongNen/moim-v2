package org.edupoll.service;

import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class MoimService {
	@Autowired
	MoimRepository moimRepository;

	@Autowired
	UserRepository userRepository;

	// 새 모임 등록용 서비스 메서드
	public String createNewMoim(Moim moim, String logonId) {
		User user = userRepository.findById(logonId).get(); // 로그온 상태라면 무조건 있는 데이터.
//		
//		User user = new User();
//		user.setId(logonId);

		moim.setManager(user);
		moim.setCurrentPerson(1);

		Moim saved = moimRepository.save(moim);

		return saved.getId();
	}

	// 등록된 모임글들 불러오기용 서비스 메서드
	public List<Moim> getMoimsInSpecificPage(int pageNo) {
		Sort sort = Sort.by(Direction.DESC, "targetDate");
		PageRequest pageRequest = PageRequest.of(pageNo - 1, 12, sort);
		return moimRepository.findAll(pageRequest).toList();
	}

	// 특정 ID의 모임정보 불러오기용 서비스 메서드
	public Moim getSpecificMoimById(String id) {
		return moimRepository.findById(id).orElse(null);
	}
	
	public long countList() {
		return moimRepository.count();
	}
	


}
