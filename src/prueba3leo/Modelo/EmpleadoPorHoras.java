/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Modelo;

/**
 *
 * @author Abril
 */
public class EmpleadoPorHoras extends Empleado{
    private int horasTrabajadas;
    private double tarifaPorHora;
    public EmpleadoPorHoras(String codigo, String nombre, double SalarioBase){
    super(codigo, nombre, SalarioBase);
    
    
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }
    
    @Override
    public double calcularSalario(){
    return SalarioBase+(horasTrabajadas*tarifaPorHora);
    
    }
    
    
    
}
