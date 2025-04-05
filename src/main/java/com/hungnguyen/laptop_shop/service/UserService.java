package com.hungnguyen.laptop_shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.Role;
import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.domain.DTO.RegisterDTO;
import com.hungnguyen.laptop_shop.repository.RoleRepository;
import com.hungnguyen.laptop_shop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }
}
