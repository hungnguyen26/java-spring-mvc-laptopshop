package com.hungnguyen.laptop_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungnguyen.laptop_shop.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
