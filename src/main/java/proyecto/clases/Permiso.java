package proyecto.clases;
import java.io.Serializable;
import proyecto.tipos.EstadoPermiso;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * Esta clase define el comportamiento de un Permiso de Entrada, importante para las revisiones
 * de ingreso a la urbanizacion y los reportes
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 */
public class Permiso implements Comparable<Permiso>, Serializable{
    //Para la tabla
    private String residenteNombre;
    private String visitanteNombre;
    private String guardiaNombre = "No Disponible";

    private EstadoPermiso estado;
    private LocalDateTime fechaCreacion, fechaVisita;
    private Residente residente; //quien aprueba
    private Visitante visitante; //quien ingresa
    private LocalTime duracion;
    private String codigoUnico;
    //Cuando el permiso es de EstadoPermiso.USADO
    private Colaborador guardia;
    private String observacion = "Ninguna";
    private static final long serialVersionUID = 200L;
    
    /**
     * Crea un nuevo Permiso con la fecha y hora actual
     */
    public Permiso(){
        fechaCreacion = LocalDateTime.now().withSecond(0).withNano(0);
    }
    
    /**
     * Constructor de {@code codigoUnico} utilizado principalmente para comparar objetos
     * de tipo Permiso
     * @param codigoUnico Define el Código Único del Permiso
     * @see Permiso#equals(java.lang.Object)
     */
    public Permiso(String codigoUnico){
        this.codigoUnico = codigoUnico;
    }

    /**
     * Constructor para instanciar un Permiso con todos sus atributos antes
     * de ser usado
     * @param estado Define el estado del Permiso
     * @param fechaCreacion Define la fecha de creación del Permiso
     * @param residente Define el Residente que autoriza el Permiso
     * @param visitante Define el Visitante que utilizará el Permiso
     * @param fechaVisita Define la fecha de visita del Permiso
     * @param duracion Define la duración de la visita
     * @see proyecto.tipos.EstadoPermiso
     * @see Residente
     * @see Visitante
     */
    public Permiso(EstadoPermiso estado, LocalDateTime fechaCreacion, Residente residente, Visitante visitante, LocalDateTime fechaVisita, LocalTime duracion) {
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.residente = residente;
        this.visitante = visitante;
        this.fechaVisita = fechaVisita;
        this.duracion = duracion;
        crearCodigoUnico();
        residenteNombre = residente.getNombre();
        visitanteNombre = visitante.getNombre();
    }

    /**
     * Obtiene el Colaborador, que deberá ser de tipo GUARDIA, que autorizó el permiso
     * @return El Colaborador, que deberá ser de tipo GUARDIA, que autorizó el permiso
     * @see proyecto.tipos.EstadoPermiso#USADO
     * @see proyecto.tipos.TipoEmpleo#GUARDIA
     */
    public Colaborador getGuardia() {
        return guardia;
    }

    /**
     * Define el Colaborador, que deberá ser de tipo GUARDIA, que autorizó el permiso
     * @param guardia Define el Colaborador, que deberá ser de tipo GUARDIA, que autorizó el permiso
     * @see proyecto.tipos.EstadoPermiso#USADO
     * @see proyecto.tipos.TipoEmpleo#GUARDIA
     */
    public void setGuardia(Colaborador guardia) {
        this.guardia = guardia;
        guardiaNombre = guardia.getNombre();
    }

    /**
     * Obtiene la observación del Permiso cuando es USADO
     * @return La observación del Permiso cuando es USADO
     * @see proyecto.tipos.EstadoPermiso#USADO
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Define la observación del Permiso cuando es USADO
     * @param observacion Define la observación del Permiso cuando es USADO
     * @see proyecto.tipos.EstadoPermiso#USADO
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Obtiene el estado del Permiso
     * @return El estado del Permiso
     * @see proyecto.tipos.EstadoPermiso
     */
    public EstadoPermiso getEstado() {
        return estado;
    }
    
