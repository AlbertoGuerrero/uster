package com.alberto.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alberto.app.model.Vehicle;

public interface IVehiclesService {
	void addVehicle( Vehicle vehicle );
	List<Vehicle> searchAllVehicles();
	Page<Vehicle> searchAllTrips( Pageable page );
	Vehicle searchById( int idVehicle );
	void deleteVehicle( int idVehicle );
	List<Vehicle> findByIdNotIn( List<Integer> list );
}
