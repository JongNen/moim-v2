package org.edupoll.controller.api;

import org.edupoll.model.dto.AttendanceJoinResponseData;
import org.edupoll.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api")
public class AttendanceAPIController {

	@Autowired
	AttendanceService attendanceService;

	@PostMapping("/attendance/join")
	public AttendanceJoinResponseData attendanceJoinHandle(@SessionAttribute String logonId, String moimId) {
		AttendanceJoinResponseData rst = attendanceService.addNewAttendance(logonId, moimId);
		return rst;
	}
	
	@DeleteMapping("/attendance/cancel")
	public AttendanceJoinResponseData attendanceCancelHandle(@SessionAttribute String logonId, String moimId) {
		return attendanceService.cancelAttedance(logonId, moimId);
	}
}
