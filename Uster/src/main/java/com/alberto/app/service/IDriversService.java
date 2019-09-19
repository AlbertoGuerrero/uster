package com.alberto.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alberto.app.model.Driver;

public interface IDriversService {
	void addDriver( Driver driver );
	List<Driver> searchAllDrivers();
	Page<Driver> searchAllDrivers( Pageable page );
	Driver searchById( int idDriver );
	void deleteDriver( int idDriver );
	List<Driver> findByLicense( String license );
	List<Driver> findByIdNotIn( List<Integer> list );
}
