package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	// List<Attendance> findByMoimId(String moimId);
	// List<Attendance> findByUserId(String userId);

	boolean existsByUserIdIsAndMoimIdIs(String userId, String moimId);
	List<Attendance> findByMoimIdIs(String moimId);
	
	void deleteByUserIdIsAndMoimIdIs(String userId, String moimId);
}
