package com.gcy.dao;

import com.gcy.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     *
     * @return
     */
    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);
    void deleteUser(Integer userid);
}
