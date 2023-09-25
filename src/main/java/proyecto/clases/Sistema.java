package proyecto.clases;
//Java imports
import proyecto.tipos.CONSTANT;
import java.io.IOException;
import proyecto.tipos.*;
import java.util.*;
import java.time.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
//JavaFX imports
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Clase Principal. Ejecución de todo el menú principal.
 * <p>Este Sistema implementa una interfaz gráfica amigable para administrar un 
 * medio de ingreso y almacenamiento de datos de una urbanización. Además, permite manipular permisos de entrada 
 * y ejecutar procesos de revisión eficientes.</p>
 * <p>Si el proyecto se ejecuta por primera vez, el `Sistema` instancia los datos iniciales de la 
 * urbanización y 6 permisos de ejemplo.</p>
 * <p>Utiliza los archivos .csv para leer la información de las personas. Es posible modificar los datos de la urbanización 
 * y crear, consultar y eliminar permisos. Los permisos nuevos solo pueden crearlos visitantes con estado ACTIVO, al crearlo, 
 * obtendrás un código único que se entrega al visitante que lo podrá usar a la hora de visita correspondiente hasta que caduque el permiso. Para 
 * registrar la visita de un visitante, es necesario ubicarse en "Revisión de Entrada" del menú lateral para, mediante el código único, consultar los detalles 
 * de la visita y autorizar o negar el ingreso. Adicionalmente, se pueden crear reportes para analizar los permisos creados por los residentes.</p>
 * @author Alexander Nieves
 * @author Carlos Cabanilla
 */
public class Sistema extends Application {
    /**
     * Posee los datos de la urbanización sobre la que trabajará la clase {@code Sistema}
     */
    public static Urbanizacion urbanizacion;

    /**
     * Almacena los visitantes que se registren en la urbanización
     * @see Visitante
     */
    public static ArrayList<Visitante> visitantes = new ArrayList<>();

    /**
     * Almacena los residentes que vivan en la urbanización
     * @see Residente
     */
    public static ArrayList<Residente> residentes = new ArrayList<>();

    /**
     * Almacena los colaboradores que trabajen en la urbanización
     * @see Colaborador
     */
    public static ArrayList<Colaborador> colaboradores = new ArrayList<>();

    /**
     * Almacena los permisos que crean los residentes
     * @see Permiso
     * @see Residente
     * @see Visitante
     */
    public static ArrayList<Permiso> permisos = new ArrayList<>();
    
    /**
     * Almacena las ventanas del sistema
     */
    private static Scene scene;
    
    /**
     * Contiene el ícono de la aplicación
     */
    private static Image ICON;
    
    /**
     * Constructor de Sistema para definir el ícono de la aplicación
     * @throws FileNotFoundException FileNotFoundException
     */
    public Sistema() throws FileNotFoundException{
        ICON = new Image(new FileInputStream("storage/images/icon.jpeg"));
    }
    
    /**
     * Método principal para ejecutar todo el Sistema, recibe la interacción de las ventanas.
     * Opciones del Menú Principal
     * <ol>
     * <li>Urbanización: podrá visualizar y editar la información de la urbanización.</li>
     * <li>Residentes: podrá visualizar la información de todos los residentes en una tabla.</li>
     * <li>Visitantes: podrá visualizar la información de todos los visitantes en una tabla.</li>
     * <li>Colaboradores: podrá visualizar la información de todos los colaboradores en una tabla.
     * <li>Permisos de entrada: podrá crear permisos, eliminar existentes y consultar permisos de una residencia en específico.</li>
     * <li>Revisión de ingreso: se ejecutará el proceso de validación de datos por parte del guardia cuando una visita esté por ejecutarse.</li>
     * <li>Reportes: se generará un reporte a partir de la cédula de un residente y se mostrarán sus permisos creados.</li>
     * </ol>
     * @param stage El escenario principal que recibe todas las ventanas del sistema.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"), 985, 410);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Sistema de Ingreso a Urbanización");
        stage.getIcons().add(ICON);
    }
    
    /**
     * Permite enviar al escenario principal las escenas FXML
     * @param fxml Recibe la escena FXML
     * @throws IOException IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
     * Carga la siguiente escena a mostrarse
     * @param fxml Recibe la escena FXML
     * @return El archivo FXML cargado para el stage principal
     * @throws IOException IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Sistema.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * Ejecuta el sistema de ventanas
     * @param args Argumentos del programa principal
     */
    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Primer método que se llama cuando se ejecuta el Sistema. Carga los datos de cada ArrayList static del Sistema.
     * @throws Exception Exception
     * @see #importColaboradores()
     * @see #importResidentes() 
     * @see #importVisitantes() 
     * @see #loadPermisos() 
     * @see #loadUrbanizacion() 
     */
    @Override
    public void init() throws Exception{
        importColaboradores();
        loadUrbanizacion();
        importResidentes();
        importVisitantes();
        loadPermisos();
    }
    
