/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import pi.entities.Publication;
import pi.services.PublicationService;
import javax.mail.Session;
import static pi.gui.RoleFXMLController.user_id;
import pi.utils.SendMail;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class PublicationFXMLController implements Initializable {
Map<Integer,HashSet<Integer>> PostSeenBy =new HashMap<>();  
// public Set<Integer> hasSeen= new HashSet<Integer>();
    @FXML
    private TableView<Publication> PostsTable;
    @FXML
    private TableColumn<Publication, String> postidcol;
    @FXML
    private TableColumn<Publication, String> titlecol;
    @FXML
    private TableColumn<Publication, Date> createdatcol;
    @FXML
    private TableColumn<Publication, Date> updatedatcol;
    @FXML
    private TableColumn<Publication, String> viewscol;

  ObservableList<Publication> PostList= FXCollections.observableArrayList();
    @FXML
    private Text lbPostedby;
    @FXML
    private Button newPostbtn;
    @FXML
    private Button UpdatePostBTN;
    @FXML
    private Button DeletePostBTN;
    @FXML
    private Button newHome;
    @FXML
    private ImageView refreshicon;
    @FXML
    private AnchorPane o;
    @FXML
    private ImageView moredetails;
    private String role;
    private int user_id;
    public int views;
    @FXML
    private Text textreasons;
    @FXML
    private TextArea tareasons;
    @FXML
    private ImageView sendmail;
    private int post_id;
    @FXML
    private TextField filterBar;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textreasons.setVisible(false);
        tareasons.setVisible(false);
        sendmail.setVisible(false); 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PublicationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       RoleFXMLController roleController=loader.getController();  
       user_id=roleController.getUser_id();
       role=roleController.getRole();
       if(role.compareTo("admin")==0){
           UpdatePostBTN.setVisible(false);
           newPostbtn.setVisible(false);
       }
       Platform.runLater(()->{
        try {
            loadData();
            FilteredList<Publication>filteredData=new FilteredList<>(PostList,b->true);
            filterBar.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(publication-> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();
                    if (publication.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                     if (Integer.toString(publication.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    else return false;
                });
            });
            SortedList<Publication> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(PostsTable.comparatorProperty());
            PostsTable.setItems(sortedData);
        } catch (IOException ex) {
            Logger.getLogger(PublicationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       });
    }    
    private void loadData() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root = (Parent) loader.load();
       RoleFXMLController roleController=loader.getController();  
       
       role=roleController.getRole();
              user_id= roleController.getUser_id();
        PostList.clear();
        if (role.compareTo("admin")==0)
        {ArrayList<Publication> p=new PublicationService().getAll();
          for (Publication pub:p){
           PostList.add(pub);
           PostsTable.setItems(PostList);
       }}
        else if (role.compareTo("entreprise")==0) {
            ArrayList<Publication> p=new PublicationService().getUserPosts(user_id);
          for (Publication pub:p){
           PostList.add(pub);
           PostsTable.setItems(PostList);  
        }
       //ObservableList<Publication> PublicationList = FXCollections.observableArrayList();
       
       }
        postidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title") );
        createdatcol.setCellValueFactory(new PropertyValueFactory<>("createdAt") );
        updatedatcol.setCellValueFactory(new PropertyValueFactory<>("updatedAt") );
        viewscol.setCellValueFactory(new PropertyValueFactory<>("views") );
    }

    @FXML
    private void AddPostWindow(ActionEvent event) throws IOException {
        //URL url = new File("src/main/java/ua/adeptius/goit/sample.fxml").toURI().toURL();
//Parent root = FXMLLoader.load(url);
      // Parent root = FXMLLoader.load(getClass().getResource("AjouterPostFXML.fxml"));
    
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPostFXML.fxml"));
       Parent root = (Parent) loader.load();
       AjouterPostFXMLController AjouterController=loader.getController();  
     AjouterController.getAction(0);
     AjouterController.setUser_id(user_id);
       Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
 
    }

   

    @FXML
    private void UpdatePost(ActionEvent event) throws IOException {
      
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPostFXML.fxml"));
       Parent root = (Parent) loader.load();
       AjouterPostFXMLController AjouterController=loader.getController();
       AjouterController.getTitle(PostsTable.getSelectionModel().getSelectedItem().getTitle());
       AjouterController.getPostId(PostsTable.getSelectionModel().getSelectedItem().getId());
       AjouterController.getAction(1);
       Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void PostWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostDetailsFXML.fxml"));
       Parent root = (Parent) loader.load();
       PostDetailsFXMLController DetailsController=loader.getController();
       //System.out.println(PostsTable.getSelectionModel().getSelectedItems().get(0).getId());
       DetailsController.setID(PostsTable.getSelectionModel().getSelectedItems().get(0).getId());
       Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void DeletePost(ActionEvent event) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PublicationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       RoleFXMLController roleController=loader.getController();  
       post_id=PostsTable.getSelectionModel().getSelectedItem().getId();
       role=roleController.getRole();
       if(role.compareTo("entreprise")==0){
        DeletePostBTN.setOnAction(e->{
            
            new PublicationService().Delete(PostsTable.getSelectionModel().getSelectedItem().getId());
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Post Deleted");
           alert.setHeaderText(null);
           alert.setContentText("Post Deleted Successfully");
           alert.showAndWait();
        });
        
       }
       else if(role.compareTo("admin")==0){
           textreasons.setVisible(true);
        tareasons.setVisible(true);
        sendmail.setVisible(true); 
        DeletePostBTN.setVisible(false);
       }
        
    }

    @FXML
    private void newHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BlogHomeFXML.fxml"));
       Parent root = (Parent) loader.load(); 
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
    }

    @FXML
    private void LoadData(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root = (Parent) loader.load();
       RoleFXMLController roleController=loader.getController();  
       
       role=roleController.getRole();
              user_id= roleController.getUser_id();
        PostList.clear();
        if (role.compareTo("admin")==0)
        {ArrayList<Publication> p=new PublicationService().getAll();
          for (Publication pub:p){
           PostList.add(pub);
           PostsTable.setItems(PostList);
       }}
        else if (role.compareTo("entreprise")==0) {
            ArrayList<Publication> p=new PublicationService().getUserPosts(user_id);
          for (Publication pub:p){
           PostList.add(pub);
           PostsTable.setItems(PostList);  
        }
       }
        postidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title") );
        createdatcol.setCellValueFactory(new PropertyValueFactory<>("createdAt") );
        updatedatcol.setCellValueFactory(new PropertyValueFactory<>("updatedAt") );
        viewscol.setCellValueFactory(new PropertyValueFactory<>("views") );
    }

    @FXML
    private void MoreDetails(MouseEvent event) throws IOException {
        int index;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostDetailsFXML.fxml"));
       Parent root = (Parent) loader.load();
       PostDetailsFXMLController DetailsController=loader.getController();
       DetailsController.setID(PostsTable.getSelectionModel().getSelectedItems().get(0).getId());
       Publication p=new Publication();
       p.setId(PostsTable.getSelectionModel().getSelectedItems().get(0).getId());
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PublicationFXML.fxml"));
       Parent root1 = (Parent) loader1.load();
       PublicationFXMLController PubController=loader1.getController();
       int PostId=PostsTable.getSelectionModel().getSelectedItems().get(0).getId();
      
       ///////////////
       Stage stage =new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void SendMail(MouseEvent event) throws Exception {
       String reasons=tareasons.getText();
        String email=new PublicationService().getUserEmailById(post_id);
        SendMail.sendMail(email,reasons);
         textreasons.setVisible(false);
        tareasons.setVisible(false);
        sendmail.setVisible(false); 
        DeletePostBTN.setVisible(true);
        new PublicationService().Delete(post_id);
    }
    
}
