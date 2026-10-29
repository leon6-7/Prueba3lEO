/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Visual;

public class CodigoDuplicadoException extends Exception {
    public CodigoDuplicadoException(String codigo) {
        super("El código '" + codigo + "' ya está registrado.");
    }
}
 