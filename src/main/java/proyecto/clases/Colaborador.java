package proyecto.clases;
import proyecto.tipos.Estado;
import proyecto.tipos.TipoEmpleo;
import java.time.*;
/**
 * Esta clase define el comportamiento de un empleado de la Urbanizacion. Solo los
 * empleados de tipo ADMINISTRADOR pueden definirse como administrador de Urbanizacion
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 * @see Urbanizacion#admin
 * @see proyecto.tipos.TipoEmpleo
 */
public class Colaborador extends Persona{
    /**
     * Puesto de trabajo
     */
    private String puestoTrabajo;
    /**
     * Cargo en empresa
     */
    private TipoEmpleo empleo;
    /**
     * Fecha de inicio de actividades
     */
    private LocalDate fechaInicio;
    /**
     * Fecha de fin de actividades
     */
    private LocalDate fechaFin;
    private static final long serialVersionUID = 101L;
    
    /**
     * Constructor de cédula utilizado principalmente para comparar objetos
     * de tipo Colaborador
     * @see proyecto.clases.Persona#equals(java.lang.Object)
     * @param cedula Define la cédula de Colaborador
     */
    public Colaborador(String cedula){
        super(cedula);
    }//Fin constructor de cédula
    
    /**
     * Constructor utilizado cuando el Colaborador se encuentra en estado ACTIVO
     * @param cedula Define la cédula del Colaborador
     * @param nombre Define el nombre del Colaborador
     * @param telefono Define teléfono de contacto del Colaborador
     * @param email Define correo electrónico de contacto del Colaborador
     * @param puestoTrabajo Define el la ubicación de trabajo del Colaborador en la Urbanización
     * @param empleo Define el tipo de empleo asignado para el Colaborador
     * @param estado Define si el Colaborador se encuentra trabajando o no
     * @param fechaInicio Define la fecha en la que el Colaborador inicia sus actividades
     * @see proyecto.tipos.Estado
     * @see proyecto.tipos.TipoEmpleo
     */
    public Colaborador(String cedula, String nombre, String telefono, String email, String puestoTrabajo, TipoEmpleo empleo, Estado estado, LocalDate fechaInicio) {
        super(cedula, nombre, telefono, email, estado);
        this.puestoTrabajo = puestoTrabajo;
        this.empleo = empleo;
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Constructor utilizado cuando el Colaborador se encuentra en estado INACTIVO
     * @param cedula Define la cédula del Colaborador
     * @param nombre Define el nombre del Colaborador
     * @param telefono Define teléfono de contacto del Colaborador
     * @param email Define correo electrónico de contacto del Colaborador
     * @param puestoTrabajo Define el la ubicación de trabajo del Colaborador en la Urbanización
     * @param empleo Define el tipo de empleo asignado para el Colaborador
     * @param estado Define si el Colaborador se encuentra trabajando o no
     * @param fechaInicio Define la fecha en la que el Colaborador inicia sus actividades
     * @param fechaFin Define la fecha en la que el Colaborador finaliza sus actividades
     * @see proyecto.tipos.Estado
     * @see proyecto.tipos.TipoEmpleo
     */
    public Colaborador(String cedula, String nombre, String telefono, String email, String puestoTrabajo, TipoEmpleo empleo, Estado estado, LocalDate fechaInicio, LocalDate fechaFin) {
        this(cedula, nombre, telefono, email, puestoTrabajo, empleo, estado, fechaInicio);
        this.fechaFin = fechaFin;
    }//Fin de Constructor
    
    /**
     * Constructor que importa los datos de un array de String. Si los valores de las fechas o estado o tipoEmpleo son incorrectos, los almacena como null
     * @param datos Define todos los datos que debe tener un colaborador. Su formato debe ser ﻿cedula;nombre;telefono;email;estado;puestoTrabajo;empleo;fechaInicio (aaaa-mm-dd);fechaFin (aaaa-mm-dd)
     * @see proyecto.tipos.Estado
     * @see proyecto.tipos.TipoEmpleo
     */
    public Colaborador(String[] datos){
        super(datos);
        puestoTrabajo = datos[5];
        try{
            empleo = TipoEmpleo.valueOf(datos[6]);
        } catch(Exception e){}
        try{
            fechaInicio = LocalDate.parse(datos[7]);
        } catch(Exception e){
            fechaInicio = null;
        }
        try{
            fechaFin = LocalDate.parse(datos[8]);
        } catch(Exception e){
            fechaFin = null;
        }
    }
    
    /**
     * Obtener la ubicación de trabajo del Colaborador en la Urbanización
     * @return El puesto de Trabajo del Colaborador
     */
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }//Cierre del getter
    
