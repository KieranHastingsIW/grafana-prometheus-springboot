package com.helloworldapi.helloworld_api.service;

import java.util.List;

import com.helloworldapi.helloworld_api.Entity.User;

public interface UserService {
    List<User> getListOfUsers();
    User getUser(Long id) throws Exception;
    void saveUser(User user);
    void deleteUser(Long id);
    User updateUser(User user, Long id) throws Exception;
}
