package org.edupoll.controller.api;

import org.edupoll.model.dto.FollowResponseData;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
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
			@SessionAttribute(name = "logonId") String owner) {
		return followService.createFollow(owner, target);
	}

	@DeleteMapping
	public FollowResponseData followDeleteHandle(@SessionAttribute(name = "logonId") String owner,
			@RequestParam String target) {
		return followService.deleteFollow(owner, target);
	}

}
