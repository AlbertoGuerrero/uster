package com.alberto.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alberto.app.model.Driver;
import com.alberto.app.service.IDriversService;

@Controller
@RequestMapping( value="/drivers" )
public class DriversController {
	
	@Autowired
	private IDriversService driversService;
	
	// Método para mostrar todos los conductores
	@GetMapping(value = "/index")
	public String showVehicles( Model model ) {
		List<Driver> listDrivers = driversService.searchAllDrivers();
		if ( listDrivers.isEmpty() ) {
			model.addAttribute( "emptyList", "¡La lista de conductores está vacía!" );
		} 
		model.addAttribute( "drivers", listDrivers );
		return "drivers/listDrivers";
	}
	
	// Método para mostrar todos los conductores paginados 
	@GetMapping(value = "/indexPaginate")
	public String showDriversPaginate( Model model, Pageable page ) {
		Page<Driver> list = driversService.searchAllDrivers( page );
		model.addAttribute( "drivers", list );
		return "drivers/listDrivers";
	}
	
	// Método para mostrar el formulario para añadir un conductor nuevo
	@GetMapping(value = "/create")
	public String crear( @ModelAttribute Driver driver ) {		
		return "drivers/formDriver";
	}
	
	// Método para guardar un conductor
	@PostMapping( "/save" )
	public String save(@ModelAttribute Driver driver, BindingResult result, RedirectAttributes attributes ) {
		if ( result.hasErrors() ) {
			return "drivers/formDriver";
		}
		
		driversService.addDriver( driver );
		attributes.addFlashAttribute( "message", "¡El registro fue guardado con éxito!" );
		return "redirect:/drivers/indexPaginate";
	}
	
	// Método para editar un vehículo
	@GetMapping(value = "/edit/{id}")
	public String edit( @PathVariable( "id" ) int idDriver, Model model ) {		
		Driver driver = driversService.searchById( idDriver );
		model.addAttribute( "driver", driver );
		return "drivers/formDriver";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete( @PathVariable( "id" ) int idDriver, RedirectAttributes attributes ) {	
		driversService.deleteDriver( idDriver );	
		attributes.addFlashAttribute( "message", "¡El registro fue borrado con éxito!" );
		return "redirect:/drivers/indexPaginate";
	}
}