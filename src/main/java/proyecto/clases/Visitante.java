package proyecto.clases;
import proyecto.tipos.Estado;
/**
 * Esta clase define al objeto Visitante de la Urbanizacion
 * @author Alexander Nieves
 * @see Persona
 */
public class Visitante extends Persona{
    /**
     * Almacena la empresa en caso de que el usuario sea un repartidor
     */
    private String empresa = "No Aplica";
    /**
     * Almacena las sanciones registradas al usuario
     */
    private String sanciones = "Ninguno";
    /**
     * Almacena la variable que determina la versión de la serialización
     */
    private static final long serialVersionUID = 103L;
    
    /**
     * Constructor de cédula utilizado principalmente para comparar objetos
     * de tipo Visitante
     * @see Persona#equals(java.lang.Object)
     * @param cedula Define la cédula del Visitante
     */
    public Visitante(String cedula){
        super(cedula);
    }
    
    /**
     * Obtiene la información del Visitante
     * @return <ul>
     * <li>Con Empresa Registrada: Presenta al Visitante como un repartidor</li>
     * <li>Sin Empresa Registrada: Presenta la información del Visitante sin el atributo de empresa</li>
     * </ul>
     */
    @Override
    public String toString(){
        if(empresa == null)
            empresa = "No Aplica";
        return super.toString() + ";" + String.join(";", empresa, sanciones);
    }
    
    /**
     * Constructor cuando el Visitante no es repartidor
     * @param cedula Define la cédula del Visitante
     * @param nombre Define el nombre del Visitante
     * @param telefono Define teléfono de contacto del Visitante
     * @param email Define correo electrónico de contacto del Visitante
     * @param estado Define si el Visitante se encuentra habilitado o no.
     * @param sanciones Define el Historial de Sanciones del Visitante
     * @see proyecto.tipos.Estado
     */
    public Visitante(String cedula, String nombre, String telefono, String email, Estado estado, String sanciones){
        super(cedula, nombre, telefono, email, estado);
        this.sanciones = sanciones;
    }
    
    /**
     * Constructor cuando el Visitante es un repartidor
     * @param cedula Define la cédula del Visitante
     * @param nombre Define el nombre del Visitante
     * @param telefono Define teléfono de contacto del Visitante
     * @param email Define correo electrónico de contacto del Visitante
     * @param estado Define si el Visitante se encuentra habilitado o no.
     * @param empresa Define la empresa para la que trabaja el Visitante
     * @param sanciones Define el Historial de Sanciones del Visitante
     * @see proyecto.tipos.Estado
     */
    public Visitante(String cedula, String nombre, String telefono, String email, Estado estado, String empresa, String sanciones){
        this(cedula, nombre, telefono, email, estado, sanciones);
        this.empresa = empresa;
    }
    
    /**
     * Constructor para instanciar un objeto Visitante de un array de Strings. Si el valor de estado es incorrecto, los mantiene con su valor por defecto.
     * @param datos Define la información de un visitante. Su formato debe ser: ﻿cedula;nombre;telefono;email;estado;empresa;historialSanciones
     * @see proyecto.tipos.Estado
     */
    public Visitante(String[] datos){
        super(datos);
        empresa = datos[5];
        sanciones = datos[6];
    }
    
    /**
     * Obtiene la empresa para la que trabaja el Visitante
     * @return la empresa para la que trabaja el Visitante
     */ 
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Define la empresa para la que trabaja el Visitante
     * @param empresa Define la empresa para la que trabaja el Visitante
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el Historial de Sanciones del Visitante
     * @return El Historial de Sanciones del Visitante
     */
    public String getSanciones() {
        return sanciones;
    }

    /**
     * Define el Historial de Sanciones del Visitante
     * @param sanciones Define el Historial de Sanciones del Visitante
     */
    public void setSanciones(String sanciones) {
        this.sanciones = sanciones;
    }
}