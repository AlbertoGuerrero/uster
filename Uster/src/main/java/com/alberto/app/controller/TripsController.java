package com.alberto.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alberto.app.model.Driver;
import com.alberto.app.model.Trip;
import com.alberto.app.model.Vehicle;
import com.alberto.app.service.IDriversService;
import com.alberto.app.service.ITripsService;
import com.alberto.app.service.IVehiclesService;

@Controller
@RequestMapping( value="/trips" )
public class TripsController {
	
	@Autowired
	private ITripsService tripsService;
	
	@Autowired
	private IVehiclesService vehiclesService;
	
	@Autowired
	private IDriversService driversService;
	
	// Método para mostrar todos los vehículos
	@GetMapping(value = "/index")
	public String showVehicles( Model model ) {
		List<Trip> listTrips = tripsService.searchAllTrips();
		if ( listTrips.isEmpty() ) {
			model.addAttribute( "emptyList", "¡La lista de viajes está vacía!" );
		} 
		model.addAttribute( "trips", listTrips );
		return "trips/listTrips";
	}
	
	// Método para mostrar todos los viajes paginados 
		@GetMapping(value = "/indexPaginate")
		public String showDriversPaginate( Model model, Pageable page ) {
			Page<Trip> list = tripsService.searchAllTrips( page );
			model.addAttribute( "trips", list );
			return "trips/listTrips";
		}
	
	// 1º PASO: Selección de una fecha para viajar
	@GetMapping(value = "/addTrip")
	public String crear( @ModelAttribute Trip trip ) {		
		return "trips/firstStep";
	}
	
	// 2º PASO: Selección de un vehículo que esté disponible para la fecha seleccionada
	@PostMapping( value = "/secondStep" )
	public String selectVehicle( @ModelAttribute Trip trip, Model model ) {
		List<Trip> listTrips = tripsService.findByDate( trip.getDate() );
		model.addAttribute( "date", trip.getDate() );
		// Si no hay ningún viaje para la fecha seleccionada se muestran todos los vehículos
		if ( listTrips.isEmpty() ) {
			List<Vehicle> listVehicles = vehiclesService.searchAllVehicles();
			if ( listVehicles.isEmpty() ) {
				model.addAttribute( "emptyList", "¡La lista de vehículos está vacía!" );
			} 
			model.addAttribute( "vehicles", listVehicles );
		} else {
			List<Integer> listOccupiedVehicles = new ArrayList<Integer>();
			for ( Trip t : listTrips ) {
				listOccupiedVehicles.add( t.getVehicle().getId() );
			}
			List<Vehicle> listVehicles = vehiclesService.findByIdNotIn( listOccupiedVehicles );
			if ( listVehicles.isEmpty() ) {
				model.addAttribute( "emptyList", "¡No hay vehículos disponibles!" );
			} 
			model.addAttribute( "vehicles", listVehicles );
		}
		return "trips/secondStep";
	}
	
	// 3º PASO: Selección de un conductor que no tenga programado un viaje para la fecha seleccionada Y que tenga la misma licencia que el vehículo seleccionado
	@GetMapping( value = "/select/{id}/{date}" )
	public String selectDriver( @PathVariable( "id" ) int idVehicle, @PathVariable( "date" ) Date date, Model model ) {
		List<Trip> listTrips = tripsService.findByDate( date );
		model.addAttribute( "date", date );
		model.addAttribute( "idVehicle", idVehicle );
		// Si no hay ningún viaje para la fecha seleccionada obtenemos todos los conductores
		if ( listTrips.isEmpty() ) {
			List<Driver> listDrivers = driversService.searchAllDrivers();
			if ( listDrivers.isEmpty() ) {
				model.addAttribute( "emptyList", "¡La lista de conductores está vacía!" );
			} else {
				// Si hay conductores en la lista, nos quedamos con aquellos que tengan la misma licencia que el vehículo seleccionado
				
				// Obtener licencia del vehículo seleccionado
				Vehicle vehicleLicense = vehiclesService.searchById( idVehicle );
				String licenseRequired = vehicleLicense.getLicenseRequired();
				
				// Obtener conductores con la misma licencia que el vehículo seleccionado
				List<Driver> listFreeDrivers = driversService.findByLicense( licenseRequired );
				// Si no hay conductores mostrar un mensaje y si hay conductores mostrar la lista
				if ( listFreeDrivers.isEmpty() ) {
					model.addAttribute( "emptyList", "¡No hay conductores libres que tengan una licencia para el vehículo y fecha seleccionadas!" );
				} else {
					model.addAttribute( "drivers", listFreeDrivers );
				}
			}
		} else {
			// Si encontramos viajes para la fecha seleccionada, quitamos los conductores que tengan un viaje programado
			List<Integer> listOccupiedDrivers = new ArrayList<Integer>();
			for ( Trip t : listTrips ) {
				listOccupiedDrivers.add( t.getDriver().getId() );
			}
			List<Driver> listDrivers = driversService.findByIdNotIn( listOccupiedDrivers );
			if ( listDrivers.isEmpty() ) {
				model.addAttribute( "emptyList", "¡No hay conductores disponibles!" );
			} else {
				// Obtener licencia del vehículo seleccionado
				Vehicle vehicleLicense = vehiclesService.searchById( idVehicle );
				String licenseRequired = vehicleLicense.getLicenseRequired();
				
				// Obtener conductores con la misma licencia que el vehículo seleccionado
				List<Driver> listFreeDrivers = new ArrayList<Driver>();
				
				for ( Driver d: listDrivers ) {
					if ( d.getLicense().equals( licenseRequired ) ) {
						listFreeDrivers.add( d );
					}
				}
				// Si no hay conductores mostrar un mensaje y si hay conductores mostrar la lista
				if ( listFreeDrivers.isEmpty() ) {
					model.addAttribute( "emptyList", "¡No hay conductores libres que tengan una licencia para el vehículo y fecha seleccionadas!" );
				} else {
					model.addAttribute( "drivers", listFreeDrivers );
				}
			}
		}
		return "trips/thirdStep";
	}
	
	// Crear el viaje seleccionado
	@GetMapping( value = "/select/{idVehicle}/{date}/{idDriver}" )
	public String save( @PathVariable( "idVehicle" ) int idVehicle, @PathVariable( "date" ) Date date, @PathVariable( "idDriver" ) int idDriver, RedirectAttributes attributes ) {
		Trip trip = new Trip();
		
		Vehicle vehicle = vehiclesService.searchById( idVehicle );
		Driver driver = driversService.searchById( idDriver );
		
		trip.setVehicle( vehicle );
		trip.setDriver( driver );
		trip.setDate( date );
		
		tripsService.addTrip( trip );
		attributes.addFlashAttribute( "message", "¡El registro fue guardado con éxito!" );
		return "redirect:/trips/indexPaginate";	
	}
	
	// Se especifica como se debe realizar el Data Binding de la fecha
	@InitBinder
	public void initBinder( WebDataBinder webDataBinder ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
		webDataBinder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, true ) );
	}
}
