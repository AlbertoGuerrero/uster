package com.alberto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.app.model.Vehicle;

@Repository( "VehiclesRepository" ) 
public interface VehiclesRepository extends JpaRepository<Vehicle, Integer> {
	List<Vehicle> findByIdNotIn( List<Integer> list );
}
