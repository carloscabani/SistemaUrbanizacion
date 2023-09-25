package proyecto.tipos;
/**
 * Determina los estados que puede tener una Persona
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 * @see proyecto.clases.Persona
 */
public enum Estado {
    /**
     * Activo.<br>
     * Indica que la persona se encuentra disponible para interactuar con el Sistema.
     */
    ACTIVO,
    /**
     * Inactivo.<br>
     * Indica que la persona se ha eliminado del Sistema. No puede
     * interactuar con el Sistema.
     */
    INACTIVO;
}
