package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edupoll.model.dto.AttendanceJoinResponseData;
import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AttendanceService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AttendanceRepository attendanceRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MoimRepository moimRepository;

	// 참가 취소 요청시 처리할 서비스 메서드

	@Transactional
	public AttendanceJoinResponseData cancelAttedance(String userId, String moimId) {
		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();

		attendanceRepository.deleteByUserIdIsAndMoimIdIs(userId, moimId);
		ajrd.setResult(true);
		Moim moim = moimRepository.findById(moimId).get();
		moim.setCurrentPerson(moim.getCurrentPerson() - 1);
		Moim saved = moimRepository.save(moim);

		ajrd.setCurrentPerson(saved.getCurrentPerson());
		List<String> alist = attendanceRepository.findByMoimIdIs(moimId).stream().map(t -> t.getUser().getId())
				.toList();
		ajrd.setAttendUserIds(alist);
		
		return ajrd;
	}
	

	// 참가 신청 요청시 처리할 서비스 메서드
	@Transactional
	public AttendanceJoinResponseData addNewAttendance(String userId, String moimId) {
		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();
		logger.debug("userId={}, moimId={}", userId, moimId);
		Optional<User> user = userRepository.findById(userId);
		Optional<Moim> moim = moimRepository.findById(moimId);
		if (user.isEmpty() || moim.isEmpty()) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("유효하지 않은 정보가 전송되었습니다.");
			return ajrd;
		}
		if (attendanceRepository.existsByUserIdIsAndMoimIdIs(userId, moimId)) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("이미 참가중인 모임입니다.");
			return ajrd;
		}
		if (moim.get().getCurrentPerson() == moim.get().getMaxPerson()) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("최대 허용 인원을 초과하였습니다.");
			return ajrd;
		}
		
		if(moim.get().getManager().getId().matches(userId)){
			ajrd.setResult(false);
			ajrd.setErrorMessage("본인 글은 참여가 불가능합니다.");
			return ajrd;
		}
		Attendance one = new Attendance(user.get(), moim.get());
		attendanceRepository.save(one);
		moim.get().setCurrentPerson(moim.get().getCurrentPerson() + 1);
		moimRepository.save(moim.get());
		ajrd.setResult(true);
		ajrd.setCurrentPerson(moim.get().getCurrentPerson());
		List<String> list = new ArrayList<>();
		for (Attendance a : attendanceRepository.findByMoimIdIs(moimId)) {
			list.add(a.getUser().getId());
		}
		List<String> alist = attendanceRepository.findByMoimIdIs(moimId).stream().map(t -> t.getUser().getId())
				.toList();

		ajrd.setAttendUserIds(alist);

		return ajrd;
	}

	// 특정유저가 특정모임에 참여중인 확인하고자 할때 사용할 서비스 메서드
	public boolean isExistsAttendance(String userId, String moimId) {
		return attendanceRepository.existsByUserIdIsAndMoimIdIs(userId, moimId);
	}
}
