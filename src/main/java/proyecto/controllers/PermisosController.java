package proyecto.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import proyecto.clases.Permiso;
import proyecto.clases.Residente;
import proyecto.clases.Sistema;
import proyecto.clases.Visitante;
import proyecto.tipos.EstadoPermiso;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.Event;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;


/**
 * FXML Controller class
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 * @version 14.1.23
 */
public class PermisosController implements Initializable {

    @FXML
    private ComboBox<String> comboResidentes, comboVisitantes, comboManzanaVilla;
    @FXML
    private ComboBox<LocalDate> comboFecha;
    @FXML
    private TextField txtHoraVisita, txtDuracionVisita, txtCodigo;
    @FXML
    private Button crear, deletePermiso;
    @FXML
    private Label alertFormat1, alertFormat2;
    
    private static Permiso permiso;
    //FECHA DE HOY
    private final LocalDateTime fechaHoy = LocalDateTime.now().minusMinutes(20).withNano(0);
    
    //FECHA DEL MISTERIOSO MAÑANA
    private final LocalDateTime fechaManana = LocalDateTime.now().minusMinutes(20).withSecond(0).withNano(0).plusDays(1);
    @FXML
    private TableView<Permiso> permisosTable;
    
    private static ArrayList<Permiso> consultaPermiso;
    
    @FXML
    private Pane paneEliminarPermiso;
    @FXML
    private Pane paneUrbanizacion, paneColaborador, paneResidente, paneVisitante;
    @FXML
    private Pane paneReporte, panePermiso, paneRevision, paneSalir, paneNuevoPermiso;
    @FXML
    private TextArea permisoArea;
    @FXML
    private Tab nuevoTab, eliminarTab;
    @FXML
    private TabPane permisosTabPane;
    
    private ImageView imagen;
    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        permisosTabPane.setStyle("");
        //Consultar TabPane
        String[] header = ("Código Único;Creado por;Visitante;Estado;Fecha de Creación;Fecha de Visita;Duración;Revisado por;Observación").split(";");
        String[] cellValues = ("codigoUnico;residenteNombre;visitanteNombre;estado;fechaCreacion;fechaVisita;duracion;guardiaNombre;observacion").split(";");
        for(int i = 0; i < header.length; i++){
            permisosTable.getColumns().add(new TableColumn(header[i]));
            permisosTable.getColumns().get(i).setCellValueFactory(new PropertyValueFactory(cellValues[i]));
        }
        //llenado comboBox
        for(Residente residente : Sistema.residentes){
            comboManzanaVilla.getItems().add(residente.getManzana() + " | " + residente.getVilla());
        }
        comboManzanaVilla.setValue(comboManzanaVilla.getItems().get(0));
        consultarTabDefault();
        
        //Nuevo Permiso TabPane
        comboFecha.getItems().addAll(fechaHoy.toLocalDate(),fechaManana.toLocalDate());
        //LLENAMOS EL COMBO BOX DE RESIDENTES
        for(Residente residente : Sistema.residentes){
            if(residente.toFilteredInformation() != null)
                comboResidentes.getItems().add(residente.toFilteredInformation());
        }
        //LLENAMOS EL COMBO BOX DE VISITANTES
        for(Visitante visitante : Sistema.visitantes){
            if(visitante.toFilteredInformation() != null)
                comboVisitantes.getItems().add(visitante.toFilteredInformation());
        }
        comboFecha.setValue(comboFecha.getItems().get(0));
        permiso = new Permiso();
        if (comboFecha.getValue().equals(fechaHoy.toLocalDate())){
            txtHoraVisita.setTooltip(new Tooltip("Disponibilidad\nDesde " + permiso.getFechaCreacion().plusMinutes(15).toLocalTime() + "\nhasta 23:59"));
        }else {
            txtHoraVisita.setTooltip(new Tooltip("Disponibilidad\nDesde 00:01 hasta " + permiso.getFechaCreacion().toLocalTime() + " del día de mañana"));
        }
        comboResidentes.setValue(comboResidentes.getItems().get(0));
        comboVisitantes.setValue(comboVisitantes.getItems().get(0));
        nuevoTabDefault();
        //Imagen en la pestaña CREAR PERMISO
//        try{
//        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\permiso.png"),141,131,false,false));
//        }catch (Exception e){}
//        paneNuevoPermiso.getChildren().add(imagen);
//        imagen.setLayoutX(24);
//        imagen.setLayoutY(90);
        
