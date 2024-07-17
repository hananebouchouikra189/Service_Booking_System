package com.codeElevate.ServiceBookingSystem.service.client;

import java.util.List;

import com.codeElevate.ServiceBookingSystem.dto.AdDTO;
import com.codeElevate.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.dto.ReviewDTO;

public interface ClientService {
	public List<AdDTO> getAllAds();
	public List<AdDTO> searchAdByName(String name);
	public boolean bookService(ReservationDTO reservationDTO);
	public AdDetailsForClientDTO getAdDetailsByAdId(Long adId);
	List<ReservationDTO> getAllBookingsByUserId(Long userId);
	Boolean giveReview(ReviewDTO reviewDTO);

}
