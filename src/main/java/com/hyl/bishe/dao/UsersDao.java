package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<Users,Integer> {
    Users findAllByPhone(String phone);
}
