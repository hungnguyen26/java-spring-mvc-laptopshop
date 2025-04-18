package com.hungnguyen.laptop_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungnguyen.laptop_shop.domain.Cart;
import com.hungnguyen.laptop_shop.domain.User;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findByUser(User user);
}
