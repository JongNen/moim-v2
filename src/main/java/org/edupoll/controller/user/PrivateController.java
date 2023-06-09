package org.edupoll.controller.user;

import org.edupoll.model.entity.UserDetail;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class PrivateController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	@GetMapping("/private")
	public String showPrivateInfoView(@SessionAttribute String logonId, Model model) {
		model.addAttribute("user", userService.findSpecificUserById(logonId));
		return "private/default";
	}
	
	@GetMapping("/private/modify")
	public String showPrivateModifyForm(@SessionAttribute String logonId, Model model) {
		UserDetail savedDetail = userService.findSpecificSavedDetail(logonId);
		model.addAttribute("savedDetail", savedDetail);
		
		return "private/modify-form";
		
	}
	
	@PostMapping("/private/modify")
	public String privateModifyHandle(@SessionAttribute String logonId, UserDetail detail, Model model) {
		boolean rst = userService.modifySpecificUserDetail(logonId, detail);
		logger.debug("privateModifyHandle.. {}", rst);
		return "redirect:/private/modify";
	}

}
