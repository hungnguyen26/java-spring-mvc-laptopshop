package com.hungnguyen.laptop_shop.service;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String hanldeHello(){
        return "hello from servicce";
    }
    

    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }
}