    /**
     * Deserializa los datos almacenados en urbanizacion.urb. Si no existen, carga unos datos predeterminados.
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    private static void loadUrbanizacion() throws FileNotFoundException, IOException, ClassNotFoundException{
        File location = new File(CONSTANT.URBANIZACION_LOCATION);
        if(!location.exists()){
            urbanizacion = new Urbanizacion("Bella Vista", "Lagarto", colaboradores.get(0).getEmail(), "Cerca de UCSG", "Vergara & Asociados", colaboradores.get(0));
        } else{
            FileInputStream fin=new FileInputStream(location);
            ObjectInputStream in =new ObjectInputStream(fin);
            urbanizacion = (Urbanizacion) in.readObject();
        }
        System.out.println("[" + LocalTime.now().withNano(0) + "] Datos de la Urbanización cargados correctamente.");
    }
    
    /**
     * Lee el archivo colaboradores.csv con los datos de los colaboradores y los almacena en un ArrayList de Colaboradores
     */
    private static void importColaboradores(){
        try {
            try (FileReader reader = new FileReader(CONSTANT.COLABORADORES_LOCATION, Charset.defaultCharset())) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                bufferedReader.readLine(); //Encabezado
                while ((line = bufferedReader.readLine()) != null) {
                    String[] datos = line.strip().split(";");
                    Colaborador importado = new Colaborador(datos);
                    colaboradores.add(importado);
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Colaboradores cargados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de colaboradores no encontrado");
        }
    }
    
    /**
     * Lee el archivo residentes.csv con los datos de los residentes y los almacena en un ArrayList de Residentes
     */
    private static void importResidentes(){
        try {
            try (FileReader reader = new FileReader(CONSTANT.RESIDENTES_LOCATION, Charset.defaultCharset())) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                bufferedReader.readLine(); //Encabezado
                while ((line = bufferedReader.readLine()) != null) {
                    String[] datos = line.strip().split(";");
                    Residente importado = new Residente(datos,urbanizacion);
                    residentes.add(importado);
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Residentes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de residentes no encontrado");
        }
    }
    
    /**
     * Lee el archivo visitantes.csv con los datos de los visitantes y los almacena en un ArrayList de Visitantes
     */
    private static void importVisitantes(){
        try {
            try (FileReader reader = new FileReader(CONSTANT.VISITANTES_LOCATION, Charset.defaultCharset())) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                bufferedReader.readLine(); //Encabezado
                while ((line = bufferedReader.readLine()) != null) {
                    String[] datos = line.strip().split(";");
                    Visitante importado = new Visitante(datos);
                    visitantes.add(importado);
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Visitantes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de visitantes no encontrado");
        }
    }
    
    /**
     * Deserializa el archivo permisos.permisos con los datos de los permisos y los almacena en un ArrayList de Permisos. Si el archivo no existe carga información predeterminada.
     */
    private static void loadPermisos() throws FileNotFoundException, IOException, ClassNotFoundException{
        File location = new File(CONSTANT.PERMISOS_LOCATION);
        if(!location.exists()){
            permisos.add(new Permiso(EstadoPermiso.CADUCADO, LocalDateTime.of(2022, Month.NOVEMBER, 27, 20, 0), residentes.get(0), visitantes.get(0), LocalDateTime.of(2022, 11, 28, 17, 30), LocalTime.of(2, 30)));
            permisos.add(new Permiso(EstadoPermiso.ACTIVO, LocalDateTime.now().minusMinutes(20).withNano(0).withSecond(0), residentes.get(0), visitantes.get(1), LocalDateTime.now().withNano(0).withSecond(0), LocalTime.of(0, 30)));
            permisos.add(new Permiso(EstadoPermiso.ACTIVO, LocalDateTime.now().minusMinutes(20).withNano(0).withSecond(0), residentes.get(4), visitantes.get(3), LocalDateTime.now().withNano(0).withSecond(0), LocalTime.of(0, 30)));
            permisos.add(new Permiso(EstadoPermiso.ACTIVO, LocalDateTime.now().minusMinutes(30).withNano(0).withSecond(0), residentes.get(1), visitantes.get(0), LocalDateTime.now().withNano(0).withSecond(0).plusMinutes(50), LocalTime.of(1, 50)));
            permisos.add(new Permiso(EstadoPermiso.INACTIVO, LocalDateTime.now().minusMinutes(10).withNano(0).withSecond(0), residentes.get(0), visitantes.get(1), LocalDateTime.now().withNano(0).withSecond(0).plusMinutes(50), LocalTime.of(1, 35)));
            permisos.add(new Permiso(EstadoPermiso.USADO, LocalDateTime.now().minusMinutes(55).withNano(0).withSecond(0), residentes.get(1), visitantes.get(0), LocalDateTime.now().withNano(0).withSecond(0).minusMinutes(20), LocalTime.of(2, 45)));
            permisos.get(5).setGuardia(colaboradores.get(1));
            permisos.get(5).setObservacion("Me regaló una pizza");
        } else{
            FileInputStream fin=new FileInputStream(location);
            ObjectInputStream in =new ObjectInputStream(fin);
            permisos = (ArrayList<Permiso>) in.readObject();
        }
        System.out.println("[" + LocalTime.now().withNano(0) + "] Permisos cargados correctamente.");
    }
    
    /**
     * Método que se llama al cierre del Sistema y realiza los procesos de almacenado de información de colaboradores y permisos
     * @throws Exception Exception
     */
    @Override
    public void stop() throws Exception{
        updateUrbanizacion();
        exportColaboradores();
        updatePermisos();
        exportVisitantes();
    }
    
    /**
     * Escribe en el archivo colaboradores.csv la información nueva o modificada.
     */
    private static void exportColaboradores(){
        try {
            FileWriter writer = new FileWriter(CONSTANT.COLABORADORES_LOCATION, Charset.defaultCharset());
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.append(CONSTANT.COLABORADORES_HEADER);
                for (Colaborador colaborador : colaboradores){
                    bufferedWriter.append("\n");
                    bufferedWriter.append(colaborador.toString());
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Colaboradores almacenados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de colaboradores no encontrado");
        }
    }
    
    /**
     * Escribe en el archivo residentes.csv la información nueva o modificada.
     * @deprecated El sistema no modifica datos de residentes.     
     */
    @Deprecated
    private static void exportResidentes() {
        try {
            FileWriter writer = new FileWriter(CONSTANT.RESIDENTES_LOCATION, Charset.defaultCharset());
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.append(CONSTANT.RESIDENTES_HEADER);
                for (Residente residente : residentes){
                    bufferedWriter.append("\n");
                    bufferedWriter.append(residente.toString());
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Residentes almacenados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de residentes no encontrado");
        }
    }
    
    /**
     * Escribe en el archivo visitantes.csv la información nueva o modificada.
     */
    private static void exportVisitantes() {
        try {
            FileWriter writer = new FileWriter(CONSTANT.VISITANTES_LOCATION, Charset.defaultCharset());
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.append(CONSTANT.VISITANTES_HEADER);
                for (Visitante visitante : visitantes){
                    bufferedWriter.append("\n");
                    bufferedWriter.append(visitante.toString());
                }
            }
            System.out.println("[" + LocalTime.now().withNano(0) + "] Visitantes almacenados correctamente.");
        } catch (IOException e) {
            System.out.println("[" + LocalTime.now().withNano(0) + "] Archivo de visitantes no encontrado");
        }
    }
    
    /**
     * Serializa los permisos en el archivo permisos.permisos
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOException
     */
    public static void updatePermisos() throws FileNotFoundException, IOException {
        FileOutputStream fout=new FileOutputStream(CONSTANT.PERMISOS_LOCATION);
        try (ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeObject(permisos);
        }
        System.out.println("[" + LocalTime.now().withNano(0) + "] Permisos actualizados correctamente.");
    }
    
    /**
     * Serializa los datos de la urbanización en el archivo urbanizacion.urb
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOException
     */
    public static void updateUrbanizacion() throws FileNotFoundException, IOException{
        FileOutputStream fout=new FileOutputStream(CONSTANT.URBANIZACION_LOCATION);
        try (ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeObject(urbanizacion);
        }
        System.out.println("[" + LocalTime.now().withNano(0) + "] Datos de Urbanización guardados correctamente.");
    }
}