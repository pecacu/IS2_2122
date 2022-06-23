package es.unican.is2.ImpuestoCirculacionCommon;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Furgoneta
    extends Turismo implements Serializable
{
    
    private double potencia;
    private boolean comercial;
    
   /**
    * Retorna el valor del atributo comercial
    * @return true si la furgoneta es de uso comercial
    *         false si no es de uso comercial
    */
    public boolean getComercial() {
    	return comercial;
    }
    
    /**
	 * Retorna la potencia de la furgoneta
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
    
  
	@Override
    public double precioImpuesto() {
		double impu = -1; 
		if (potencia < 8) impu = 25.24;
		if (8 <= potencia && potencia <= 11.99) impu = 68.16;
		if (12 <= potencia && potencia <= 15.99) impu = 143.88;
		if (16 <= potencia && potencia <= 19.99) impu = 179.22;
		if (potencia >= 20) impu = 224.00;
		return (comercial) ? impu + (0.2 * impu) : impu;
    	
    }
}
