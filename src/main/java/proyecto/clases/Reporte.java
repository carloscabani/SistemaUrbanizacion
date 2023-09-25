package proyecto.clases;

import proyecto.tipos.EstadoPermiso;

/**
 * Clase que genera una línea de reporte de un permiso para el {@code reportesTable}
 * @see Permiso
 * @see proyecto.controllers.ReportesController#reportesTable
 * @author Alexander Nieves
 */
public final class Reporte {
    /**
     * Refleja el número de línea de reporte
     */
    private int contador;
    /**
     * Es el {@code codigoUnico} del {@code permiso}
     * @see Permiso#codigoUnico
     */
    private String codigoUnico;
    /**
     * Es el nombre del {@code visitante} del {@code permiso}
     * @see Permiso#visitante
     */
    private String visitante;
    /**
     * Son las {@code observaciones} del {@code permiso}
     * @see Permiso#observacion
     */
    private String observaciones;
    /**
     * Es el {@code estado} del {@code permiso}
     * @see Permiso#estado
     * @see EstadoPermiso
     */
    private EstadoPermiso estado;
    
    /**
     * Constructor de un reporte
     * @param contador Define el número de línea de reporte
     * @param permiso Define el permiso a analizar
     */
    public Reporte(int contador, Permiso permiso){
        codigoUnico = permiso.getCodigoUnico();
        visitante = permiso.getVisitante().getNombre();
        observaciones = permiso.getObservacion();
        estado = permiso.getEstado();
        this.contador = contador;
    }
    
    /**
     * Obtiene el contador del reporte
     * @return el contador
     */
    public int getContador() {
        return contador;
    }
    
    /**
     * Define el contador del reporte
     * @param contador el contador del reporte
     */
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    /**
     * Obtiene el código único del permiso del reporte
     * @return el código único
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    
    /**
     * Define el código único del permiso para el reporte
     * @param codigoUnico el código único
     */
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    /**
     * Obtiene el nombre del visitante del permiso del reporte
     * @return el nombre del visitante
     */
    public String getVisitante() {
        return visitante;
    }

    /**
     * Define el nombre del visitante para el reporte
     * @param visitante el nombre del visitante
     */
    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    /**
     * Obtiene las observaciones del permiso del reporte
     * @return las observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define las observaciones para el reporte
     * @param observaciones las observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el estado del permiso del reporte
     * @return el estado de permiso
     */
    public EstadoPermiso getEstado() {
        return estado;
    }

    /**
     * Define el estado del permiso para el reporte
     * @param estado el estado
     */
    public void setEstado(EstadoPermiso estado) {
        this.estado = estado;
    }
}