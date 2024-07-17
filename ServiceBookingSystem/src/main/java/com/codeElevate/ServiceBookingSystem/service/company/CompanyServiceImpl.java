package com.codeElevate.ServiceBookingSystem.service.company;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeElevate.ServiceBookingSystem.dto.AdDTO;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.entity.Ad;
import com.codeElevate.ServiceBookingSystem.entity.Reservation;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.ReservationStatus;
import com.codeElevate.ServiceBookingSystem.repository.AdRepository;
import com.codeElevate.ServiceBookingSystem.repository.ReservationRepository;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;

@Service
public class CompanyServiceImpl  implements CompanyService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdRepository adRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
		  Optional<User> optionalUser = userRepository.findById(userId);
		  if (optionalUser.isPresent()) {
		    Ad ad = new Ad();
		    ad.setServicename(adDTO.getServiceName());
		    ad.setDescription(adDTO.getDescription());
		    if (adDTO.getImg() != null) {
		      ad.setImg(adDTO.getImg().getBytes());
		      System.out.println("Image size: " + adDTO.getImg().getBytes().length);
		    } else {
		      System.out.println("Image is null");
		    }
		    ad.setPrice(adDTO.getPrice());
		    ad.setUser(optionalUser.get());
		    adRepository.save(ad);
		    return true;
		  }
		  return false;
		}


	@Override
	public List<AdDTO> getAllAds(Long userId) {
	  List<Ad> ads = adRepository.findAllByUserId(userId);
	  return ads.stream().map(ad -> {
	    AdDTO dto = ad.getAdDto();
	    dto.setReturneImg(ad.getImg()); // Ensure the image data is set correctly
	    return dto;
	  }).collect(Collectors.toList());
	}

	public AdDTO getAdById(Long adId) {
		Optional<Ad> optionalAd=adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			return optionalAd.get().getAdDto();
		}
		return null;
		
	}
	
	public boolean updateAd(Long adId,AdDTO adDTO) throws IOException {
		Optional<Ad> optionalAd=adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			Ad ad=optionalAd.get();
			ad.setServicename(adDTO.getServiceName());
			ad.setDescription(adDTO.getDescription());
			ad.setPrice(adDTO.getPrice());
			if(adDTO.getImg()!=null) {
				ad.setImg(adDTO.getImg().getBytes());
			}
			adRepository.save(ad);
			return true;
		}else
		return false;
		
	}
	
	public boolean deleteAd(Long adId) {
		Optional<Ad> optionalAd=adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			adRepository.delete(optionalAd.get());
			return true;
		}
		return false;
	}

	public List<ReservationDTO> getAllBooking(Long companyId){
		return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	public boolean changeBookingStatus(Long bookingId,String status) {
		Optional<Reservation> optionalReservation=reservationRepository.findById(bookingId);
		if(optionalReservation.isPresent()) {
			Reservation existingReservation=optionalReservation.get();
			if(Objects.equals(existingReservation, "Approve")) {
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			}else {
				existingReservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			reservationRepository.save(existingReservation);
			return true;
		}
		return false;
	}
}
