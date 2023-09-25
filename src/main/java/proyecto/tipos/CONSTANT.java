package proyecto.tipos;

/**
 * Almacena constantes que agilizan el código
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 * @version 14.1.23
 */
public class CONSTANT {
    /**
     * Ubicación del documento CSV de Colaboradores
     */
    public static final String COLABORADORES_LOCATION = "storage/personas/colaboradores.csv";
    
    /**
     * Ubicación del documento CSV de Visitantes
     */
    public static final String VISITANTES_LOCATION = "storage/personas/visitantes.csv";
    
    /**
     * Ubicación del documento CSV de Residentes
     */
    public static final String RESIDENTES_LOCATION = "storage/personas/residentes.csv";
    
    /**
     * Ubicación del archivo serializado de permisos
     */
    public static final String PERMISOS_LOCATION = "storage/permisos.permisos";
    
    /**
     * Ubicación del archivo serializado de la urbanización
     */
    public static final String URBANIZACION_LOCATION = "storage/urbanizacion.urb";
    
    /**
     * Encabezado de una lista de Colaboradores
     */
    public static final String COLABORADORES_HEADER = "Cedula;Nombre;Telefono;E-mail;Estado;Puesto;Cargo;Inicio de Actividades;Fin de Actividades";
    
    /**
     * Encabezado de una lista de Visitantes
     */
    public static final String VISITANTES_HEADER = "Cedula;Nombre;Telefono;E-mail;Estado;Empresa;Historial de Sanciones";
    
    /**
     * Encabezado de una lista de Residentes
     */
    public static final String RESIDENTES_HEADER = "Cedula;Nombre;Telefono;E-mail;Estado;Mz;Villa;Habitantes con Residente";
}