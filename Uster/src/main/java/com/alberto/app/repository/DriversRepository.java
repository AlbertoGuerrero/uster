package com.alberto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.app.model.Driver;

@Repository( "DriversRepository" ) 
public interface DriversRepository extends JpaRepository<Driver, Integer> {
	List<Driver> findByLicense( String license );
	List<Driver> findByIdNotIn( List<Integer> list );
}
