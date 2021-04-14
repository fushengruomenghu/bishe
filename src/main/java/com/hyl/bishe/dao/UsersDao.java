package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface UsersDao extends JpaRepository<Users,Integer> {
    Users findAllByPhone(String phone);
    @Transactional
    @Modifying
    @Query(value = "update p_users set username=?1,sex=?2,phone=?3,location=?4,pici=?5,grade=?6,leibie=?7,hobby=?8 where id=?9",nativeQuery = true)
    void updateUserById(String username,String sex,String phone,String location,String pici, String grade, String leibie,String hobby,Integer id);
}
