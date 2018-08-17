package com.gravity.paydebt.repository;

import com.gravity.paydebt.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByUsername(String username);

}
