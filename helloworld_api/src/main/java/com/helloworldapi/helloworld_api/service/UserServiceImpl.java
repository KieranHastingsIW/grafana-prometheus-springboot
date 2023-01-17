package com.helloworldapi.helloworld_api.service;

import java.util.List;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.helloworldapi.helloworld_api.Entity.User;
import com.helloworldapi.helloworld_api.repository.UserRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public List<User> getListOfUsers(){
        return userRepository.findAll();
    }



    @Override
    public void saveUser(User user) {
        userRepository.save(user);
        
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        
    }

    @Override
    public User updateUser(User user, Long id) throws Exception {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User unwrappedUser = optUser.get();
            return userRepository.save(updateAssist(unwrappedUser,user));
         } else {
            throw new Exception("user not found");
         }

    }

    public User updateAssist(User unwrappedUser, User user){
        unwrappedUser.setFirstName(user.getFirstName());
        unwrappedUser.setLastName(user.getLastName());
        unwrappedUser.setAge(user.getAge());
        unwrappedUser.setGender(user.getGender());
        return unwrappedUser;
    }



    @Override
    public User getUser(Long id) throws Exception{
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User unwrappedUser = optUser.get();
            return unwrappedUser;
         } else {
            throw new Exception("user not found");
         }
        }
}
