package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CompaniesEntity;
import com.example.demo.entity.OrderProductsEntity;
import com.example.demo.entity.OrdersEntity;
import com.example.demo.entity.ReceivingOrdersEntity;
import com.example.demo.interFace.CompaniesInterface;
import com.example.demo.interFace.OrderProductsInterface;
import com.example.demo.interFace.OrdersInterface;
import com.example.demo.interFace.ReceivingOrdersInterface;

@Repository
public class OrderProductsRepository {
	
	@Autowired
	OrderProductsInterface orderProductsInterface;
	@Autowired
	OrdersInterface ordersInterface;
	@Autowired
	ReceivingOrdersInterface receivingOrdersInterface;
	@Autowired
	CompaniesInterface companiesInterface;
	
	public List<OrderProductsEntity> selectAll() {
		List<OrderProductsEntity> orderProductsEntityList = orderProductsInterface.findAll();
		return orderProductsEntityList;
	}
	
	public OrderProductsEntity selectOne(int id) {
		OrderProductsEntity oPEntity = orderProductsInterface.getOne(id);
		return oPEntity;
	}
	
	public List<CompaniesEntity> getIds() {
		List<CompaniesEntity> companiesEntity = companiesInterface.findAll();
		return companiesEntity;
	}
	
	public Page<OrderProductsEntity> getPage(int pageNum , int sortDate , int oPid , String productName , Integer fromPrice , Integer toPrice , String customerName , String companyName) {
		
		Sort sort;
		if(sortDate == 2) {
			sort = new Sort(Direction.ASC, "registrationDate");
		}else {
			sort = new Sort(Direction.DESC, "registrationDate");
		}
		Pageable pageable = PageRequest.of(pageNum, 3, sort);
		
        Specification<OrderProductsEntity> searchConditions = Specification.where((Specification<OrderProductsEntity>) null);
        if(oPid > 0) {
        	Specification<OrderProductsEntity> searchId = (root, query, cb) -> cb.equal(root.get("orderProductsId"), oPid);
        	searchConditions = searchConditions.and(searchId);
        }
        if((productName != null) && (!productName.isEmpty())) {
        	Specification<OrderProductsEntity> searchProductName = (root, query, cb) -> cb.like(root.get("orderProductsName"), "%"+ productName + "%");
        	searchConditions = searchConditions.and(searchProductName);
        }
        if((fromPrice != null)) {
        	Specification<OrderProductsEntity> searchFromPrice = (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), fromPrice);
        	searchConditions = searchConditions.and(searchFromPrice);
        }
        if((toPrice != null)) {
        	Specification<OrderProductsEntity> searchToPrice = (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), toPrice);
        	searchConditions = searchConditions.and(searchToPrice);
        }
        if((customerName != null) && (!customerName.isEmpty())) {
        	Specification<OrderProductsEntity> searchCustomerName = (root, query, cb) -> cb.like(root.get("receivingOrdersEntity").get("customerName"), "%"+ customerName + "%");
        	searchConditions = searchConditions.and(searchCustomerName);
        }
        if((companyName != null) && (!companyName.isEmpty())) {
        	Specification<OrderProductsEntity> searchCompanyName = (root, query, cb) -> cb.like(root.get("ordersEntity").get("companiesEntity").get("companyName"), "%"+ companyName + "%");
        	searchConditions = searchConditions.and(searchCompanyName);
        }
		Page<OrderProductsEntity> oPEntityList = orderProductsInterface.findAll(searchConditions , pageable);
		
		return oPEntityList;
	}
	
	public void doUpdate(int oPId , String oPName , String OPPrice , int comId , String oIName , String oCount , String oPrice , String cusName , String cusAddress , String cusTel) {
		OrderProductsEntity oPEntity = orderProductsInterface.getOne(oPId);
		
		oPEntity.setOrderProductsName(oPName);
		oPEntity.setPrice(Integer.parseInt(OPPrice));
		oPEntity.getOrdersEntity().setCompanyId(comId);
		oPEntity.getOrdersEntity().setOrderItemName(oIName);
		oPEntity.getOrdersEntity().setOrderCount(Integer.parseInt(oCount));
		oPEntity.getOrdersEntity().setOrderPrice(Integer.parseInt(oPrice));
		oPEntity.getReceivingOrdersEntity().setCustomerName(cusName);
		oPEntity.getReceivingOrdersEntity().setCustomerAddress(cusAddress);
		oPEntity.getReceivingOrdersEntity().setCustomerTel(cusTel);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		oPEntity.setUpdateDate(timestamp);
		
		orderProductsInterface.save(oPEntity);
	}
	
	public void doDelete(int oPId) {
		ordersInterface.delete(ordersInterface.getOne(oPId));
		receivingOrdersInterface.delete(receivingOrdersInterface.getOne(oPId));
		orderProductsInterface.delete(orderProductsInterface.getOne(oPId));
	}
	
	public void doRegist(String oPName , String oPPrice , int comId , String oIName , String oCount , String oPrice , String cusName , String cusAddress , String cusTel) {
		OrderProductsEntity orderProductsEntity = new OrderProductsEntity();
		OrdersEntity ordersEntity = new OrdersEntity();
		ReceivingOrdersEntity receivingOrdersEntity = new ReceivingOrdersEntity();
		
		
		orderProductsEntity.setOrderProductsName(oPName);
		orderProductsEntity.setPrice(Integer.parseInt(oPPrice));
		orderProductsEntity.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
		orderProductsEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		
		ordersEntity.setCompanyId(comId);
		ordersEntity.setOrderItemName(oIName);
		ordersEntity.setOrderCount(Integer.parseInt(oCount));
		ordersEntity.setOrderPrice(Integer.parseInt(oPrice));
		
		receivingOrdersEntity.setCustomerName(cusName);
		receivingOrdersEntity.setCustomerAddress(cusAddress);
		receivingOrdersEntity.setCustomerTel(cusTel);
		
		
		orderProductsEntity.setOrdersEntity(ordersEntity);
		orderProductsEntity.setReceivingOrdersEntity(receivingOrdersEntity);
		
		orderProductsInterface.save(orderProductsEntity);
	}
	
	
	
	
	
	
	
	

}