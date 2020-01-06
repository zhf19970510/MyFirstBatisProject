package com.zhf.dao;

import com.zhf.pojo.User;

import java.util.List;

/**
 * Created on 2020/1/3 0003.
 */
public interface UserMapper {
    User findUserById(int id);
    List<User> findUserByName(String name);
    void deleteUser(int id);
    void updateUser(User user);
    void addUser(User user);
}
