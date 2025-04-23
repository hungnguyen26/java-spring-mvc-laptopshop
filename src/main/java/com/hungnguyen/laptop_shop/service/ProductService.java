package com.hungnguyen.laptop_shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.Cart;
import com.hungnguyen.laptop_shop.domain.CartDetail;
import com.hungnguyen.laptop_shop.domain.Order;
import com.hungnguyen.laptop_shop.domain.OrderDetail;
import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.repository.CartDetailRepository;
import com.hungnguyen.laptop_shop.repository.CartRepository;
import com.hungnguyen.laptop_shop.repository.OrderDetailRepository;
import com.hungnguyen.laptop_shop.repository.OrderRepository;
import com.hungnguyen.laptop_shop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Product createProduct(Product pr){
        return this.productRepository.save(pr);
    }

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(long id){
        return this.productRepository.findById(id);
    }

    public void deleteProductById(long id){
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long product_id, HttpSession session){
        User user = this.userService.getUserByEmail(email);
        if(user!=null){
            // check user đã có cart chưa, nếu chưa tạo mới
            Cart cart = this.cartRepository.findByUser(user);

            if(cart == null){
                // tạo mới cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            // save cart-detail
            // tìm product by id
            Optional<Product> p  = this.productRepository.findById(product_id);
            if(p.isPresent()){
                Product realProduct = p.get();

                // check sp đã từng được thêm trước đây hay chưa
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if(oldDetail == null){
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);


                    // update cart (sum)
                    int s = cart.getSum()+ 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                }else{
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                }

                
            }
            
        }

    }
    

    public Cart fechCartByUser(User user){
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session){
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if(cartDetailOptional.isPresent()){
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();

            //delete cart detail
            this.cartDetailRepository.deleteById(cartDetailId);

            //update cart
            if(currentCart.getSum() >= 1){
                //update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            }else{
                // delete cart(sum=1)
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
              CartDetail currentCartDetail = cdOptional.get();
              currentCartDetail.setQuantity(cartDetail.getQuantity());
              this.cartDetailRepository.save(currentCartDetail);
            }
          }
          
    }

    public void handlePlaceOrder(User user, HttpSession session,String receiverName,String receiverAddress,String receiverPhone){


        // create order
        // Order order = new Order();
        // order.setUser(user);
        // order.setReceiverName(receiverName);
        // order.setReceiverAddress(receiverAddress);
        // order.setReceiverPhone(receiverPhone);
        // order = this.orderRepository.save(order);

        

        // b1: get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if(cart!= null){
            List<CartDetail> cartDetails = cart.getCartDetails();

            if(cartDetails != null){
                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");

                double sum = 0;
                for(CartDetail cd : cartDetails){
                    sum+= cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);
                
                // create orderDetail
                for(CartDetail cd : cartDetails){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setQuantity(cd.getQuantity());
                    orderDetail.setPrice(cd.getPrice());
                    this.orderDetailRepository.save(orderDetail);
                }

                //b2 : delete cart_detail
                for(CartDetail cd : cartDetails){
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                //  delete cart
                this.cartDetailRepository.deleteById(cart.getId());

                //b3: set session
                session.setAttribute("sum", 0);
            }
        }

    }
}
