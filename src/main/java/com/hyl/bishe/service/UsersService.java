package com.hyl.bishe.service;

import com.hyl.bishe.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService {
    Page<Users> getUserList(int pageNum, int pageSize);
    List<Users> findAllUsers();
}
