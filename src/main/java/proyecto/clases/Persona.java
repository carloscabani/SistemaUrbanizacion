package proyecto.clases;
import java.io.Serializable;
import proyecto.tipos.Estado;
import java.util.Objects;

/**
 * Esta clase define los atributos en comun de los Visitantes, Colaboradores y Residentes
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 * @see Residente
 * @see Colaborador
 * @see Visitante
 */
public abstract class Persona implements Serializable{
    /**
     * Cédula de la persona
     */
    private String cedula;
    /**
     * Nombre de la persona
     */
    private String nombre;
    /**
     * Teléfono de la persona
     */
    private String telefono;
    /**
     * Correo electrónico de la persona
     */
    private String email;
    /**
     * Estado {@code ACTIVO} o {@code INACTIVO} del usuario
     */
    private Estado estado;
    private static final long serialVersionUID = 100L;
    
    /**
     * Constructor de cédula utilizado principalmente para comparar objetos
     * de tipo Persona
     * @see Persona#equals(java.lang.Object)
     * @param cedula Define la cédula de Colaborador
     */
    public Persona(String cedula){
        this.cedula = cedula;
    }
    
    /**
     * Constructor para instanciar un objeto Persona
     * @param cedula Define la cédula de la Persona
     * @param nombre Define el nombre de la Persona
     * @param telefono Define teléfono de contacto de la Persona
     * @param email Define correo electrónico de contacto de la Persona
     * @param estado Define si la Persona se encuentra habilitada o no.
     * @see proyecto.tipos.Estado
     */
    public Persona(String cedula, String nombre, String telefono, String email, Estado estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }
    
    /**
     * Constructor para instanciar un objeto Persona de un array de Strings. Si el valor de estado es incorrecto, lo mantiene con su valor por defecto.
     * @param datos Define los valores para una persona. El orden de los datos del array debe ser: ﻿cedula;nombre;telefono;email;estado
     */
    public Persona(String[] datos){
        this(datos[0],datos[1],datos[2],datos[3],null);
        try{
            estado = Estado.valueOf(datos[4]);
        } catch(Exception e){}
    }
    
    /**
     * Obtiene la cédula de la Persona
     * @return La cédula de la Persona
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Define la cédula de la Persona según el parámetro recibido
     * @param cedula Recibe la cédula de la Persona
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Obtiene el nombre de la Persona
     * @return El nombre de la Persona
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Define el nombre de la Persona según el parámetro recibido
     * @param nombre Recibe el nombre de la Persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el teléfono de la Persona
     * @return El teléfono de la Persona
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Define el teléfono de la Persona según el parámetro recibido
     * @param telefono Recibe el teléfono de la Persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el correo electrónico de la Persona
     * @return El correo electrónico de la Persona
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Define el correo electrónico de la Persona según el parámetro recibido
     * @param email Recibe el correo electrónico de la Persona
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Obtiene el estado de la Persona (ACTIVO o INACTIVO)
     * @return El estado de la Persona
     * @see proyecto.tipos.Estado#ACTIVO
     * @see proyecto.tipos.Estado#INACTIVO
     */
    public Estado getEstado() {
        return estado;
    }
    
    /**
     * Define el estado de la Persona según el parámetro recibido (ACTIVO o INACTIVO)
     * @param estado Recibe el estado de la Persona
     * @see proyecto.tipos.Estado#ACTIVO
     * @see proyecto.tipos.Estado#INACTIVO
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    /**
     * Obtiene la información de la Persona
     * @return La información de la Persona
     */
    @Override
    public String toString() {
        String estado = "null";
        try{
            estado = this.estado.toString();
        }catch(Exception e){}
        return String.join(";",new String[] {cedula,nombre,telefono,email,estado});
    }
    
    /**
     * Indica cuándo otro objeto es "igual a" esta Persona.
     * Utiliza el atributo {@code cédula} para comparar ambos objetos.
     * @param obj Recibe el objeto a comparar
     * @return <ul><li>{@code true} si este objeto Persona es igual que el argumento {@code obj}</li>
     * <li>{@code true} si sus cédulas son iguales</li>
     * <li>{@code false} si es {@code null}</li>
     * <li>{@code false} si no es de la misma clase</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //compara referencia
            return true;
        }
        if (obj == null) { //pregunta si es null
            return false;
        }
        if (getClass() != obj.getClass()) //compara clases
            return false;
        final Persona other = (Persona) obj; //downcasting de obj
        return Objects.equals(this.cedula, other.cedula);
    }
    
    /**
     * Devuelve la información importante de la {@code Persona}
     * @return un formato de texto de tipo: {@code nombre} : {@code cedula}
     * @see Estado
     */
    public String toInformation(){
        return nombre + " : " + cedula;
    }
    
    /**
     * Analiza el {@code estado} para devolver la información importante de la {@code Persona} 
     * @return un formato de texto de tipo: {@code nombre} : {@code cedula} si el {@code estado} es ACTIVO, caso contrario null
     * @see #toInformation()
     * @see Estado
     */
    public String toFilteredInformation(){
        if(estado.equals(Estado.ACTIVO)){
            return toInformation();
        }
        return null;
    }
}