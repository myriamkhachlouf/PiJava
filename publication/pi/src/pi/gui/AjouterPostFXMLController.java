/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pi.entities.Publication;
import pi.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class AjouterPostFXMLController implements Initializable {

    @FXML
    private TextField tfPostTitle;
    @FXML
    private TextArea taPostContent;
    @FXML
    private Button AddPostBTN;
    private int PostId;
    public static int action;
    @FXML
    private Button UpdatePostBTN;
    @FXML
    private Text textNU;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    private boolean validateFields()
    {
        if(tfPostTitle.getText().isEmpty()| taPostContent.getText().isEmpty()){
           Alert alert= new Alert(AlertType.WARNING);
           alert.setTitle("Validate Fields");
           alert.setHeaderText(null);
           alert.setContentText("Fields Content Is Empty!");
           alert.showAndWait();
            return false;
        }
        if(tfPostTitle.getText().length()<5| taPostContent.getText().length()<10){
           Alert alert= new Alert(AlertType.WARNING);
           alert.setTitle("Validate Fields");
           alert.setHeaderText(null);
           alert.setContentText("Fields Content Is Too Short!");
           alert.showAndWait();
            return false;
        }
        return true;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(()->{
        if(action==1)
        {
            UpdatePostBTN.setVisible(true);
            AddPostBTN.setVisible(false);
            textNU.setText("Update Post");
        }
        else if(action==0){
            UpdatePostBTN.setVisible(false);
            AddPostBTN.setVisible(true);
            textNU.setText("New Post");
        }
         });  
    }    

    @FXML
    private void AddPost(ActionEvent event) {
            
        AddPostBTN.setOnAction(e->{
            if (validateFields()){
            Publication p= new Publication(); 
            p.setPostedby_id(user_id);
            p.setTitle(tfPostTitle.getText());
            p.setContenu(taPostContent.getText());
            new PublicationService().add(p);
            Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Post Added");
           alert.setHeaderText(null);
           alert.setContentText("Post Added Successfully!");
           alert.showAndWait();
            }
        });
        action=-1;
        
    }
    public void getTitle(String text){
        tfPostTitle.setText(text);
    }
    public void getPostId(Integer id){
        PostId=id;
    }
public void getAction(int b){
        action=b;
    }

    //lezemni neddhakar n7ot l bolean fil add 0 wfil update 1

    @FXML
    private void UpdatePost(ActionEvent event) {
        UpdatePostBTN.setOnAction(e->{
             if (validateFields()){
            Publication p= new Publication(); 
            p.setTitle(tfPostTitle.getText());
            p.setContenu(taPostContent.getText());
            p.setId(PostId);
            new PublicationService().Edit(p);
            Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Post Updated");
           alert.setHeaderText(null);
           alert.setContentText("Post Updated Successfully!");
           alert.showAndWait();
             }
             });
        action=-1;
    }
    
}
