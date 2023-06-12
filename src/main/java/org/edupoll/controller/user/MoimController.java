package org.edupoll.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Moim;
import org.edupoll.security.support.Account;
import org.edupoll.service.AttendanceService;
import org.edupoll.service.MoimService;
import org.edupoll.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MoimController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MoimService moimService;

	@Autowired
	ReplyService replyService;
	
	@Autowired
	AttendanceService attendanceService;

	@GetMapping("/moim")
	public String showMoimList(@RequestParam(defaultValue = "1") int page, Model model) {
		model.addAttribute("moims", moimService.getMoimsInSpecificPage(page));
		List<String> pages = new ArrayList<>();
		long cnt = moimService.countList();
		for (int i = 1; i <= cnt / 12 + (cnt % 12 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		model.addAttribute("pages", pages);

		return "moim/list";

	}

	@GetMapping("/moim/create")
	public String showMoimCreateForm() {
		return "moim/create";
	}

	@PostMapping("/moim/create")
	public String moimCreateHandle(Moim moim, @AuthenticationPrincipal Account account) {
		String createdId = moimService.createNewMoim(moim, account.getUsername());
		logger.debug("moimCreateHandle result id = {}", createdId);
		return "redirect:/moim/view?id=" + createdId;
	}

	@GetMapping("/moim/view")
	public String showMoimDetail(@AuthenticationPrincipal Account account ,String id, @RequestParam(defaultValue = "1") int p, Model model) {
		model.addAttribute("moim", moimService.getSpecificMoimById(id));
		//model.addAttribute("replys", replyService.getReplysByMoimId(id, p));
		model.addAttribute("isJoined", attendanceService.isExistsAttendance(account.getUsername(), id));
		return "moim/view";
	}

	@PostMapping("/moim/reply")
	public String CreateReplyHandle(AddReplyRequestData data) {
		replyService.createNewReplay(data);
		return "redirect:/moim/view?id=" + data.getMoimId();

	}

}
