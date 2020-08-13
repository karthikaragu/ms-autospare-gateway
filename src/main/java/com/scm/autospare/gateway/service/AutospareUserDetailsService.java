package com.scm.autospare.gateway.service;

import com.scm.autospare.gateway.entity.UserDetail;
import com.scm.autospare.gateway.repository.AutospareUserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutospareUserDetailsService implements UserDetailsService {
    @Autowired
    AutospareUserDetailRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserDetail> user = repository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("User not registered !!"));
        return  new AutospareUserDetails(user.get());

    }
}
