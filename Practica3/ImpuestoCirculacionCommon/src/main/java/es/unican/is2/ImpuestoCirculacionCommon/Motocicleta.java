package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;
	
	public Motocicleta(String matricula, LocalDate fechaMatriculacion, int cilindrada) {
		super(matricula, fechaMatriculacion);
		this.cilindrada = cilindrada;
	}
    /**
     * Retorna la cilindrada de la motocicleta
     * @return cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		double impu = -1; 
		if (cilindrada < 125) impu = 8.84;
		if (125 <= cilindrada && cilindrada < 250) impu = 15.14;
		if (250 <= cilindrada && cilindrada < 500) impu = 30.30;
		if (500 <= cilindrada && cilindrada < 1000) impu = 60.58;
		if (cilindrada >= 1000) impu = 121.16;
		return impu;
    }
}
