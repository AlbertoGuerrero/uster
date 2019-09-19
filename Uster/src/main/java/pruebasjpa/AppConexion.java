package pruebasjpa;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alberto.app.model.Vehicle;
import com.alberto.app.repository.VehiclesRepository;

public class AppConexion {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		VehiclesRepository repo = context.getBean( "VehiclesRepository", VehiclesRepository.class );	
		
		// Guardar un vehículo
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setBrand( "BMW" );
		vehicle1.setModel( "Serie 1" );
		vehicle1.setPlate( "4584NAH" );
		vehicle1.setLicenseRequired( "A" );
		repo.save( vehicle1 );
		
		// Leer un vehículo
		Optional<Vehicle> vehicle2 = repo.findById( 1 );
		System.out.println( vehicle2.get() );
		
		// Actualizar un vehículo
		Optional<Vehicle> optional = repo.findById( 1 );
		if ( optional.isPresent() ) {
			Vehicle vehicle = optional.get();
			System.out.println( "Antes de actualizar:" + vehicle );
			vehicle.setPlate( "111AA" );
			System.out.println( "Despues de actualizar:" + vehicle );
			repo.save( vehicle );
		}
	
		// Borrar un vehículo
		int idVehicle = 1;
		
		if ( repo.existsById( idVehicle ) ) {
			repo.deleteById( idVehicle );
		}	
		context.close();
	}
}