package proyecto.clases;
import proyecto.tipos.Estado;

/**
 * Esta clase define al objeto Residente que habita en la Urbanizacion
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 */
public class Residente extends Persona{
    /**
     * Manzana donde habita el residente
     */
    private int manzana;
    /**
    * Villa donde habita el residente
    */
    private int villa;
    /**
     * Cantidad de habitantes que viven con el residente
     */
    private int habitantesWithRes = 1;
    /**
     * Urbanización a la que pertenece el residente
     */
    private Urbanizacion urbanizacion;
    /**
     * Almacena la variable que determina la versión de la serialización
     */
    private static final long serialVersionUID = 102L;

    /**
     * Constructor de cédula utilizado principalmente para comparar objetos
     * de tipo Residente
     * @see Persona#equals(java.lang.Object)
     * @param cedula Define la cédula del Residente
     */
    public Residente(String cedula){
        super(cedula);
    }
    
    /**
     * Obtiene la información del Residente para exportar al documento csv
     * @return La información del Residente
     */
    @Override
    public String toString(){
        return super.toString() + ";" + String.join(";", String.valueOf(manzana),String.valueOf(villa),String.valueOf(habitantesWithRes));
    }
    
    /**
     * Constructor para instanciar un objeto Residente
     * @param cedula Define la cédula del Residente
     * @param nombre Define el nombre del Residente
     * @param telefono Define teléfono de contacto del Residente
     * @param email Define correo electrónico de contacto del Residente
     * @param estado Define si el Residente se encuentra habilitado o no.
     * @param manzana Define la manzana del Residente
     * @param villa Define la villa del Residente
     * @param habitantesWithRes Define la cantidad de personas que viven con el Residente
     * @param urbanizacion Define la manzana del Residente
     * @see proyecto.tipos.Estado
     */
    public Residente(String cedula, String nombre, String telefono, String email, Estado estado, int manzana, int villa, int habitantesWithRes, Urbanizacion urbanizacion){
        super(cedula, nombre, telefono, email, estado);
        this.manzana = manzana;
        this.villa = villa;
        this.habitantesWithRes = habitantesWithRes;
        this.urbanizacion = urbanizacion;
    }
    
    /**
     * Constructor para instanciar un objeto Residente de un array de Strings.Si los valores de estado, manzana, villa o habitantesWithRes son incorrectos, los mantiene con sus valores por defecto.
     * @param datos Define los valores para el residente, excepto la urbanización. El orden de los datos del array debe ser: ﻿cedula;nombre;telefono;email;estado;manzana;villa;habitantesConResidente
     * @param urbanizacion Urbanización del residente
     */
    public Residente(String datos[], Urbanizacion urbanizacion){
        super(datos);
        try{
            manzana = Integer.parseInt(datos[5]);
        }catch(Exception e){}
        try{
            villa = Integer.parseInt(datos[6]);
        }catch(Exception e){}
        try{
            habitantesWithRes = Integer.parseInt(datos[7]);
        }catch(Exception e){}
        this.urbanizacion = urbanizacion;
    }
    /**
     * Obtiene la Manzana del Residente
     * @return La Manzana del Residente
     */
    public int getManzana() {
        return manzana;
    }

    /**
     * Define la manzana del Residente según el valor ingresado
     * @param manzana Define la manzana del Residente
     */
    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    /**
     * Obtiene la villa del Residente
     * @return La villa del Residente
     */
    public int getVilla() {
        return villa;
    }

    /**
     * Define la villa del Residente según el valor ingresado
     * @param villa Define la villa del Residente
     */
    public void setVilla(int villa) {
        this.villa = villa;
    }

    /**
     * Obtiene la cantidad de personas que viven con el Residente
     * @return La cantidad de personas que viven con el Residente
     */
    public int getHabitantesWithRes() {
        return habitantesWithRes;
    }

    /**
     * Define la cantidad de personas que viven con el Residente
     * @param habitantesWithRes Define la cantidad de personas que viven con el Residente
     */
    public void setHabitantesWithRes(int habitantesWithRes) {
        this.habitantesWithRes = habitantesWithRes;
    }

    /**
     * Obtiene la urbanización a la que pertenece el Residente
     * @return La urbanización a la que pertenece el Residente
     * @see Urbanizacion
     */
    public Urbanizacion getUrbanizacion() {
        return urbanizacion;
    }

    /**
     * Define la urbanización a la que pertenece el Residente
     * @param urbanizacion Define la urbanización a la que pertenece el Residente
     */
    public void setUrbanizacion(Urbanizacion urbanizacion) {
        this.urbanizacion = urbanizacion;
    }
    
}