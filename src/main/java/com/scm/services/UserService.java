package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.user;

public interface UserService {

    user  saveUser( user user);
   Optional<user>  getUserById(String id);
    Optional<user>  updatUser(user user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistForEmail(String email);
    List<user> getAllUsers();  

    // add more method related user service[Logic]

}
