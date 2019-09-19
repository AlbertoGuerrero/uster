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

import com.alberto.app.model.Vehicle;
import com.alberto.app.service.IVehiclesService;

@Controller
@RequestMapping( value="/vehicles" )
public class VehiclesController {
	
	@Autowired
	private IVehiclesService vehiclesService;
	
	// M�todo para mostrar todos los veh�culos
	@GetMapping(value = "/index")
	public String showVehicles( Model model ) {
		List<Vehicle> listVehicles = vehiclesService.searchAllVehicles();
		if ( listVehicles.isEmpty() ) {
			model.addAttribute( "emptyList", "�La lista de veh�culos est� vac�a!" );
		} 
		model.addAttribute( "vehicles", listVehicles );
		return "vehicles/listVehicles";
	}
	
	// M�todo para mostrar todos los veh�culos paginados 
	@GetMapping(value = "/indexPaginate")
	public String showVehiclesPaginate( Model model, Pageable page ) {
		Page<Vehicle> list = vehiclesService.searchAllTrips( page );
		model.addAttribute( "vehicles", list );
		return "vehicles/listVehicles";
	}
	
	// M�todo para mostrar el formulario para a�adir un veh�culo nuevo
	@GetMapping(value = "/create")
	public String crear( @ModelAttribute Vehicle vehicle ) {		
		return "vehicles/formVehicle";
	}
	
	// M�todo para guardar un veh�culo
	@PostMapping( "/save" )
	public String save(@ModelAttribute Vehicle vehicle, BindingResult result, RedirectAttributes attributes ) {
		if ( result.hasErrors() ) {
			return "vehicles/formVehicle";
		}
		
		vehiclesService.addVehicle( vehicle );
		attributes.addFlashAttribute( "message", "�El registro fue guardado con �xito!" );
		return "redirect:/vehicles/indexPaginate";
	}
	
	// M�todo para editar un veh�culo
	@GetMapping(value = "/edit/{id}")
	public String edit( @PathVariable( "id" ) int idVehicle, Model model ) {		
		Vehicle vehicle = vehiclesService.searchById( idVehicle );
		model.addAttribute( "vehicle", vehicle );
		return "vehicles/formVehicle";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete( @PathVariable( "id" ) int idVehicle, RedirectAttributes attributes ) {	
		vehiclesService.deleteVehicle( idVehicle );	
		attributes.addFlashAttribute( "message", "�El registro fue borrado con �xito!" );
		return "redirect:/vehicles/indexPaginate";
	}
}
