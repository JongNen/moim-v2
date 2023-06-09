package org.edupoll;

import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppMoimApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MoimRepository moimRepository;

	@Test
	void contextLoads() {
		User found = userRepository.findById("zz101").get();
		List<Moim> attends = found.getAttendMoims();
		for(Moim m : attends) {
			System.out.println(m.getId() + " / " + m.getTitle());
		}
	}

}