        //Eliminar Permiso TabPane
        eliminarTabDefault();
        //Imagen en la pestaña ELIMINAR PERMISO
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\miLoco.png"),328,175,false,false));
        }catch (FileNotFoundException e){}
        paneEliminarPermiso.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(96);
        
        //Agregamos la imagen del icono a cada opcion:
        
        //URBANIZACION
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoUrbanizacion.png"),25,25,false,false));
        }catch (Exception e){}
        paneUrbanizacion.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //COLABORADOR
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoColaborador.png"),25,25,false,false));
        }catch (Exception e){}
        paneColaborador.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //RESIDENTE
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoResidente.png"),25,25,false,false));
        }catch (Exception e){}
        paneResidente.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //VISITANTE
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoVisitante.png"),25,25,false,false));
        }catch (Exception e){}
        paneVisitante.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //PERMISOS DE ENTRADA
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoPermiso.png"),25,25,false,false));
        }catch (Exception e){}
        panePermiso.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //REVISION DE ENTRADA
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoRevision.png"),25,25,false,false));
        }catch (Exception e){}
        paneRevision.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //REPORTES
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoReporte.png"),25,25,false,false));
        }catch (Exception e){}
        paneReporte.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
        
        //SALIR
        try{
        imagen = new ImageView(new Image(new FileInputStream("storage\\images\\background\\iconoSalida.png"),25,25,false,false));
        }catch (Exception e){}
        paneSalir.getChildren().add(imagen);
        imagen.setLayoutX(14);
        imagen.setLayoutY(7);
    }
    
    //Tab Nuevo Permiso
    private void nuevoTabDefault(){
        //Evalúa los permisos activos a la fecha actual para decidir si ya caducaron o no
        for(Permiso permiso: Sistema.permisos){
            permiso.actualizarEstado();
        }
        //Definiendo valores por defecto
        txtDuracionVisita.setText("");
        txtHoraVisita.setText("");
        txtDuracionVisita.setDisable(true);
        crear.setDisable(true);
    }

    @FXML
    private void crearPermiso(ActionEvent event) throws IOException {
        //ALERTA DE CONFIRMACION
        final Visitante visitante = Sistema.visitantes.get(Sistema.visitantes.indexOf(new Visitante(comboVisitantes.getValue().split(":")[1].strip())));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de permiso");
        alert.setContentText("Historial de Sanciones del visitante:\n" + visitante.getSanciones() + "\n¿Deseas registrar el permiso?");
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.OK)){
            final String hora[] = txtHoraVisita.getText().split(":");
            final String[] duracion = txtDuracionVisita.getText().split(":");
            final Residente residente = Sistema.residentes.get(Sistema.residentes.indexOf(new Residente(comboResidentes.getValue().split(":")[1].strip())));
            permiso.setDuracion(LocalTime.of(Integer.parseInt(duracion[0]), Integer.parseInt(duracion[1])).plusMinutes(15));
            permiso.setFechaVisita(LocalDateTime.of(comboFecha.getValue(), LocalTime.of(Integer.parseInt(hora[0]), Integer.parseInt(hora[1]))).minusMinutes(15));
            permiso.setEstado(EstadoPermiso.ACTIVO);
            permiso.setResidente(residente);
            permiso.setVisitante(visitante);
            permiso.crearCodigoUnico();
            
            ButtonType copiar = new ButtonType("Copiar Código", ButtonBar.ButtonData.OTHER);
            alert = new Alert(Alert.AlertType.INFORMATION,"Código Único Generado:\n" + permiso.getCodigoUnico() + "\nAsegúrate de que el visitante reciba esta información.",copiar,ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("Permiso Creado Correctamente");
            alert.showAndWait();
            if(alert.getResult().equals(copiar)){
                StringSelection stringSelection = new StringSelection(permiso.getCodigoUnico());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);    
            }
            Sistema.permisos.add(permiso);
        } else{
            alert = new Alert(Alert.AlertType.ERROR,"Proceso de creación de permiso anulado");
            alert.setTitle("Proceso anulado");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        nuevoTabDefault();
        permisosTabPane.getSelectionModel().select(nuevoTab);
    }
    
    @FXML
    private void isHora(KeyEvent event){
        if(event.getSource().equals(txtDuracionVisita)){
            if(!isHora(txtDuracionVisita.getText())){
                crear.setDisable(true);
                alertFormat2.setText("Formato de hora incorrecto");
            }else{
                crear.setDisable(false);
                alertFormat2.setText("");
            }
        }
        if(event.getSource().equals(txtHoraVisita)){
            if(!isHora(txtHoraVisita.getText())){
                crear.setDisable(true);
                alertFormat1.setText("Formato de hora incorrecto");
                txtDuracionVisita.setDisable(true);
            }else{
                crear.setDisable(false);
                alertFormat1.setText("");
                txtDuracionVisita.setDisable(false);
                LocalTime hhmm = LocalTime.of(Integer.parseInt(txtHoraVisita.getText().split(":")[0]), Integer.parseInt(txtHoraVisita.getText().split(":")[1]));
                //Valida la hora cuando el rango elegido es hoy
                if(comboFecha.getValue().equals(fechaHoy.toLocalDate()) && hhmm.isAfter(permiso.getFechaCreacion().plusMinutes(14).toLocalTime()) && hhmm.isBefore(LocalTime.of(23, 59))){
                    crear.setDisable(false);
                    alertFormat1.setText("");
                } else if(comboFecha.getValue().equals(fechaManana.toLocalDate()) && hhmm.isAfter(LocalTime.of(0, 1).minusMinutes(1)) && hhmm.isBefore(permiso.getFechaCreacion().toLocalTime())){
                    crear.setDisable(false);
                    alertFormat1.setText("");
                } else{ //Todo esta mal
                    alertFormat1.setText("Horario no disponible");
                    crear.setDisable(true);
                }
            }
        }
    }
    
    @FXML
    private void fechas(ActionEvent event) {
        if (comboFecha.getValue().equals(fechaHoy.toLocalDate())){
            txtHoraVisita.setTooltip(new Tooltip("Disponibilidad\nDesde " + permiso.getFechaCreacion().plusMinutes(15).toLocalTime() + "\nhasta 23:59"));
        }else {
            txtHoraVisita.setTooltip(new Tooltip("Disponibilidad\nDesde 00:01 hasta " + permiso.getFechaCreacion().toLocalTime() + " del día de mañana"));
        }
    }
    //Fin de Tab Nuevo Permiso
    
    //Tab Eliminar Permiso
    private void eliminarTabDefault(){
        //Evalúa los permisos activos a la fecha actual para decidir si ya caducaron o no
        for(Permiso permiso: Sistema.permisos){
            permiso.actualizarEstado();
        }
        
        deletePermiso.setDisable(true);
        txtCodigo.setText("");
        permisoArea.setText("");
    }
    
    @FXML
    private void buscarPermiso(ActionEvent event) {
        permiso = new Permiso(txtCodigo.getText());
        if(Sistema.permisos.contains(permiso)){
            permiso = Sistema.permisos.get(Sistema.permisos.indexOf(permiso));
            permisoArea.setText(permiso.toString());
            if(permiso.getEstado().equals(EstadoPermiso.ACTIVO)){
                deletePermiso.setDisable(false);
                deletePermiso.setDefaultButton(true);
            } else {
                //No se eliminan permisos que no sean de estado activo
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"No se permite eliminar permisos en estado " + permiso.getEstado().toString());
                alert.setHeaderText(null);
                alert.setTitle("Resultado de la Búsqueda");
                alert.showAndWait();
                eliminarTabDefault();
                permisosTabPane.getSelectionModel().select(eliminarTab);
            }
        } else {
            permisoArea.setText("No existe ningún permiso asociado al código proporcionado");
            deletePermiso.setDisable(true);
        }
    }
    
    @FXML
    private void eliminarPermiso(ActionEvent event) throws IOException {
        Alert alert;
        //Confirmación eliminación
        alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de eliminar el permiso?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de Eliminación");
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.OK)){
            permiso.setEstado(EstadoPermiso.INACTIVO);
            //Información de eliminacion
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Permiso Eliminado");
            alert.setContentText("Se ha eliminado el permiso");
            alert.showAndWait();
        } else {
            //Cancelación de eliminacion
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Proceso de Eliminación Cancelado");
            alert.setContentText("El permiso no ha sido eliminado");
            alert.showAndWait();
        }
        eliminarTabDefault();
        permisosTabPane.getSelectionModel().select(eliminarTab);
    }
    //Fin de Tab Eliminar Permiso
    
    //Tab Consultar Permiso
    private void consultarTabDefault(){
        permisosTable.getItems().clear();
        //Evalúa los permisos activos a la fecha actual para decidir si ya caducaron o no
        for(Permiso permiso: Sistema.permisos){
            permiso.actualizarEstado();
        }
        final Label promp = new Label("Aquí se visualizan los permisos en caso de que existan.");
        promp.setTextAlignment(TextAlignment.CENTER);
        permisosTable.setPlaceholder(promp);
    }

    @FXML
    private void consultarPermiso(ActionEvent event) {
        int manzana = Integer.parseInt(comboManzanaVilla.getValue().split("\\|")[0].strip());
        int villa = Integer.parseInt(comboManzanaVilla.getValue().split("\\|")[1].strip());
        consultaPermiso = new ArrayList<>();
        for(Permiso permiso : Sistema.permisos){
            if(permiso.getResidente().getManzana() == manzana && permiso.getResidente().getVilla() == villa){
                //Se agregan al conjunto solo los permisos que coinciden con la manzana y villa recibida
                consultaPermiso.add(permiso);
            }
        }
        Collections.sort(consultaPermiso);
        permisosTable.getItems().setAll(consultaPermiso);
    }
    
    @FXML
    private void selected(ActionEvent event) {
        consultarTabDefault();
    }
    //Fin de Tab Consultar Permiso
    
    /**
     * Validación para analizar si un texto cumple con el formato de hora
     * @param hora Texto a validar
     * @return true si se puede convertir a LocalTime, falso sino
     */
    private static boolean isHora(String hora){
        try{
            String[] formato = hora.split(":");
            LocalTime.of(Integer.parseInt(formato[0]), Integer.parseInt(formato[1]));
            return true; //La hora está dentro del rango (0 a 23) y sus minutos también (0 a 59)
        } catch(Exception e){
            return false;
        }
    }
    
    //Conexiones del menú principal
    @FXML
    private void salir(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void goToUrbanizacion(MouseEvent event) throws IOException {
        Sistema.setRoot("urbanizacionView");
    }

    @FXML
    private void goToColaboradores(MouseEvent event) throws IOException {
        Sistema.setRoot("colaboradoresView");
    }

    @FXML
    private void goToResidentes(MouseEvent event) throws IOException {
        Sistema.setRoot("residentesView");
    }

    @FXML
    private void goToVisitantes(MouseEvent event) throws IOException {
        Sistema.setRoot("visitantesView");
    }

    @FXML
    private void goToPermisos(MouseEvent event) throws IOException {
        Sistema.setRoot("permisosView");
    }

    @FXML
    private void goToRevision(MouseEvent event) throws IOException {
        Sistema.setRoot("revisionView");
    }

    @FXML
    private void goToReportes(MouseEvent event) throws IOException {
        Sistema.setRoot("reportesView");
    }

    @FXML
    private void nuevoDefault(Event event) throws IOException {
        nuevoTabDefault();
    }

    @FXML
    private void eliminarDefault(Event event) throws IOException {
        eliminarTabDefault();
    }

    @FXML
    private void consultarDefault(Event event) throws IOException {
        consultarTabDefault();
    }

}