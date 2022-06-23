package es.unican.is2.ImpuestoCirculacionCommon;
import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Furgoneta
    extends Turismo implements Serializable
{
    
    private boolean comercial;
    public Furgoneta (String matricula, LocalDate fecha, double potencia) {
    	super(matricula, fecha, potencia);
    	this.comercial = true;
    }
   /**
    * Retorna el valor del atributo comercial
    * @return true si la furgoneta es de uso comercial
    *         false si no es de uso comercial
    */
    public boolean getComercial() {
    	return comercial;
    }
       
	@Override
    public double precioImpuesto() {
		double impu = -1; 
		if (this.getPotencia() < 8) impu = 25.24;
		if (8 <= this.getPotencia() && this.getPotencia() <= 11.99) impu = 68.16;
		if (12 <= this.getPotencia() && this.getPotencia() <= 15.99) impu = 143.88;
		if (16 <= this.getPotencia() && this.getPotencia() <= 19.99) impu = 179.22;
		if (this.getPotencia() >= 20) impu = 224.00;
		return (comercial) ? impu + (0.2 * impu) : impu;
    	
    }
}
