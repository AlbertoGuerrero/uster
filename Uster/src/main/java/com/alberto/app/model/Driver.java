package com.alberto.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Drivers")
public class Driver {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment MySQL
	private int id;
	private String name;
	private String surname;
	private String license;
	
	@OneToMany(mappedBy = "driver")
	private List<Trip> trips;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", surname=" + surname + ", license=" + license + "]";
	}
}