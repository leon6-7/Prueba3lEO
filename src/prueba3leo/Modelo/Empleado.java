/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Modelo;

/**
 *
 * @author Abril
 */
public abstract class Empleado {
    protected String codigo;
    protected String nombre;
    protected double SalarioBase;
    
    public Empleado(String codigo, String nombre, double SalarioBase){
    this.codigo=codigo;
    this.nombre=nombre;
    this.SalarioBase=SalarioBase;
    
    }
    
    
    public abstract double calcularSalario();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalarioBase() {
        return SalarioBase;
    }

    public void setSalarioBase(double SalarioBase) {
        this.SalarioBase = SalarioBase;
    }
    
    
    public String  mostrarInfromacion(){
        return "Codigo"+codigo+" | "+"Nombre: "+nombre+" | "+"SalarioBase: "+SalarioBase+"|";
    }
    
    public String obtenerCategoria(){
    double sueldo=calcularSalario();
            switch(sueldo){
                case sueldo==1000:
                    
            
            }
        }
        
    
}
