package com.gravity.paydebt.repository;

import com.gravity.paydebt.model.UserPwd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPwdRepository extends JpaRepository<UserPwd, Integer> {

    UserPwd findByUsername(String username);

}