    /**
     * Define el estado del Permiso
     * @param estado Define el estado del Permiso
     * @see proyecto.tipos.EstadoPermiso
     */
    public void setEstado(EstadoPermiso estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha de creación del Permiso
     * @return La fecha de creación del Permiso
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Define la fecha de creación del Permiso
     * @param fechaCreacion Define la fecha de creación del Permiso
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el Residente que autoriza el Permiso
     * @return El Residente que autoriza el Permiso
     * @see Residente
     */
    public Residente getResidente() {
        return residente;
    }

    /**
     * Define el Residente que autoriza el Permiso
     * @param residente Define el Residente que autoriza el Permiso
     * @see Residente
     */
    public void setResidente(Residente residente) {
        this.residente = residente;
        residenteNombre = residente.getNombre();
    }

    /**
     * Obtiene el Visitante que utilizará el Permiso
     * @return El Visitante que utilizará el Permiso
     * @see Visitante
     */
    public Visitante getVisitante() {
        return visitante;
    }

    /**
     * Define el Visitante que utilizará el Permiso
     * @param visitante Define el Visitante que utilizará el Permiso
     * @see Visitante
     */
    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
        visitanteNombre = visitante.getNombre();
    }

    /**
     * Obtiene la fecha de visita del Permiso
     * @return La fecha de visita del Permiso
     */
    public LocalDateTime getFechaVisita() {
        return fechaVisita;
    }

    /**
     * Define la fecha de visita del Permiso
     * @param fechaVisita Define la fecha de visita del Permiso
     */
    public void setFechaVisita(LocalDateTime fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    /**
     * Obtiene la duración de la visita
     * @return La duración de la visita
     */
    public LocalTime getDuracion() {
        return duracion;
    }

    /**
     * Define la duración de la visita
     * @param duracion Define la duración de la visita
     */
    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el Códgio Único del Permiso
     * @return El Códgio Único del Permiso
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    
    /**
     * Obtiene el nombre del Residente
     * @return El nombre del Residente
     */
    public String getResidenteNombre() {
        return residenteNombre;
    }

    /**
     * Define el nombre del Residente
     * @param residenteNombre Define el nombre del Residente
     */
    public void setResidenteNombre(String residenteNombre) {
        this.residenteNombre = residenteNombre;
    }

    /**
     * Obtiene el nombre del Visitante
     * @return El nombre del Visitante
     */
    public String getVisitanteNombre() {
        return visitanteNombre;
    }

    /**
     * Define el nombre del Visitante
     * @param visitanteNombre Define el nombre del Visitante
     */
    public void setVisitanteNombre(String visitanteNombre) {
        this.visitanteNombre = visitanteNombre;
    }

    /**
     * Obtiene el nombre del Guardia
     * @return El nombre del Guardia
     */
    public String getGuardiaNombre() {
        return guardiaNombre;
    }

    /**
     * Define el nombre del Guardia
     * @param guardiaNombre Define el nombre del Guardia
     */
    public void setGuardiaNombre(String guardiaNombre) {
        this.guardiaNombre = guardiaNombre;
    }
    
    /**
     * Crea el Código Único del Permiso con ciertos criterios.
     * El Código Único está conformado por:
     * <ul>
     * <li>2 letras nombre de residente</li>
     * <li>fecha creación permiso</li>
     * <li>Un guión ( - )</li>
     * <li>2 letras nombre de visitante</li>
     * <li>hora creación permiso</li>
     * </ul>
     */
    public void crearCodigoUnico(){
        this.codigoUnico = (residente.getNombre().substring(0, 2) + fechaCreacion.format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE) + "-" + visitante.getNombre().substring(0, 2) + fechaCreacion.getHour() + fechaCreacion.getMinute()).toUpperCase();
    }

