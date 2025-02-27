package com.mehmetozanguven.springsecuritymultipleproviders.service.model;

import com.mehmetozanguven.springsecuritymultipleproviders.entities.UserDTO;
import com.mehmetozanguven.springsecuritymultipleproviders.repositories.UserRepository;
import com.mehmetozanguven.springsecuritymultipleproviders.security.SecureUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostgresqlUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userInDb = userRepository.findUserDTOByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found in the db"));
        return new SecureUser(userInDb);
    }
}
