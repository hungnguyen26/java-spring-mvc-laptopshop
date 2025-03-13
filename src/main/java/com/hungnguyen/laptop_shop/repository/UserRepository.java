package com.hungnguyen.laptop_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungnguyen.laptop_shop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User hungnguyen);

    void deleteById(long id);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);

}
