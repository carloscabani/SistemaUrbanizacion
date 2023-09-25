package proyecto.tipos;
/**
 * Determina el tipo de Empleo que puede tener un Colaborador
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 * @see proyecto.clases.Colaborador
 */
public enum TipoEmpleo {
    /**
     * El Guardia.<br>
     * Es el encargado de hacer las revisiones de ingreso,
     * aceptar las visitas y de registrar las
     * observaciones pertinentes.
     */
    GUARDIA,
    /**
     * El Administrador.<br>
     * Es el único tipo de empleo que puede fijarse como
     * administrador de una urbanización.
     */
    ADMINISTRADOR,
    /**
     * El Jardinero.<br>
     * Se encarga de la estética de y arreglos de
     * las áreas verdes de la urbanización.
     */
    JARDINERO;
}
