package es.unican.is2.ImpuestoCirculacionBusiness;

import java.util.LinkedList;
import java.util.List;
import es.unican.is2.ImpuestoCirculacionCommon.*;
/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	/**
	 * Se anhade un contribuyente si ya existe se comunica el error.
	 */
	public Contribuyente altaContribuyente(Contribuyente c) {
		// TODO
		return contribuyentes.creaContribuyente(c);
		
	}

	
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		// TODO
		Contribuyente c = contribuyentes.contribuyente(dni);
		if (c == null) {
			throw new OperacionNoValida("No se encuentra al cliente");
		}
		if (c.getVehiculos().size() != 0) {
			throw new OperacionNoValida("El cliente tiene vehiculos asociados");
		}
		return contribuyentes.eliminaContribuyente(dni);
	 }
	
	public Contribuyente contribuyente(String dni) {
		// TODO
		return contribuyentes.contribuyente(dni);
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		// TODO
		if (vehiculos.vehiculo(v.getMatricula()) != null) {
			throw new OperacionNoValida("Existe un vehiculo dado de alta con esa matricula en la BBDD");
		}
		Contribuyente c = contribuyentes.contribuyente(dni);
		if (c == null) {
			throw new OperacionNoValida("No existe ningun contribuyente con ese dni en la BBDD");
		}
		List<Vehiculo> list_v_cont = c.getVehiculos();
		for (int i = 0; i < list_v_cont.size(); i++) {
			if (list_v_cont.get(i).getMatricula().equals(v.getMatricula())) {
				throw new OperacionNoValida("Ya tiene este vehiculo el contribuyente");
			}
		}
		list_v_cont.add(v);
		return vehiculos.creaVehiculo(v);
	}

	@Override
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		// TODO
		Vehiculo v = vehiculos.vehiculo(matricula); 
		if ( v == null) {
			throw new OperacionNoValida("No existe un vehiculo con esa matricula en la BBDD");
		}
		Contribuyente c = contribuyentes.contribuyente(dni);
		if (c == null) {
			throw new OperacionNoValida("No existe ningun contribuyente con ese dni en la BBDD");
		}
		List<Vehiculo> list_v_cont = c.getVehiculos();
		for (int i = 0; i < list_v_cont.size(); i++) {
			if (list_v_cont.get(i).getMatricula().equals(v.getMatricula())) {
				list_v_cont.remove(list_v_cont.get(i));
				return vehiculos.eliminaVehiculo(v.getMatricula());
			}
		}
		throw new OperacionNoValida("Este vehiculo no esta asignado a este contribuyente");
	}

	/**
	 * retorna el vehiculo
	 * Si no existe el vehiculo retorna null
	 */
	@Override
	public Vehiculo vehiculo(String matricula) {
		// TODO
		return vehiculos.vehiculo(matricula);
	}	
}

