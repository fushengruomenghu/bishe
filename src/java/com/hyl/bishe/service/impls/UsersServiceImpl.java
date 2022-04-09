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

    @Override
    public void updateUserById(Users users, Integer id) {
        usersDao.updateUserById(users.getUsername(),users.getSex(),users.getPhone(),users.getLocation(),users.getPassword(),users.getGrade(),users.getLeibie(),users.getMajor(),id,users.getUsersrole(),users.getSecret());
    }

    @Override
    public List<Users> findall() {
        return usersDao.findAll();
    }

    public void updateUserGradeByPhone(String grade,String phone) {
        usersDao.updateUserGradeByPhone(grade,phone);
    }

    public Users findUsersByPhoneAndPassword(String phone,String password){
       return usersDao.findUsersByPhoneAndPassword(phone,password);
    }

    public Users findUserByPhone(String phone){
        return usersDao.findAllByPhone(phone);
    }
    public Users findUserById(Integer id){
        return usersDao.findAllById(id);
    }
    public void sava(Users user){
        usersDao.save(user);
    }
    public boolean deleteUser(Users user){
        usersDao.delete(user);
        if (usersDao.findAllById(user.getId())==null) {
            return true;
        }else {
            return false;
        }
    }

    public void updatepassword(String inputPassToFormPass, String phone) {
       usersDao.updatePasswordByPhone(phone,inputPassToFormPass);
    }
}
