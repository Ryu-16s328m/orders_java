package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CompaniesEntity;

public interface CompaniesInterface extends JpaRepository<CompaniesEntity, Integer> {

}
