package com.alberto.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alberto.app.model.Vehicle;
import com.alberto.app.repository.VehiclesRepository;

@Service
public class VehiclesServiceJPA implements IVehiclesService {
	
	@Autowired
	private VehiclesRepository vehiclesRepository;
	
	@Override
	public void addVehicle( Vehicle vehicle ) {
		vehiclesRepository.save( vehicle );
	}
	
	@Override
	public List<Vehicle> searchAllVehicles() {
		List<Vehicle> listVehicles = new ArrayList<Vehicle>();
		Iterable<Vehicle> iterableVehicles = vehiclesRepository.findAll();
		for ( Vehicle v : iterableVehicles ) {
			listVehicles.add( v );
		}
		return listVehicles;
	}

	@Override
	public Vehicle searchById( int idVehicle ) {
		Optional<Vehicle> optional = vehiclesRepository.findById( idVehicle );
		if ( optional.isPresent() ) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteVehicle( int idVehicle ) {
		vehiclesRepository.deleteById( idVehicle );
	}

	@Override
	public List<Vehicle> findByIdNotIn( List<Integer> list ) {
		return vehiclesRepository.findByIdNotIn( list );
	}

	@Override
	public Page<Vehicle> searchAllTrips( Pageable page ) {
		return vehiclesRepository.findAll( page );
	}
}