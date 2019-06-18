package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.OrdersEntity;


public interface OrdersInterface extends JpaRepository<OrdersEntity, Integer> {

}