package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ReceivingOrdersEntity;

public interface ReceivingOrdersInterface extends JpaRepository<ReceivingOrdersEntity, Integer> {

}
