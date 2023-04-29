package com.Deka.MovieSchedule.MovieScheduleService.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Deka.MovieSchedule.MovieScheduleService.Entity.ParkingSlot;
import com.Deka.MovieSchedule.MovieScheduleService.Entity.movieSchedule;
import com.Deka.MovieSchedule.MovieScheduleService.exception.ResourceNotFoundException;
import com.Deka.MovieSchedule.MovieScheduleService.repository.ParkingSlotRepository;
import com.Deka.MovieSchedule.MovieScheduleService.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository ScheduleRepository;
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;

	@Override
	public movieSchedule saveSchedule(movieSchedule movieSchedule)  {
		
		List<movieSchedule> movieSchedules= ScheduleRepository.findByDateAndShowTime( movieSchedule.getDate(), movieSchedule.getShowTime());
		if(!(movieSchedules.size()==0)) {
			throw new RuntimeException("Schedule already booked for movie "+movieSchedules.get(0).getMovieTitle());
		}
//		parkingSlotRepository.save(null)
		
		for(String a:Arrays.asList("A","B","C","D","E","F")) {
			
			for(int i=1;i<11;i++) {
				parkingSlotRepository.save(new ParkingSlot(a+i,movieSchedule.getMovieTitle(),movieSchedule.getDate(),movieSchedule.getShowTime(),true));
			}
		}
		return ScheduleRepository.save(movieSchedule);
	}

	
	@Override
	public List<movieSchedule> getAllSchedule() {
		return ScheduleRepository.findAll();
	}

	@Override
	public movieSchedule getSchedule(String movieTitle) {
		
		return ScheduleRepository.findById(movieTitle)
				.orElseThrow(() -> new ResourceNotFoundException("Movie Title is not found on Server" + movieTitle));
	}

	@Override
	public List<movieSchedule> getScheduleByDate(String date) {
		
		List<movieSchedule> shedules=ScheduleRepository.findByDate(date);
		
		if(shedules.size()==0) {
			
			throw new RuntimeException("No shedulr avaliable for the date"+date);
		}
		
		
		return shedules;
				
				
//				.orElseThrow(() -> new ResourceNotFoundException("no movie shedule avaliable for date" + date));
	
	}


	@Override
	public movieSchedule getScheduleBydateAndTime(String date, String time)  {
		// TODO Auto-generated method stub
		  List<movieSchedule> shList= ScheduleRepository.findByDateAndShowTime(date,time);
		  
		  if(shList.size()==0) {
			  movieSchedule ms=new movieSchedule();
			  ms.setMovieTitle("invalid");
			  return ms ;
		  }
		
		
		return shList.get(0);
	}

}
