package com.alberto.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment MySQL
	private int id;
	private String brand;
	private String model;
	private String plate;
	private String licenseRequired;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Trip> trips;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	public String getLicenseRequired() {
		return licenseRequired;
	}
	
	public void setLicenseRequired(String licenseRequired) {
		this.licenseRequired = licenseRequired;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", brand=" + brand + ", model=" + model + ", plate=" + plate + ", licenseRequired="
				+ licenseRequired + "]";
	}
}
