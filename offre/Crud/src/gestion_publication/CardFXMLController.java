/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_publication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Entities.Publication;
import Service.PublicationService;


/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class CardFXMLController implements Initializable {

    @FXML
    private VBox BOX;
    @FXML
    private ImageView IMAGE;
    @FXML
    private Label TITLE;
    @FXML
    private Button READMOREBTN;
private String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FCEDC6","EBD4D4"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Publication publication){
        TITLE.setText(publication.getTitle());
        BOX.setStyle("-fx-background-color: #"+ colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 15;"+
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.5),10,0,0,10);"+
                "-fx-padding-right: 20;"
                );
    }

    @FXML
    private void ReadPost(ActionEvent event) throws IOException {
        Integer id=new PublicationService().getIdFromTitle(TITLE.getText());
         FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("PostDetailsFXML.fxml"));
      
        Parent root = (Parent) fxmlloader.load();
       PostDetailsFXMLController deltailsController=fxmlloader.getController();
      // AjouterController.getPostId(PostsTable.getSelectionModel().getSelectedItem().getId());
       deltailsController.setID(id);
       Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
//readblog na3mel attribut id ye5ou l id mta3 l blog w ba3d yeba3thou m3a lcontroller