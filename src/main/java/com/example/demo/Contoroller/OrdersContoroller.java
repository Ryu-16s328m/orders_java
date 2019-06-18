package com.example.demo.Contoroller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.OrderProductsEntity;
import com.example.demo.form.RegistForm;
import com.example.demo.form.UpdateForm;
import com.example.demo.form.ViewForm;
import com.example.demo.service.OrderProductsService;

@Controller
public class OrdersContoroller {
	@Autowired
	OrderProductsService oPService;

	
	public ModelAndView getAll() {
		
		List<OrderProductsEntity> orderProductEntityList = oPService.selectAll();
 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("oPEntityList", orderProductEntityList);
		modelAndView.setViewName("/view.html");
 
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders" , method = RequestMethod.GET )
	public ModelAndView view(@RequestParam(value="page", defaultValue="0") int pageNum , ViewForm form,BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		Page<OrderProductsEntity> orderProductEntityList = oPService.getPage(pageNum, form.getSortDate() , form.getoPId() , form.getoPName() , form.getFromPrice() , form.getToPrice() ,form.getCustomerName() , form.getCompanyName());
		List<Integer> ids = oPService.getOPIds();
		modelAndView.addObject("idsList" , ids);
		modelAndView.addObject("sortDate" , form.getSortDate());
		modelAndView.addObject("oPId" , form.getoPId());
		modelAndView.addObject("oPName" , form.getoPName());
		modelAndView.addObject("fromPrice" , form.getFromPrice());
		modelAndView.addObject("toPrice" , form.getToPrice());
		modelAndView.addObject("customerName" , form.getCustomerName());
		modelAndView.addObject("companyName", form.getCompanyName());
		
		modelAndView.addObject("oPEntityList", orderProductEntityList);
		modelAndView.setViewName("/view.html");
		
		return modelAndView;
	}
	
	public OrderProductsEntity getOne(int id) {
		OrderProductsEntity oPEntity = oPService.selectOne(id);
		return oPEntity;
	}
	
	@RequestMapping(value = "/orders/detail" , method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id") int oPId) {
		ModelAndView modelAndView = new ModelAndView();
		OrderProductsEntity oPEntity = getOne(oPId);
		modelAndView.addObject("oPEntity" , oPEntity);
		modelAndView.setViewName("/detail.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/update" , method = RequestMethod.GET)
	public ModelAndView updateView(@ModelAttribute("form") UpdateForm form , @RequestParam(value = "oPId") int oPId) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Integer> ids = oPService.getCIds();
		modelAndView.addObject("idsList" , ids);
		
		OrderProductsEntity oPeEntity = getOne(oPId);
		modelAndView.addObject("oPId" , oPeEntity.getOrderProductsId());
		modelAndView.addObject("oPName" , oPeEntity.getOrderProductsName());
		modelAndView.addObject("oPPrice" , oPeEntity.getPrice());
		modelAndView.addObject("comId" , oPeEntity.getOrdersEntity().getCompanyId());
		modelAndView.addObject("oIName" , oPeEntity.getOrdersEntity().getOrderItemName());
		modelAndView.addObject("oCount" , oPeEntity.getOrdersEntity().getOrderCount());
		modelAndView.addObject("oPrice" , oPeEntity.getOrdersEntity().getOrderPrice());
		modelAndView.addObject("cusName" , oPeEntity.getReceivingOrdersEntity().getCustomerName());
		modelAndView.addObject("cusAddress" , oPeEntity.getReceivingOrdersEntity().getCustomerAddress());
		modelAndView.addObject("cusTel" , oPeEntity.getReceivingOrdersEntity().getCustomerTel());
		
		modelAndView.setViewName("/update.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "orders/updateConfirm" , method = RequestMethod.POST)
	public ModelAndView updateConfirm(@ModelAttribute("form") @Valid UpdateForm form , BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("oPId" , form.getoPId());
		modelAndView.addObject("oPName" , form.getoPName());
		modelAndView.addObject("oPPrice" , form.getoPPrice());
		modelAndView.addObject("comId" , form.getComId());
		modelAndView.addObject("oIName" , form.getoIName());
		modelAndView.addObject("oCount" , form.getoCount());
		modelAndView.addObject("oPrice" , form.getoPrice());
		modelAndView.addObject("cusName" , form.getCusName());
		modelAndView.addObject("cusAddress" , form.getCusAddress());
		modelAndView.addObject("cusTel" , form.getCusTel());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		modelAndView.addObject("updateDate" , timestamp);
		
		if (result.hasErrors()) {
			List<Integer> ids = oPService.getCIds();
			modelAndView.addObject("idsList" , ids);
			modelAndView.setViewName("/update.html");
		}else {
			modelAndView.setViewName("/updateConfirm.html");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "orders/backToUpdateView" , method = RequestMethod.POST)
	public ModelAndView backToUpdateView(@RequestParam(name = "oPId") Integer oPId, 
										 @RequestParam(name = "oPName") String oPName,
										 @RequestParam(name = "oPPrice") Integer oPPrice,
										 @RequestParam(name = "comId") Integer comId,
										 @RequestParam(name = "oIName") String oIName,
										 @RequestParam(name = "oCount") Integer oCount,
										 @RequestParam(name = "oPrice") Integer oPrice,
										 @RequestParam(name = "cusName") String cusName,
										 @RequestParam(name = "cusAddress") String cusAddress,
										 @RequestParam(name = "cusTel") String cusTel,
										 @ModelAttribute("form") @Valid UpdateForm form) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("oPId" , oPId);
		modelAndView.addObject("oPName" , oPName);
		modelAndView.addObject("oPPrice" , oPPrice);
		modelAndView.addObject("comId" , comId);
		modelAndView.addObject("oIName" , oIName);
		modelAndView.addObject("oCount" , oCount);
		modelAndView.addObject("oPrice" , oPrice);
		modelAndView.addObject("cusName" , cusName);
		modelAndView.addObject("cusAddress" , cusAddress);
		modelAndView.addObject("cusTel" , cusTel);
		List<Integer> ids = oPService.getCIds();
		modelAndView.addObject("idsList" , ids);
		modelAndView.setViewName("/update.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/doUpdate" , method = RequestMethod.POST)
	public String doUpdate(UpdateForm form) {
		oPService.doUpdate( form.getoPId(),
						    form.getoPName(),
						    form.getoPPrice(),
						    form.getComId(),
						    form.getoIName(),
						    form.getoCount(),
						    form.getoPrice(),
						    form.getCusName(),
						    form.getCusAddress(),
						    form.getCusTel()      );
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/orders/deleteConfirm" , method = RequestMethod.GET)
	public ModelAndView deleteConfirm(@RequestParam(name = "oPId") int oPId) {
		ModelAndView modelAndView = new ModelAndView();
		OrderProductsEntity oPeEntity = getOne(oPId);
		modelAndView.addObject("oPId" , oPeEntity.getOrderProductsId());
		modelAndView.addObject("oPName" , oPeEntity.getOrderProductsName());
		modelAndView.addObject("oPPrice" , oPeEntity.getPrice());
		modelAndView.addObject("oPUpdateDate", oPeEntity.getUpdateDate());
		modelAndView.addObject("oPRegistDate", oPeEntity.getRegistrationDate());
		modelAndView.addObject("comId" , oPeEntity.getOrdersEntity().getCompanyId());
		modelAndView.addObject("oIName" , oPeEntity.getOrdersEntity().getOrderItemName());
		modelAndView.addObject("oCount" , oPeEntity.getOrdersEntity().getOrderCount());
		modelAndView.addObject("oPrice" , oPeEntity.getOrdersEntity().getOrderPrice());
		modelAndView.addObject("cusName" , oPeEntity.getReceivingOrdersEntity().getCustomerName());
		modelAndView.addObject("cusAddress" , oPeEntity.getReceivingOrdersEntity().getCustomerAddress());
		modelAndView.addObject("cusTel" , oPeEntity.getReceivingOrdersEntity().getCustomerTel());
		modelAndView.setViewName("/deleteConfirm.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/doDelete" , method = RequestMethod.GET)
	public String doDelete(@RequestParam(name = "oPId") int oPId) {
		oPService.doDelete(oPId);
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/orders/regist" , method = RequestMethod.GET)
	public ModelAndView registView(@ModelAttribute("form") RegistForm form) {
		ModelAndView modelAndView = new ModelAndView();
		List<Integer> ids = oPService.getCIds();
		modelAndView.addObject("idsList" , ids);
		modelAndView.setViewName("/regist.html");
		
		modelAndView.addObject("oPPrice");
		modelAndView.addObject("comId");
		modelAndView.addObject("oIName");
		modelAndView.addObject("oCount");
		modelAndView.addObject("oPrice");
		modelAndView.addObject("cusName");
		modelAndView.addObject("cusAddress");
		modelAndView.addObject("cusTel");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/registConfirm" , method = RequestMethod.POST)
	public ModelAndView registConfirm(@ModelAttribute("form") @Valid RegistForm form , BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("oPName" , form.getoPName());
		modelAndView.addObject("oPPrice" , form.getoPPrice());
		modelAndView.addObject("comId" , form.getComId());
		modelAndView.addObject("oIName" , form.getoIName());
		modelAndView.addObject("oCount" , form.getoCount());
		modelAndView.addObject("oPrice" , form.getoPrice());
		modelAndView.addObject("cusName" , form.getCusName());
		modelAndView.addObject("cusAddress" , form.getCusAddress());
		modelAndView.addObject("cusTel" , form.getCusTel());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		modelAndView.addObject("updateDate" , timestamp);
		modelAndView.addObject("registDate" , timestamp);
		
		if (result.hasErrors()) {
			List<Integer> ids = oPService.getCIds();
			modelAndView.addObject("idsList" , ids);
			modelAndView.setViewName("/regist.html");
		}else {
			modelAndView.setViewName("/registConfirm.html");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "orders/backToRegistView" , method = RequestMethod.POST)
	public ModelAndView backToRegistView(@RequestParam(name = "oPId") Integer oPId, 
										 @RequestParam(name = "oPName") String oPName,
										 @RequestParam(name = "oPPrice") Integer oPPrice,
										 @RequestParam(name = "comId") Integer comId,
										 @RequestParam(name = "oIName") String oIName,
										 @RequestParam(name = "oCount") Integer oCount,
										 @RequestParam(name = "oPrice") Integer oPrice,
										 @RequestParam(name = "cusName") String cusName,
										 @RequestParam(name = "cusAddress") String cusAddress,
										 @RequestParam(name = "cusTel") String cusTel,
										 @ModelAttribute("form") @Valid RegistForm form) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("oPId" , oPId);
		modelAndView.addObject("oPName" , oPName);
		modelAndView.addObject("oPPrice" , oPPrice);
		modelAndView.addObject("comId" , comId);
		modelAndView.addObject("oIName" , oIName);
		modelAndView.addObject("oCount" , oCount);
		modelAndView.addObject("oPrice" , oPrice);
		modelAndView.addObject("cusName" , cusName);
		modelAndView.addObject("cusAddress" , cusAddress);
		modelAndView.addObject("cusTel" , cusTel);
		List<Integer> ids = oPService.getCIds();
		modelAndView.addObject("idsList" , ids);
		modelAndView.setViewName("/regist.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/doRegist" , method = RequestMethod.POST)
	public String doRegist(RegistForm form , BindingResult result) {
		
		oPService.doRegist(form.getoPName(), form.getoPPrice(), form.getComId(), form.getoIName(), form.getoCount(), form.getoPrice(), form.getCusName(), form.getCusAddress(), form.getCusTel());
		
		return "redirect:/orders";
	}
	
	
}