    /**
     * Ingresa la ubicación de trabajo del Colaborador en la Urbanización
     * @param puestoTrabajo Define el puesto de Trabajo del Colaborador
     */
    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }//Cierre del setter
    
    /**
     * Obtiene el trabajo asignado al Colaborador
     * @return El cargo asignado al Colaborador
     * @see proyecto.tipos.TipoEmpleo
     * @see proyecto.tipos.TipoEmpleo#ADMINISTRADOR
     * @see proyecto.tipos.TipoEmpleo#GUARDIA
     * @see proyecto.tipos.TipoEmpleo#JARDINERO
     */
    public TipoEmpleo getEmpleo() {
        return empleo;
    }//Cierre del getter
    
    /**
     * Ingresa el trabajo asignado al Colaborador
     * @param empleo Define el cargo asignado al Colaborador
     */
    public void setEmpleo(TipoEmpleo empleo) {
        this.empleo = empleo;
    }//Cierre del setter
    
    /**
     * Obtiene la fecha en la que el Colaborador inicia sus actividades
     * @return la fecha en la que el Colaborador inicia sus actividades
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }//Cierre del getter
    
    /**
     * Ingresa la fecha en la que el Colaborador inicia sus actividades
     * @param fechaInicio Define la fecha en la que el Colaborador inicia sus actividades
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }//Cierre del setter
    
    /**
     * Obtiene la fecha en la que el Colaborador finaliza sus actividades
     * @return la fecha en la que el Colaborador finaliza sus actividades
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }//Cierre del getter
    
    /**
     * Ingresa la fecha en la que el Colaborador finaliza sus actividades
     * @param fechaFin Define la fecha en la que el Colaborador finaliza sus actividades
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }//Cierre del setter
    
    /**
     * Obtiene la información del Colaborador
     * @return <ul>
     * <li>Cuando tiene Estado ACTIVO: La información del Colaborador sin su fecha de fin de actividades</li>
     * <li>Cuando tiene Estado INACTIVO: La información del Colaborador con su fecha de fin de actividades</li>
     * </ul>
     */
    @Override
    public String toString() {
        String inicio = "No Registrado";
        String fin = "No Registrado";
        String empleo = "No Registrado";
        try{
            inicio = fechaInicio.toString();
        }catch(Exception e){}
        try{
            fin = fechaFin.toString();
        }catch(Exception e){}
        try{
            empleo = this.empleo.toString();
        }catch(Exception e){}
        return super.toString() + ";" + String.join(";", puestoTrabajo, empleo, inicio, fin);
    }
    
    /**
     * Analiza el {@code estado} y el {@code empleo} para devolver la información importante del {@code Colaborador}
     * @param tipoEmpleo tipo de Empleo
     * @return un formato de texto de tipo: {@code nombre} : {@code cedula} si el {@code estado} es ACTIVO y el {@code empleo} es igual al parámetro ingresado, caso contrario null
     * @see Persona#toFilteredInformation()
     * @see Estado
     * @see TipoEmpleo
     */
    public String toFilterByInformation(TipoEmpleo tipoEmpleo){
        if(empleo.equals(tipoEmpleo)){
            return super.toFilteredInformation();
        }
        return null;
    }
    
}