package com.rest.api.service;

import com.rest.api.model.User;
import com.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(String username,String password) {
        return userRepository.save(new User(username,password));
    }

    public boolean getByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);

        if(user == null)
            return false;
        else
            return true;
    }
}
