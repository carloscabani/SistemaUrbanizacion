package proyecto.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import proyecto.clases.Sistema;

/**
 * FXML Controller class
 * @author Carlos Cabanilla
 * @author Alexander Nieves
 */
public class MainViewController extends Thread implements Initializable {

    @FXML
    private ImageView background;
    @FXML
    private Pane mainPane;

    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.getChildren().clear();
        try {
            background = new ImageView(new Image(new FileInputStream("storage/images/background/gif.gif"),985, 410,false,false));
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        mainPane.getChildren().add(background);
    }

    @FXML
    private void goToMain(MouseEvent event) throws IOException {
        Sistema.setRoot("urbanizacionView");
    }
}
