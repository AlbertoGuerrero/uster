package com.alberto.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alberto.app.model.Trip;
import com.alberto.app.repository.TripsRepository;

@Service
public class TripsServiceJPA implements ITripsService {
	
	@Autowired
	private TripsRepository tripsRepository;
	
	@Override
	public List<Trip> searchAllTrips() {
		List<Trip> listTrips = new ArrayList<Trip>();
		Iterable<Trip> iterableTrips = tripsRepository.findAll();
		for ( Trip t : iterableTrips ) {
			listTrips.add( t );
		}
		return listTrips;
	}

	@Override
	public void addTrip( Trip trip ) {
		tripsRepository.save( trip );
	}

	@Override
	public List<Trip> findByDate( Date date ) {
		return tripsRepository.findByDate( date );
	}

	@Override
	public Page<Trip> searchAllTrips( Pageable page ) {
		return tripsRepository.findAll( page );
	}
}
