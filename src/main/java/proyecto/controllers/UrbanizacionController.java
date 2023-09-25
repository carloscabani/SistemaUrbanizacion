package proyecto.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import proyecto.clases.Colaborador;
import proyecto.clases.Sistema;
import proyecto.tipos.TipoEmpleo;

/**
 * FXML Controller class
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 * @version 14.1.23
 */
public class UrbanizacionController implements Initializable {

    @FXML
    private TextField txtNombre, txtEtapa, txtDireccion, txtContacto, txtConstructora;
    
    @FXML
    private Label alertMessage;
    
    @FXML
    private ComboBox<String> adminList;
    @FXML
    private Button cancelar;
    @FXML
    private Button editar;
    @FXML
    private Button guardar;
    @FXML
    private TextField txtAdmin;
    @FXML
    private Label rotulo;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rotulo.setText("Vista Urbanización");
        String email = "noDisponible@noDisponible.com";
        String admin = "No Disponible";
        try{
            admin = Sistema.urbanizacion.getAdmin().getNombre();
            email = Sistema.urbanizacion.getAdmin().getEmail();
        }catch(Exception e){}
        txtNombre.setText(Sistema.urbanizacion.getNombre());
        txtEtapa.setText(Sistema.urbanizacion.getEtapa());
        txtDireccion.setText(Sistema.urbanizacion.getDireccion());
        txtContacto.setText(email);
        txtConstructora.setText(Sistema.urbanizacion.getConstructora());
        txtAdmin.setText(admin);
        for(Colaborador colaborador : Sistema.colaboradores){
            if(colaborador.toFilterByInformation(TipoEmpleo.ADMINISTRADOR) != null)
                adminList.getItems().add(colaborador.toFilterByInformation(TipoEmpleo.ADMINISTRADOR));
        }
        adminList.setValue(adminList.getItems().get(0));
        
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
    
    @FXML
    private void guardar(ActionEvent event) throws IOException {
        Sistema.urbanizacion.setNombre(txtNombre.getText());
        Sistema.urbanizacion.setConstructora(txtConstructora.getText());
        Sistema.urbanizacion.setDireccion(txtDireccion.getText());
        if(adminList.getValue() != null){
            String cedula = adminList.getValue().split(":")[1].strip();
            Colaborador admin = new Colaborador(cedula);
            admin = Sistema.colaboradores.get(Sistema.colaboradores.indexOf(admin));
            Sistema.urbanizacion.setAdmin(admin);
            Sistema.urbanizacion.setEmailAdmin(txtContacto.getText());
            admin.setEmail(txtContacto.getText());
        }else{
            Sistema.urbanizacion.setEmailAdmin("noDisponible@noDisponible.com");
            Sistema.urbanizacion.setAdmin(null);             
        }
        Sistema.urbanizacion.setEtapa(txtEtapa.getText());
        Sistema.setRoot("urbanizacionView");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cambios actualizados", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Edición Completa");
        alert.show();
        Sistema.updateUrbanizacion();
    }

    @FXML
    private void isCorreo(KeyEvent event) {
        if(txtContacto.isEditable()){
            try{
                String[] estructura = txtContacto.getText().split("@");
                if(estructura[1].split("\\.").length >= 2){
                    //Si la validación llega hasta aquí, significa que el correo tiene un formato correcto
                    alertMessage.setText("");
                    guardar.setDisable(false);
                }
            }catch(Exception e){
                //Hubo un error al tratar de encontrar algún elemento cuando se hizo el split
                alertMessage.setText("Formato de correo no válido.");
                guardar.setDisable(true);
            }
        }
    }

    @FXML
    private void selectOption(ActionEvent event) {
        String cedula = adminList.getValue().split(":")[1].strip();
        Colaborador admin = Sistema.colaboradores.get(Sistema.colaboradores.indexOf(new Colaborador(cedula)));
        txtContacto.setText(admin.getEmail());
    }

    @FXML
    private void editMode(ActionEvent event) {
        //Activando campos editables
        txtConstructora.setEditable(true);
        txtContacto.setEditable(true);
        txtContacto.setPromptText(txtContacto.getText());
        txtContacto.setText("");
        txtDireccion.setEditable(true);
        txtEtapa.setEditable(true);
        txtNombre.setEditable(true);
        txtAdmin.setVisible(false);
        adminList.setVisible(true);
        //Activando botones
        editar.setDisable(true);
        guardar.setDisable(false);
        cancelar.setVisible(true);
        //Cambiando rótulo
        rotulo.setText("Vista Urbanización (Modo Edición)");
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        Sistema.setRoot("urbanizacionView");
    }
}
