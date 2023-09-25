package proyecto.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import proyecto.clases.Permiso;
import proyecto.clases.Reporte;
import proyecto.clases.Residente;
import proyecto.clases.Sistema;
import proyecto.tipos.EstadoPermiso;

/**
 * FXML Controller class
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 * @version 14.1.23
 */
public class ReportesController implements Initializable {
    /**
     * Caja de opciones que presenta la lista de residentes disponibles para elegir
     * @see Residente
     * @see Residente#toInformation()
     */
    @FXML
    private ComboBox<String> residentesBox;
    /**
     * Tabla que presenta los reportes de permisos generados por el usuario
     * @see Reporte
     * @see Permiso
     */
    @FXML
    private TableView<Reporte> reportesTable;
    /**
     * Etiqueta de la {@code manzana} del Residente elegido para el reporte
     * @see Residente
     */
    @FXML
    private Label manzana;
    /**
     * Etiqueta de la {@code villa} del Residente elegido para el reporte
     * @see Residente
     */
    @FXML
    private Label villa;
    /**
     * Etiqueta del {@code estado} del Residente elegido para el reporte
     * @see Residente
     */
    @FXML
    private Label estado;
    /**
     * Botón que genera el reporte
     * @see #generar(javafx.event.ActionEvent)
     */
    @FXML
    private Button generarButton;
    /**
     * Etiqueta para el marcador de posición de la {reportesTable}
     * @see #reportesTable
     */
    private Label promptText;
    @FXML
    private Pane paneUrbanizacion;
    @FXML
    private Pane paneColaborador;
    @FXML
    private Pane paneResidente;
    @FXML
    private Pane paneVisitante;
    @FXML
    private Pane panePermiso;
    @FXML
    private Pane paneRevision;
    @FXML
    private Pane paneReporte;
    @FXML
    private Pane paneSalir;
    
    private ImageView imagen;

    /**
     * Initializes the controller class.
     * @param url La ubicación utilizada para resolver las rutas relativas del objeto raíz, o null si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz no fue localizado.
     * @see #defaultOptions()
     * @see #residentesBox
     * @see #reportesTable
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Permiso permiso: Sistema.permisos){
            permiso.actualizarEstado(); //Evalúa los permisos activos a la fecha actual para decidir si ya caducaron o no
        }
        for(Residente residente : Sistema.residentes){
            if(residente.toFilteredInformation() != null)
                residentesBox.getItems().add(residente.toInformation());
        }
        residentesBox.setValue(residentesBox.getItems().get(0));
        final String[] header = "N°,Código Único,Estado,Visitante,Observaciones".split(",");
        final String[] columnas = "contador,codigoUnico,estado,visitante,observaciones".split(",");
        for(int i = 0; i < header.length; i++){
            reportesTable.getColumns().add(new TableColumn(header[i]));
            reportesTable.getColumns().get(i).setCellValueFactory(new PropertyValueFactory(columnas[i]));
        }
        defaultOptions();

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
    
    /**
     * Valores por defecto para una vista de reporte vacía
     */
    private void defaultOptions(){
        manzana.setText("Manzana: ");
        villa.setText("Villa: ");
        estado.setText("Estado: ");
        promptText = new Label("Ningún reporte Generado\nElija un residente y dé clic en \"Generar\"");
        promptText.setTextAlignment(TextAlignment.CENTER);
        reportesTable.setPlaceholder(promptText);
        generarButton.setDisable(false);
    }
    
    /**
     * Finaliza el programa
     * @see javafx.application.Platform
     * @param event recibe el evento del mouse
     */
    @FXML
    private void salir(MouseEvent event) {
        Platform.exit();
    }

    /**
     * Redirecciona a la ventana de Urbanización
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToUrbanizacion(MouseEvent event) throws IOException {
        Sistema.setRoot("urbanizacionView");
    }
    
    /**
     * Redirecciona a la ventana de Colaboradores
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToColaboradores(MouseEvent event) throws IOException {
        Sistema.setRoot("colaboradoresView");
    }

    /**
     * Redirecciona a la ventana de Residentes
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToResidentes(MouseEvent event) throws IOException {
        Sistema.setRoot("residentesView");
    }

    /**
     * Redirecciona a la ventana de Visitantes
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToVisitantes(MouseEvent event) throws IOException {
        Sistema.setRoot("visitantesView");
    }

    /**
     * Redirecciona a la ventana de Permisos
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToPermisos(MouseEvent event) throws IOException {
        Sistema.setRoot("permisosView");
    }

    /**
     * Redirecciona a la ventana de Revisión de Entrada
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToRevision(MouseEvent event) throws IOException {
        Sistema.setRoot("revisionView");
    }
    
    /**
     * Redirecciona a la ventana de Reportes
     * @param event recibe el evento del mouse
     * @throws IOException
     * @see Sistema#setRoot(java.lang.String)
     */
    @FXML
    private void goToReportes(MouseEvent event) throws IOException {
        Sistema.setRoot("reportesView");
    }
    
    /**
     * Genera un reporte con la cédula elegida en el {residentesBox}
     * @param event recibe el evento del mouse
     * @see #residentesBox
     */
    @FXML
    private void generar(ActionEvent event) {
        promptText.setText("El Residente no posee ningún permiso registrado");
        Residente residente = Sistema.residentes.get(Sistema.residentes.indexOf(new Residente(residentesBox.getValue().split(":")[1].strip())));
        manzana.setText(manzana.getText().concat(String.valueOf(residente.getManzana())));
        villa.setText(villa.getText().concat(String.valueOf(residente.getVilla())));
        estado.setText(estado.getText().concat(residente.getEstado().toString()));
        int i = 1;
        for(Permiso permiso : Sistema.permisos){
            if (permiso.getResidente().equals(residente)){
                reportesTable.getItems().add(new Reporte(i,permiso));
                i++;
            }
        }
        generarButton.setDisable(true);
    }
    
    /**
     * Devuelve a los valores iniciales cuando una nueva opción del {residentesBox} es elegida
     * @param event recibe el evento del mouse
     * @see #residentesBox
     * @see #defaultOptions()
     */
    @FXML
    private void selected(ActionEvent event) {
        defaultOptions();
        reportesTable.getItems().clear();
    }   
}