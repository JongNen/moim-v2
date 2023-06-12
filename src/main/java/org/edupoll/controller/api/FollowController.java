package org.edupoll.controller.api;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	FollowService followService;

	@PostMapping
	public FollowResponseData followPostHandle(@RequestParam String target,
			@AuthenticationPrincipal Account account) {
		return followService.createFollow(account.getUsername(), target);
	}

	@DeleteMapping
	public FollowResponseData followDeleteHandle(@AuthenticationPrincipal Account account,
			@RequestParam String target) {
		return followService.deleteFollow(account.getUsername(), target);
	}

}
