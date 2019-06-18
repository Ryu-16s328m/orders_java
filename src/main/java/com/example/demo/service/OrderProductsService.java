package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CompaniesEntity;
import com.example.demo.entity.OrderProductsEntity;
import com.example.demo.repository.OrderProductsRepository;

@Service
public class OrderProductsService {
	
	@Autowired
	OrderProductsRepository orderProductsRepository;
	
	public List<OrderProductsEntity> selectAll() {
		// リポジトリのselectAll()メソッドを呼び出す
		List<OrderProductsEntity> orderProductsEntityList = orderProductsRepository.selectAll();
 
		return orderProductsEntityList;
	}
	
	public OrderProductsEntity selectOne(int id) {
		OrderProductsEntity oPEntity = orderProductsRepository.selectOne(id);
		return oPEntity;
	}
	
	public List<Integer> getCIds() {
		List<CompaniesEntity> companiesEntitiesList = orderProductsRepository.getIds();
		ArrayList<Integer> cIds = new ArrayList<>();
		for(CompaniesEntity entity:companiesEntitiesList) {
			cIds.add(entity.getCompanyId());
		}
		return cIds;
	}
	
	public Page<OrderProductsEntity> getPage(int pageNum , int sortDate , int oPid , String productName , Integer fromPrice , Integer toPrice ,String customerName , String companyName) {
		Page<OrderProductsEntity> oPEntityList = orderProductsRepository.getPage(pageNum, sortDate, oPid, productName, fromPrice, toPrice, customerName , companyName);

		return oPEntityList;
	}
	
	public List<Integer> getOPIds(){
		List<OrderProductsEntity> oPEntityList = orderProductsRepository.selectAll();
		ArrayList<Integer> oPIds = new ArrayList<>();
		for(OrderProductsEntity entity:oPEntityList) {
			oPIds.add(entity.getOrderProductsId());
		}
		return oPIds;
	}
	
	public void doUpdate(int oPId , String oPName , String OPPrice , int comId , String oIName , String oCount , String oPrice , String cusName , String cusAddress , String cusTel) {
		orderProductsRepository.doUpdate(oPId, oPName, OPPrice, comId, oIName, oCount, oPrice, cusName, cusAddress, cusTel);
	}
	
	public void doDelete(int oPId) {
		orderProductsRepository.doDelete(oPId);
	}
	
	public void doRegist(String oPName , String oPPrice , int comId , String oIName , String oCount , String oPrice , String cusName , String cusAddress , String cusTel) {
		orderProductsRepository.doRegist(oPName, oPPrice, comId, oIName, oCount, oPrice, cusName, cusAddress, cusTel);
	}
}
