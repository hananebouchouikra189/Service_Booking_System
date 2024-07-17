package com.codeElevate.ServiceBookingSystem.dto;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AdDTO {

	private Long id;
	private String serviceName;
	private String description;
	private Double price;
	private MultipartFile img;
	private byte[] ReturneImg;
	private Long userId;
	private String companyName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public byte[] getReturneImg() {
		return ReturneImg;
	}
	public void setReturneImg(byte[] img) {
		this.ReturneImg = img;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String comapnyName) {
		this.companyName = comapnyName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}

	
	

}
