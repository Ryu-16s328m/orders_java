package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class RegistForm {
	@NotBlank(message = "{blankMessage}")
	private String oPName;
	@NotBlank(message = "{blankMessage}")
	@Pattern(regexp = "^[0-9]*$" , message = "{intMessage}")
	private String oPPrice;
	private int comId;
	@NotBlank(message = "{blankMessage}")
	private String oIName;
	@NotBlank(message = "{blankMessage}")
	@Pattern(regexp = "^[0-9]*$" , message = "{intMessage}")
	private String oCount;
	@NotBlank(message = "{blankMessage}")
	@Pattern(regexp = "^[0-9]*$" , message = "{intMessage}")
	private String oPrice;
	@NotBlank(message = "{blankMessage}")
	private String cusName;
	@NotBlank(message = "{blankMessage}")
	private String cusAddress;
	@Pattern(regexp = "^[0-9]*$" , message = "{intMessage}")
	private String cusTel;
	public String getoPName() {
		return oPName;
	}
	public void setoPName(String oPName) {
		this.oPName = oPName;
	}
	public String getoPPrice() {
		return oPPrice;
	}
	public void setoPPrice(String oPPrice) {
		this.oPPrice = oPPrice;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getoIName() {
		return oIName;
	}
	public void setoIName(String oIName) {
		this.oIName = oIName;
	}
	public String getoCount() {
		return oCount;
	}
	public void setoCount(String oCount) {
		this.oCount = oCount;
	}
	public String getoPrice() {
		return oPrice;
	}
	public void setoPrice(String oPrice) {
		this.oPrice = oPrice;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	
}
