package com.hungnguyen.laptop_shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.Order;
import com.hungnguyen.laptop_shop.domain.OrderDetail;
import com.hungnguyen.laptop_shop.repository.OrderDetailRepository;
import com.hungnguyen.laptop_shop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }
    
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id){
        return this.orderRepository.findById(id);
    }

    public void deleteOrderById(long id) {
        // delete order detail
        Optional<Order> orderOptional = this.getOrderById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getId());
            }
        }
    
        this.orderRepository.deleteById(id);
    }

    public void updateOrder(Order order){
        Optional<Order> orderOptional = this.getOrderById(order.getId());
        if (orderOptional.isPresent()) {
            Order currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }
    
}
