/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_publication;
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
import Entities.Commentaire;
import Entities.Publication;
import Service.CommentaireService;
import javaapplication1.services.UsersService;
import javaapplication1.utils.MaConnexion;

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
    public String role;
    public int user_id;

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
       user_id=MaConnexion.getInstance().connectedUserID;
       role=new UsersService().getUserByID(user_id).getRoles();
    }    
     public void setData(Commentaire commentaire) throws IOException{
        
       
      int commentuser_id=new CommentaireService().getUser_id(commentaire.getId());
      if((commentuser_id!=user_id) && (role.compareTo("[\"ROLE_ADMIN\"]")!=0))
        deletecommentbutton.setVisible(false);
      else if((commentuser_id!=user_id) && (role.compareTo("[\"ROLE_ADMIN\"]")==0)) deletecommentbutton.setVisible(true);
      else if((commentuser_id==user_id) && (role.compareTo("[\"ROLE_ADMIN\"]")!=0)) deletecommentbutton.setVisible(true);
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
        
      int commentuser_id=new CommentaireService().getUser_id(comment_id);
      
        if(event.getClickCount()==2)
        {
            if(commentuser_id==user_id){
            textcomment.setEditable(true);
            editcommentbutton.setVisible(true);
            }
        }
    }
    
}
