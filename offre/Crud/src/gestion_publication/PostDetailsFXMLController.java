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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Entities.Commentaire;
import Entities.Publication;
import Service.CommentaireService;
import Service.PublicationService;
import Service.UserServices;
import Service.UsersService;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class PostDetailsFXMLController implements Initializable {

    /**
     *
     */
    public static Map<Integer,HashSet<Integer>> PostSeenBy =new HashMap<>();
   
    private int commentid;
    @FXML
    private ImageView refresh;
    @FXML
    private Text postedby;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }
    private int PostId;
    @FXML
    private Text TextTitle;
    @FXML
    private TextArea tfContent;
    @FXML
    private Text createdAt;
    @FXML
    private Text nbrview;
    private List<Commentaire> comments;
    @FXML
    private VBox BOX;
    @FXML
    private ImageView addcomment;
    @FXML
    private TextField commentfield;
    private Commentaire CommentData;
    public String postername;
     public String role;
    public int user_id;
   public void setCursor(){
       commentfield.getCursor();
   }
    public void setcommentfield(String s)
    {
     commentfield.setText(s); 
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      user_id=MaConnexion.getInstance().connectedUserID;
       role=new UsersService().getUserByID(user_id).getRoles();  
       if(role.compareTo("[\"ROLE_ADMIN\"]")==0){
           addcomment.setVisible(false);
           commentfield.setVisible(false);
       }
      
       
        Platform.runLater(()->{
            comments =new ArrayList<>(comments(PostId)); 

            try {
                 if (PostSeenBy.containsKey(user_id))
                  {
                      System.out.println("already have");
                      if (PostSeenBy.get(user_id).contains(PostId))
                      System.out.println("already added");
                      else{
            new PublicationService().updateviews(PostId);
            PostSeenBy.get(user_id).add(PostId);
                      }
                  }
        else{
                      System.out.println("i ll add");
                      System.out.println(user_id);
             HashSet<Integer>s=new HashSet<Integer>();
            s.add(PostId);
            PostSeenBy.put(user_id, s);
            new PublicationService().updateviews(PostId);
            }   
                showData();
for (int i=0;i<comments.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("PostCommentFXML.fxml"));
    
        VBox commentbox = fxmlloader.load();
        PostCommentFXMLController commentController = fxmlloader.getController();
        
        commentController.setData(comments.get(i));
        BOX.getChildren().add(commentbox);
    }
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
         
        });
        
    }    
     public void showData() throws IOException{
         PublicationService ps=new PublicationService();
        Publication p=new Publication();
        p=ps.getPostById(PostId);
        postername=new PublicationService().getPosterName(PostId);
        postedby.setText(postername);
        TextTitle.setText(p.getTitle());
        tfContent.setText(p.getContenu());
        nbrview.setText(Integer.toString(p.getViews()));
        Date date = p.getCreatedAt();  
DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
String strDate = dateFormat.format(date); 
        createdAt.setText(strDate);
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PublicationFXML.fxml"));
       Parent root1 = (Parent) loader1.load();
       PublicationFXMLController PubController=loader1.getController();
      
     }
      public void setID(int id){
          this.PostId=id;
       
    }
       public int getID(){
          return this.PostId;
       
    }
       public List<Commentaire> comments(int id){
        List<Commentaire> ls=new ArrayList<>();
        ls= new CommentaireService().getPostComments(id);
        
        return ls;
    }

    @FXML
    private void SendComment(MouseEvent event) throws IOException {
         if (commentfield.getText().isEmpty())
         {
              Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Validate Fields");
           alert.setHeaderText(null);
           alert.setContentText("Please Enter Into Comment Field");
           alert.showAndWait();
         }
        else{
              
        Commentaire c = new Commentaire();
        c.setContenu(commentfield.getText());
        c.setPostedby_id(user_id);
        new CommentaireService().add(c, PostId);
        comments.clear();
        BOX.getChildren().clear();
        comments =new ArrayList<>(comments(PostId)); 

            try {
                showData();
for (int i=0;i<comments.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("PostCommentFXML.fxml"));
    
        VBox commentbox = fxmlloader.load();
        PostCommentFXMLController commentController = fxmlloader.getController();
        commentController.setData(comments.get(i));
        BOX.getChildren().add(commentbox);
        commentfield.clear();
       
    }
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }

    @FXML
    private void refresh(MouseEvent event) {
        comments.clear();
        BOX.getChildren().clear();
        comments =new ArrayList<>(comments(PostId)); 
        try {
             
                showData();
for (int i=0;i<comments.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("PostCommentFXML.fxml"));
    
        VBox commentbox = fxmlloader.load();
        PostCommentFXMLController commentController = fxmlloader.getController();
        commentController.setData(comments.get(i));
        BOX.getChildren().add(commentbox);
        commentfield.clear();
       
    }
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public Text getNbrview() {
        return nbrview;
    }

    public void setNbrview(Text nbrview) {
        this.nbrview = nbrview;
    }
    
}
 