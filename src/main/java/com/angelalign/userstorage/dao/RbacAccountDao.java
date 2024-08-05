package com.angelalign.userstorage.dao;


import com.angelalign.userstorage.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface RbacAccountDao {

    UserEntity getUserById(Integer accountId);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    int getUserCount();
    List<UserEntity> getAllUsers();
    List<UserEntity> searchForUser(String search);

}
