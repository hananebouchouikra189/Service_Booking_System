package com.codeElevate.ServiceBookingSystem.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codeElevate.ServiceBookingSystem.dto.AdDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ads")
@Data
public class Ad {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String serviceName;
	private String description;
	private Double price;
	@Lob
	@Column(columnDefinition="longblob")
	private byte[] img;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="user_id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServicename() {
		return serviceName;
	}
	public void setServicename(String servicename) {
		this.serviceName = servicename;
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AdDTO getAdDto() {
		  AdDTO adDTO = new AdDTO();
		  adDTO.setId(id);
		  adDTO.setDescription(description);
		  adDTO.setPrice(price);
		  adDTO.setServiceName(serviceName);
		  adDTO.setCompanyName(user.getName());
		  adDTO.setReturneImg(img);
		  return adDTO;
		}
	
}
