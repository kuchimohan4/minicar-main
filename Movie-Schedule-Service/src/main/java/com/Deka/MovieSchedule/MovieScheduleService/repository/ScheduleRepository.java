package com.Deka.MovieSchedule.MovieScheduleService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Deka.MovieSchedule.MovieScheduleService.Entity.movieSchedule;

public interface ScheduleRepository extends JpaRepository<movieSchedule, String> {

	List<movieSchedule> findByDateAndShowTime( String date, String showTime);
	List<movieSchedule> findByDate(String date);
}
