package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.OrderProductsEntity;

public interface OrderProductsInterface extends JpaRepository<OrderProductsEntity, Integer>,JpaSpecificationExecutor<OrderProductsEntity> {

}
