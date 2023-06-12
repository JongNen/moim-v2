package org.edupoll.controller.api;

import org.edupoll.model.dto.response.AttendanceJoinResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public AttendanceJoinResponseData attendanceJoinHandle(@AuthenticationPrincipal Account account, String moimId) {
		AttendanceJoinResponseData rst = attendanceService.addNewAttendance(account.getUsername(), moimId);
		return rst;
	}
	
	@DeleteMapping("/attendance/cancel")
	public AttendanceJoinResponseData attendanceCancelHandle(@AuthenticationPrincipal Account account, String moimId) {
		return attendanceService.cancelAttedance(account.getUsername(), moimId);
	}
}
