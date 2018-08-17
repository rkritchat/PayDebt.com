package com.gravity.paydebt.service;

import com.gravity.paydebt.model.UserInfo;
import com.gravity.paydebt.model.UserPwd;
import com.gravity.paydebt.repository.UserPwdRepository;
import com.gravity.paydebt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailService implements UserDetailsService {

    private UserPwdRepository userPwdRepository;

    public CustomerDetailService() { }

    @Autowired
    public CustomerDetailService(UserPwdRepository userPwdRepository) {
        this.userPwdRepository = userPwdRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserPwd userPwd = getUserPwd(username);
        return userPwd == null ? null : new User(userPwd.getUsername(), encoder.encode(userPwd.getPassword()),
                AuthorityUtils.createAuthorityList(userPwd.getRole()));
    }

    public UserPwd getUserPwd(String username) {
        UserPwd userPwd = userPwdRepository.findByUsername(username);
        System.out.println(userPwd + "  || username : " + username);
        return userPwd;
    }
}