    /**
     * Obtiene los datos del Permiso
     * @return <ul>
     * <li>Estado Permiso {@code USADO}: Presenta toda la información del permiso, incluido el guardia que autorizó la visita, por dónde entró el visitante, y las observaciones del guardia</li>
     * <li>Estado Permiso distinto a {@code USADO}: Presenta la información de un permiso que no se ha usado para visitar</li>
     * </ul>
     * @see proyecto.tipos.EstadoPermiso
     */
    @Override
    public String toString() {
        String mensaje = String.format("Permiso: %s\nEstado: %s\nAutorizado por: %s\nVisitante: %s\nFecha de Creación: %s\nFecha de Visita: %s\nDuración Estimada: %s", codigoUnico, estado, residente.getNombre(), visitante.getNombre(), fechaCreacion, fechaVisita, duracion);
        if(estado.equals(EstadoPermiso.USADO))
            return mensaje + String.format("\nRevisado por: %s\nEntrada: %s\nObservaciones: %s", guardia.getNombre(), guardia.getPuestoTrabajo(), observacion);
        return mensaje;
    }

    /**
     * Decide si un permiso ACTIVO debe cambiar su estado a CADUCADO.
     * Analiza el estado del permiso y compara fechas y tiempos con el instante actual para decidir si el permiso ha caducado.<br>
     * Esta función es llamada en los menús de Reportes, Permisos y Revisión de Ingreso del método {@code main} de la clase principal {@code Sistema}.<br>
     * Ejemplos donde un permiso cambiará a estado CADUCADO:
     * <ul>
     * <li>Permiso Creado hace más de 2 días</li>
     * <li>El Permiso no se usó y la duración de la visita terminó</li>
     * <li>El Permiso se creó ayer y la fecha de visita registrada también era ayer</li>
     * </ul>
     * @see Sistema
     * @see proyecto.tipos.EstadoPermiso#ACTIVO
     * @see proyecto.tipos.EstadoPermiso#CADUCADO
     */
    public void actualizarEstado(){
        if(estado.equals(EstadoPermiso.ACTIVO)){
            if(java.time.LocalDate.now().isAfter(fechaCreacion.toLocalDate().plusDays(1))){
                estado = EstadoPermiso.CADUCADO; //Han pasado las 24 horas de creado el permiso
            }
            if(fechaVisita.toLocalDate().isEqual(java.time.LocalDate.now())){
                //La fecha de visita es hoy
                LocalDateTime time = LocalDateTime.now();
                LocalDateTime tiempoVisitaTermina = fechaVisita.plusHours(duracion.getHour()).plusMinutes(duracion.getMinute());
                if(time.compareTo(tiempoVisitaTermina) >= 0)
                    estado = EstadoPermiso.CADUCADO; //Ha acabado la hora de visita
            } else if(fechaVisita.toLocalDate().isBefore(java.time.LocalDate.now()))
                estado = EstadoPermiso.CADUCADO; //Ha pasado de la fecha de visita
        }
    }

    /**
     * Indica cuándo otro objeto es "igual a" este Permiso.
     * Utiliza el atributo {@code codigoUnico} para comparar ambos objetos.
     * @param obj Recibe el objeto a comparar
     * @return <ul><li>{@code true} si este objeto Permiso es igual que el argumento {@code obj}</li>
     * <li>{@code true} si sus códigos son iguales</li>
     * <li>{@code false} si es {@code null}</li>
     * <li>{@code false} si no es de la misma clase</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != getClass())
            return false;
        final Permiso other = (Permiso) obj;
        return java.util.Objects.equals(other.codigoUnico, codigoUnico);
    }

    /**
     * Establece los criterios de orden según la fecha de Creación.
     * Es utilizado en el menú de Permisos para presentar los permisos ordenados por fecha cuando
     * se imprime el conjunto {@code java.util.Set<Permiso> historial = new java.util.TreeSet<>()}.
     * @param permiso Objeto Permiso que será comparado
     * @return el valor de comparación según el atributo {@code fechaCreacion}
     */
    @Override
    public int compareTo(Permiso permiso) {
        return this.fechaCreacion.compareTo(permiso.fechaCreacion);
    }   
}