package com.codeElevate.ServiceBookingSystem.service.company;

import java.io.IOException;
import java.util.List;

import com.codeElevate.ServiceBookingSystem.dto.AdDTO;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;

public interface CompanyService {

	public boolean postAd(Long userId,AdDTO adDTO) throws IOException;
	public List<AdDTO> getAllAds(Long userId);
	public AdDTO getAdById(Long adId);
	public boolean updateAd(Long adId,AdDTO adDTO) throws IOException ;
	public boolean deleteAd(Long adId);
	List<ReservationDTO> getAllBooking(Long companyId);
	boolean changeBookingStatus(Long bookingId,String status);
}
