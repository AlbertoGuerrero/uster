package com.alberto.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alberto.app.model.Driver;
import com.alberto.app.repository.DriversRepository;

@Service
public class DriversServiceJPA implements IDriversService {
	
	@Autowired
	private DriversRepository driversRepository;

	@Override
	public void addDriver( Driver driver ) {
		driversRepository.save( driver );
	}

	@Override
	public List<Driver> searchAllDrivers() {
		List<Driver> listDrivers = new ArrayList<Driver>();
		Iterable<Driver> iterableDrivers = driversRepository.findAll();
		for ( Driver d : iterableDrivers ) {
			listDrivers.add( d );
		}
		return listDrivers;	
	}

	@Override
	public Driver searchById( int idDriver ) {
		Optional<Driver> optional = driversRepository.findById( idDriver );
		if ( optional.isPresent() ) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public void deleteDriver( int idDriver ) {
		driversRepository.deleteById( idDriver );
	}

	@Override
	public List<Driver> findByLicense( String license ) {
		return driversRepository.findByLicense( license );
	}

	@Override
	public List<Driver> findByIdNotIn( List<Integer> list ) {
		return driversRepository.findByIdNotIn( list );
	}

	@Override
	public Page<Driver> searchAllDrivers( Pageable page ) {
		return driversRepository.findAll( page );
	}
}
