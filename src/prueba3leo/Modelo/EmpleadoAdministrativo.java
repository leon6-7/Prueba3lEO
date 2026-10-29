
package prueba3leo.Modelo;

/**
 *
 * @author Abril
 */
public class EmpleadoAdministrativo extends Empleado{
    private double bonificacion;
    private double descuento;
    public EmpleadoAdministrativo(String codigo, String nombre, double SalarioBase){
    super(codigo, nombre, SalarioBase);
    
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(double bonificacion) {
        if(bonificacion>0){
        this.bonificacion = bonificacion;}
        
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        if(descuento>0){
        this.descuento = descuento;}
    }
    
    @Override
    public double calcularSalario(){
    return (this.SalarioBase+bonificacion)*((1-descuento)/100);
    
    }
}
