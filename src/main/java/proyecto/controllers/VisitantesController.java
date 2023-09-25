package proyecto.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import proyecto.tipos.CONSTANT;
import proyecto.clases.Sistema;
import proyecto.clases.Visitante;

/**
 * Controlador de la ventana de Visitantes
 * @author Alexander Nieves
 */
public class VisitantesController implements Initializable {

    @FXML
    private TableView<Visitante> tableVisitantes;
    
    private ImageView imagen;
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

    /**
     * Inicializa el controlador
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] headshot = CONSTANT.VISITANTES_HEADER.split(";");
        String[] cellValues =("cedula;nombre;telefono;email;estado;empresa;sanciones").split(";");
        
        for(int i=0; i<headshot.length; i++){
            tableVisitantes.getColumns().add(new TableColumn(headshot[i]));
            tableVisitantes.getColumns().get(i).setCellValueFactory(new PropertyValueFactory(cellValues[i]));
        }
        tableVisitantes.getItems().setAll(Sistema.visitantes);
        
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

}
