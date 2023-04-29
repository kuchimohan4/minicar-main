package com.drivein.repository;

import org.springframework.stereotype.Repository;

import com.drivein.entities.userDetails;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface uderDetailsRepository extends JpaRepository<userDetails,String> {

}
