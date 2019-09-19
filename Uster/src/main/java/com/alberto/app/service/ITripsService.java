package com.alberto.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alberto.app.model.Trip;

public interface ITripsService {
	void addTrip( Trip trip );
	List<Trip> searchAllTrips();
	Page<Trip> searchAllTrips( Pageable page );
	List<Trip> findByDate( Date date );
}
