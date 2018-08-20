package com.gravity.paydebt.service;

import com.gravity.paydebt.model.UserInfo;
import com.gravity.paydebt.model.UserPwd;
import com.gravity.paydebt.repository.user.UserPwdRepository;
import com.gravity.paydebt.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserPwdRepository userPwdRepository;

    public UserService() {
    }

    @Autowired
    public UserService(UserRepository userRepository, UserPwdRepository userPwdRepository) {
        this.userRepository = userRepository;
        this.userPwdRepository = userPwdRepository;
    }

    public ResponseEntity<UserInfo> createUser(UserInfo userInfo) {
        if (!isUsernameExist(userInfo.getUsername())) {
            userRepository.save(userInfo);
            userPwdRepository.save(userInfo.getUserPwd());
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(userInfo, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<UserInfo> updateUserInfo(UserInfo userInfo) {
        if (isUsernameExist(userInfo.getUsername())) {
            userRepository.save(userInfo);
            userInfo = userRepository.findByUsername(userInfo.getUsername());
        }else{
            userInfo = null;
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    public ResponseEntity<UserPwd> updateUserPwd(UserPwd userPwd) {
        if (isUsernameExist(userPwd.getUsername())) {
            userPwdRepository.save(userPwd);
            userPwd = userPwdRepository.findByUsername(userPwd.getUsername());
        }else{
            userPwd = null;
        }
        return new ResponseEntity<>(userPwd, HttpStatus.OK);
    }

    public ResponseEntity<List<UserInfo>> getAllUser() {
        userRepository.findAll();
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }

    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username) == null ? false:true;
    }

}
