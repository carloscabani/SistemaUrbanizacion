package proyecto.tipos;
/**
 * Determina los estados que puede tener un Permiso
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 * @see proyecto.clases.Permiso
 */
public enum EstadoPermiso {
    /**
     * Activo.<br>
     * Indica que un permiso puede ser usado para que un
     * Visitante ingrese a la urbanización.
     */
    ACTIVO,
    /**
     * Inactivo.<br>
     * Indica que un permiso ha sido eliminado y no puede
     * ser usado para las visitas.
     */
    INACTIVO,
    /**
     * Caducado.<br>
     * Indica que un permiso ya no puede ser usado
     * para las visitas porque su tiempo de uso ha finalizado.
     */
    CADUCADO,
    /**
     * Usado.<br>
     * Indica que un permiso ha sido usado, refleja que
     * guardia admitió el ingreso del visitante a la
     * urbanización.
     */
    USADO;
}
