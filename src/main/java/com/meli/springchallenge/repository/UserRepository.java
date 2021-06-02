package com.meli.springchallenge.repository;

import com.meli.springchallenge.model.User;

public interface UserRepository {
    User findUserByUserId(Integer userId);
    void save(User userToSave);
}
