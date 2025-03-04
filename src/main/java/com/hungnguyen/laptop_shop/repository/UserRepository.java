package com.hungnguyen.laptop_shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hungnguyen.laptop_shop.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User hungnguyen);
}
