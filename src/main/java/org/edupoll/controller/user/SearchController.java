package org.edupoll.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.UserResponseData;
import org.edupoll.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	@GetMapping("/search")
	public String seachUserHandle(String data, Model model, @RequestParam(defaultValue = "1") int page,
			@SessionAttribute(required = false) String logonId) {
		List<UserResponseData> found = searchService.findBySearchUser(data, logonId, page);
		model.addAttribute("search", found);
		model.addAttribute("data", data);
		List<String> pages = new ArrayList<>();
		long cnt = searchService.countList(data);
		model.addAttribute("searchData", cnt);
		for (int i = 1; i <= cnt / 10 + (cnt % 10 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		model.addAttribute("pages", pages);
		if (logonId != null)
			model.addAttribute("login", logonId);
		return "search";
	}

	/*
	 * @GetMapping("/search") public String
	 * searchResultHandle(@RequestParam(required = false) String q, Model model) {
	 * if(q == null) { return "search/form"; }else { model.addAttribute("result",
	 * searchService.getUsersMatchedQuery(q)); model.addAttribute("query" , q);
	 * return "search/result"; } }
	 * 
	 */

}
