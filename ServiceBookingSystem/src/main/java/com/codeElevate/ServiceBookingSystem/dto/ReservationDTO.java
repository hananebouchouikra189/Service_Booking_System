package com.codeElevate.ServiceBookingSystem.dto;

import java.util.Date;

import com.codeElevate.ServiceBookingSystem.enums.ReservationStatus;
import com.codeElevate.ServiceBookingSystem.enums.ReviewStatus;

import lombok.Data;
@Data
public class ReservationDTO {

	private Long id;
	private Date bookDate;
	private String serviceName;
	private ReservationStatus reservationstatus ;
	private ReviewStatus reviewStatus;
	private Long userId;
	private String userName;
	private Long companyId;
	private Long adId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ReservationStatus getReservationstatus() {
		return reservationstatus;
	}
	public void setReservationstatus(ReservationStatus reservationstatus) {
		this.reservationstatus = reservationstatus;
	}
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	
}
