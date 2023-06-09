package org.edupoll.controller.user;

import java.util.List;

import org.edupoll.model.entity.Avatar;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.AvatarRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssociationController {

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	AvatarRepository avatarRepository;

	@GetMapping("/assoc/03")
	public String assoc03Hanlde() {
		
		User found = userRepository.findById("zz101").get();
		System.out.println(found.getId());
		List<Moim> attends = found.getAttendMoims();
		for(Moim m : attends) {
			System.out.println(m.getId() +" / "+ m.getTitle());
		}
		
		return "index";
	}

	
	
	@GetMapping("/assoc/02")
	public String assoc02Hanlde(String avatarId) {
		
		
		if (avatarRepository.findById(avatarId).isPresent()) {
			Avatar a = avatarRepository.findById(avatarId).get();
			List<UserDetail> details = a.getDetails();
			for (UserDetail d : details) {
				System.out.println("→ " + d.getUser().getId());

			}
		} else {
			System.out.println("not found");
		}
		return "index";
	}

	@GetMapping("/assoc/01")
	public String assoc01Handle(String userId) {

		User found = userRepository.findById(userId).orElse(null);
		System.out.println(found);

//		UserDetail detail =found.getUserDetail();
//		System.out.println(detail);
//		
		return "index";
	}

}
