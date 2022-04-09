package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UsersDao extends JpaRepository<Users,Integer> {
    Users findAllByPhone(String phone);
    @Transactional
    @Modifying
    @Query(value = "update p_users set username=?1,sex=?2,phone=?3,location=?4,password=?5,grade=?6,leibie=?7,major=?8,usersrole=?10,secret=?11 where id=?9",nativeQuery = true)
    void updateUserById(String username,String sex,String phone,String location,String password, String grade, String leibie,String major,Integer id,String usersrole,String secret);

    @Transactional
    @Modifying
    @Query(value = "update p_users set grade=?1 where phone=?2",nativeQuery = true)
    void updateUserGradeByPhone(String grade,String phone);

    Users findAllById(Integer id);

    Users findUsersByPhoneAndPassword(String phone, String password);

    @Transactional
    @Modifying
    @Query(value = "update p_users set password=?2 where phone=?1",nativeQuery = true)
    void updatePasswordByPhone(String phone,String password);


}
