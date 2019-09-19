package com.alberto.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trips")
public class Trip {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment MySQL
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "idVehicle")
	private Vehicle vehicle;
	
	@ManyToOne
    @JoinColumn(name = "idDriver")
	private Driver driver;
	
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
