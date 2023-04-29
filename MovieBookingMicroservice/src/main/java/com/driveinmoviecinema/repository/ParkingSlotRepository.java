package com.driveinmoviecinema.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driveinmoviecinema.models.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {

	
	List<ParkingSlot> findByDateAndPlayTimeAndAvliable(String Date,String playTime,Boolean avliable);
	List<ParkingSlot> findBySlotAndDateAndPlayTime(String slot,String date,String playtime);
	
	
}
