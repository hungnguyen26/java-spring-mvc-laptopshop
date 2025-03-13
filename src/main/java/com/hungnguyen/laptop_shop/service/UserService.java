package com.hungnguyen.laptop_shop.service;

import java.util.List;

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
    
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }

    public User getUserById(long id){
        return this.userRepository.findById(id);
    }

    public void deleteAUser(long id){
        this.userRepository.deleteById(id);
    }
}
