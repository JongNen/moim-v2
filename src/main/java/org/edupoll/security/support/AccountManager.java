package org.edupoll.security.support;

import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountManager implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (userRepository.findById(username).isPresent()) {
			Account account = userRepository.findById(username).map(t->new Account(t)).get();
			return account;
		} else {
			throw new UsernameNotFoundException("not found");
		}

//		return userRepository.findById(username).map(t -> new Account(t))
//				.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}

}
