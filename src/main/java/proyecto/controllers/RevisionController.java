package proyecto.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import proyecto.clases.Colaborador;
import proyecto.clases.Permiso;
import proyecto.clases.Sistema;
import proyecto.tipos.EstadoPermiso;
import proyecto.tipos.TipoEmpleo;

/**
 * FXML Controller class
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 * @version 14.1.23
 */
public class RevisionController implements Initializable {
    private ImageView imagen;
    @FXML
    private Pane paneUrbanizacion, paneColaborador;
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
    @FXML
    private TextField codigoUnico;
    @FXML
    private Label noEncontrado;
    @FXML
    private TextField estado;
    @FXML
    private TextField residente;
    @FXML
    private TextField visitante;
    @FXML
    private TextField creado;
    @FXML
    private TextField visita;
    @FXML
    private TextField duracion;
    @FXML
    private TitledPane historialSancionesPanel;
    @FXML
    private TextArea sancionesVisitante;
    @FXML
    private Button cancelarVisita;
    @FXML
    private TitledPane registroIngresoPanel;
    @FXML
    private ComboBox<String> guardiasBox;
    @FXML
    private TextArea observacionesAdicionales;
    private Permiso permiso;
    @FXML
    private TitledPane detallesPermisoPanel;
    @FXML
    private Accordion accordion;
    @FXML
    private Button autorizarVisitaButton;
    @FXML
    private Button finalizarRevision;
    
    /**
     * Inicializa el constrolador
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Con fines de muestra
        System.out.println("Lista de permisos con fines de muestra de proyecto:");
        for(Permiso permiso : Sistema.permisos){
            System.out.println(permiso.getCodigoUnico() + " - " + permiso.getEstado().toString());
            System.out.println(permiso.getResidente().getNombre() + "(Residente)");
            System.out.println(permiso.getVisitante().getNombre() + "(Visitante)\n");
        }
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
        
        for(Permiso permiso: Sistema.permisos){
            permiso.actualizarEstado(); //Evalúa los permisos activos a la fecha actual para decidir si ya caducaron o no
        }
        defaultMode();
    }
    private void defaultMode(){
        estado.setText("Sin resultados");
        residente.setText("Sin resultados");
        visitante.setText("Sin resultados");
        creado.setText("Sin resultados");
        visita.setText("Sin resultados");
        duracion.setText("Sin resultados");
        noEncontrado.setVisible(false);
        historialSancionesPanel.setVisible(false);
        registroIngresoPanel.setVisible(false);
        sancionesVisitante.setEditable(false);
        cancelarVisita.setVisible(false);
        permiso = null;
        estado.setStyle("");
        accordion.setExpandedPane(detallesPermisoPanel);
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

    @FXML
    private void autorizarVisita(ActionEvent event) {
        //Llenando comboBox
        for(Colaborador colaborador : Sistema.colaboradores){
            if(colaborador.toFilterByInformation(TipoEmpleo.GUARDIA) != null)
                guardiasBox.getItems().add(colaborador.toFilterByInformation(TipoEmpleo.GUARDIA));
        }
        guardiasBox.setValue(guardiasBox.getItems().get(0));
        registroIngresoPanel.setVisible(true);
        accordion.setExpandedPane(registroIngresoPanel);
        historialSancionesPanel.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Identifícate e ingresa observaciones importantes sobre la visita\nSi no tienes ninguna solo da clic en \"Finalizar Registro\"");
        alert.show();
        observacionesAdicionales.setText("Ninguna");
    }

    @FXML
    private void cancelarVisitaButton(ActionEvent event) throws IOException {
        permiso.getVisitante().setSanciones(String.join(" | ", sancionesVisitante.getText().split("\n")));
        System.out.println(permiso.getVisitante().getSanciones());
        String[] observacion = sancionesVisitante.getText().split("\n");
        permiso.setObservacion(observacion[observacion.length - 1]);
        permiso.setEstado(EstadoPermiso.INACTIVO);
        Alert alert = new Alert(Alert.AlertType.ERROR, "Se ha cancelado la visita y se eliminó el permiso.");
        alert.setHeaderText(null);
        alert.setTitle("Visita Cancelada");
        alert.showAndWait();
        Sistema.setRoot("revisionView");
    }
    
    /**
     * Guardia no autoriza el ingreso y se solicita una explicación
     * @param event 
     */
    @FXML
    private void finalizarRevision(ActionEvent event) {
        autorizarVisitaButton.setDisable(true);
        sancionesVisitante.setEditable(true);
        cancelarVisita.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor agregue en el historial de sanciones el motivo por el cual no se autorizó la visita.");
        sancionesVisitante.setText(sancionesVisitante.getText() + "\nRechazado por: ");
        finalizarRevision.setDisable(true);
        alert.showAndWait();
    }

    @FXML
    private void finalizarRegistro(ActionEvent event) throws IOException {
        permiso.setObservacion(observacionesAdicionales.getText());
        permiso.getVisitante().setSanciones(permiso.getVisitante().getSanciones() + " | " + observacionesAdicionales.getText().strip());
        System.out.println(permiso.getVisitante().getSanciones());
        Colaborador guardia = Sistema.colaboradores.get(Sistema.colaboradores.indexOf(new Colaborador(guardiasBox.getValue().split(":")[1].strip())));
        permiso.setGuardia(guardia);
        permiso.setEstado(EstadoPermiso.USADO);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"La urbanización " + Sistema.urbanizacion.getNombre() + " está encantada de recibir una nueva visita");
        alert.show();
        Sistema.setRoot("revisionView");
    }

    @FXML
    private void buscarPermiso(ActionEvent event) throws IOException {
        defaultMode();
        permiso = new Permiso(codigoUnico.getText());
        if(Sistema.permisos.contains(permiso)){
            permiso = Sistema.permisos.get(Sistema.permisos.indexOf(permiso));
            estado.setText(permiso.getEstado().toString());
            residente.setText(permiso.getResidente().getNombre());
            visitante.setText(permiso.getVisitante().getNombre());
            creado.setText(permiso.getFechaCreacion().toString());
            visita.setText(permiso.getFechaVisita().toString());
            duracion.setText(permiso.getDuracion().toString());
            if (estado.getText().equals(EstadoPermiso.ACTIVO.toString())) {
                estado.setStyle("-fx-background-color:green;");
                if(!LocalDateTime.now().isBefore(permiso.getFechaVisita())){
                    sancionesVisitante.setText(permiso.getVisitante().getSanciones());
                    historialSancionesPanel.setVisible(true);
                    accordion.setExpandedPane(historialSancionesPanel);
                }else{
                    noEncontrado.setText("La visita está programada para las " + permiso.getFechaVisita());
                    noEncontrado.setVisible(true);
                }
            } else {
                estado.setStyle("-fx-background-color:red;");
                noEncontrado.setText("No se admiten visitas cuyo permiso se encuentre en " + estado.getText());
                noEncontrado.setVisible(true);
            }
        }else{
            noEncontrado.setText("No existe ningún permiso asociado a este código.");
            noEncontrado.setVisible(true);
        }
    }
}