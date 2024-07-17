package com.codeElevate.ServiceBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeElevate.ServiceBookingSystem.dto.AdDTO;
import com.codeElevate.ServiceBookingSystem.entity.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {

	List<Ad> findAllByUserId(Long userId);
	List<Ad> findAllByServiceNameContaining(String name);
}
