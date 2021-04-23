/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pi.entities.Commentaire;
import pi.entities.Publication;
import static pi.gui.RoleFXMLController.role;
import pi.services.CommentaireService;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class PostCommentFXMLController implements Initializable {

    @FXML
    private Text USERIDCOMMENT;
    @FXML
    private Text WHEN;
    @FXML
    private VBox Comment;
    @FXML
    private ImageView editcommentbutton;
    @FXML
    private ImageView deletecommentbutton;
    @FXML
    private HBox CommentBox;
    private String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FCEDC6","EBD4D4"};
    @FXML
    private TextArea textcomment;
    private int comment_id;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      textcomment.setEditable(false);
      editcommentbutton.setVisible(false);
    }    
     public void setData(Commentaire commentaire) throws IOException{
        /* commentid.setText(Integer.toString(commentaire.getId()));
         commentid.setVisible(false);*/
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root =  loader.load();
       RoleFXMLController roleController=loader.getController(); 
       int id=roleController.getUser_id();
       String role=roleController.getRole();
      int user_id=new CommentaireService().getUser_id(commentaire.getId());
      if((id!=user_id) && (role.compareTo("admin")!=0))
        deletecommentbutton.setVisible(false);
      else if((id!=user_id) && (role.compareTo("admin")==0)) deletecommentbutton.setVisible(true);
      else if((id==user_id) && (role.compareTo("admin")!=0)) deletecommentbutton.setVisible(true);
        comment_id=commentaire.getId();
        USERIDCOMMENT.setText(commentaire.getPostername());
        textcomment.setText(commentaire.getContenu());
        Date date=commentaire.getUpdated_at();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
String strDate = dateFormat.format(date); 
        WHEN.setText(strDate);
        CommentBox.setStyle("-fx-background-color: #"+ colors[(int)(Math.random()*colors.length)]+";"+
                "-fx-background-radius: 15;"+
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.5),10,0,0,10);"+
                "-fx-padding-right: 20;"
                );
    }

    @FXML
    public void EditComment(MouseEvent event) throws IOException {
       if (textcomment.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Validate Fields");
           alert.setHeaderText(null);
           alert.setContentText("Please Enter Your New Comment");
           alert.showAndWait();
       }
       else{
       new CommentaireService().Edit(textcomment.getText(), comment_id);
       textcomment.setEditable(false);
       editcommentbutton.setVisible(false);
       }
    }

    @FXML
    private void deletecomment(MouseEvent event) {
       
        FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("PostDetailsFXML.fxml"));
    PostDetailsFXMLController detailsController = fxmlloader.getController();
       new CommentaireService().Delete(comment_id);
       Alert alert= new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Delete SUCCESSFULL");
           alert.setHeaderText(null);
           alert.setContentText("Comment Deleted Successfully! Please Refresh.");
           alert.showAndWait();
       
    }

    @FXML
    private void readyforedit(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root =  loader.load();
       RoleFXMLController roleController=loader.getController(); 
       int id=roleController.getUser_id();
      int user_id=new CommentaireService().getUser_id(comment_id);
      
        if(event.getClickCount()==2)
        {
            if(id==user_id){
            textcomment.setEditable(true);
            editcommentbutton.setVisible(true);
            }
        }
    }
    
}
