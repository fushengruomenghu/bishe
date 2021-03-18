package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.UsersDao;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Override
    public Page<Users> getUserList(int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<Users> users = usersDao.findAll(pageable);
        return users;
    }

    @Override
    public List<Users> findAllUsers() {
        return usersDao.findAll();
    }
    public Users findUserByPhone(String phone){
        return usersDao.findAllByPhone(phone);
    }
    public void sava(Users user){
        usersDao.save(user);
    }

}
