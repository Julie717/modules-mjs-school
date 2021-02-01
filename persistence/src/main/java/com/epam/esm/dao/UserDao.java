package com.epam.esm.dao;

import com.epam.esm.model.User;

import java.util.Optional;

public interface UserDao extends CommonDao<User> {
    Optional<User> findByName(String name);
}