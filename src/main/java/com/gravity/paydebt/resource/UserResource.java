package com.gravity.paydebt.resource;

import com.gravity.paydebt.model.UserInfo;
import com.gravity.paydebt.model.UserPwd;
import com.gravity.paydebt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        return userService.createUser(userInfo);
    }

    @PatchMapping("/update-info")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo) {
        return userService.updateUserInfo(userInfo);
    }

    @PatchMapping("/update-pwd")
    public ResponseEntity<UserPwd> updateUserPwd(@RequestBody UserPwd userPwd) {
        return userService.updateUserPwd(userPwd);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserInfo>> getAllUser() {
        return userService.getAllUser();
    }
}
