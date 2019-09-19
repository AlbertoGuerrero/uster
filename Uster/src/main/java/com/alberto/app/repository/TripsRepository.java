package com.alberto.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.app.model.Trip;

@Repository( "TripsRepository" ) 
public interface TripsRepository extends JpaRepository<Trip, Integer> {
	List<Trip> findByDate( Date date );
}
