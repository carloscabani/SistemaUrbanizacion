package proyecto.clases;

import java.io.Serializable;

/**
 * Esta clase contiene los atributos de la Urbanizacion sobre la que trabaja el usuario
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 */
public class Urbanizacion implements Serializable{
    /**
     * Nombre de la urbanización
     */
    private String nombre;
    /**
     * Etapa de la urbanización en la que se desarrolla el Sistema
     */
    private String etapa;
    /**
     * Correo electrónico vinculado al administrador de la urbanización
     */
    private String emailAdmin;
    /**
     * Dirección de la urbanización
     */
    private String direccion;
    /**
     * Empresa encargada de la construcción de la urbanización
     */
    private String constructora;
    /**
     * Administrador de la Urbanización
     */
    private Colaborador admin;
    /**
     * Almacena la variable que determina la versión de la serialización
     */
    private static final long serialVersionUID = 300L;
    
    /**
     * Constructor que instancia un objeto de tipo Urbanización con todos sus atributos
     * @param nombre Define el nombre de la Urbanización
     * @param etapa Define la etapa de la Urbanización
     * @param emailAdmin Define el correo electrónico del administrador de la Urbanización
     * @param direccion Define la ubicación de la Urbanización
     * @param constructora Define la empresa encargada de la construcción de la Urbanización
     * @param admin Define el Colaborador encargado de la Urbanización
     * @see Colaborador
     * @see proyecto.tipos.TipoEmpleo#ADMINISTRADOR
     */
    public Urbanizacion(String nombre, String etapa, String emailAdmin, String direccion, String constructora, Colaborador admin) {
        this.nombre = nombre;
        this.etapa = etapa;
        this.emailAdmin = emailAdmin;
        this.direccion = direccion;
        this.constructora = constructora;
        this.admin = admin;
    }

    /**
     * Obtiene los datos de la Urbanización
     * @return <ul>
     * <li>Tiene Administrador Activo: Presenta la información completa de la Urbanización</li>
     * <li>No dispone de un Administrador: Presenta la información de la Urbanización con los atributos {@code emailAdmin} y {@code admin} como {@code "No Disponible"}</li>
     * </ul>
     */
    @Override
    public String toString() {
        try{
            return "Urbanizacion " + nombre + "\nEtapa: " + etapa + "\nContacto Administración: " + emailAdmin + "\nDireccion: " + direccion + "\nConstructora: " + constructora + "\nAdministrador: " + admin.getNombre();
        } catch(Exception e){
            return "Urbanizacion " + nombre + "\nEtapa: " + etapa + "\nContacto Administración: No Disponible\nDireccion: " + direccion + "\nConstructora: " + constructora + "\nAdministrador: No Disponible";
        }   
    }

    /**
     * Obtiene el nombre de la Urbanización
     * @return El nombre de la Urbanización
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la Urbanización
     * @param nombre Define el nombre de la Urbanización
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la etapa de la Urbanización
     * @return La etapa de la Urbanización
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * Define la etapa de la Urbanización
     * @param etapa Define la etapa de la Urbanización
     */
    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    /**
     * Obtiene el correo electrónico del administrador de la Urbanización
     * @return El correo electrónico del administrador de la Urbanización
     */
    public String getEmailAdmin() {
        return emailAdmin;
    }

    /**
     * Define el correo electrónico del administrador de la Urbanización
     * @param emailAdmin Define el correo electrónico del administrador de la Urbanización
     */
    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    /**
     * Obtiene la ubicación de la Urbanización
     * @return La ubicación de la Urbanización
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define la ubicación de la Urbanización
     * @param direccion Define la ubicación de la Urbanización
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la empresa encargada de la construcción de la Urbanización
     * @return La empresa encargada de la construcción de la Urbanización
     */
    public String getConstructora() {
        return constructora;
    }

    /**
     * Define la empresa encargada de la construcción de la Urbanización
     * @param constructora Define la empresa encargada de la construcción de la Urbanización
     */
    public void setConstructora(String constructora) {
        this.constructora = constructora;
    }

    /**
     * Obtiene el Colaborador encargado de la Urbanización
     * @return El Colaborador encargado de la Urbanización
     * @see Colaborador
     * @see proyecto.tipos.TipoEmpleo#ADMINISTRADOR
     */
    public Colaborador getAdmin() {
        return admin;
    }

    /**
     * Define el Colaborador encargado de la Urbanización
     * @param admin Define el Colaborador encargado de la Urbanización
     * @see Colaborador
     * @see proyecto.tipos.TipoEmpleo#ADMINISTRADOR
     */
    public void setAdmin(Colaborador admin) {
        this.admin = admin;
    }

}