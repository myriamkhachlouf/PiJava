/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_publication;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Entities.Publication;
import Service.PublicationService;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class BlogHomeFXMLController implements Initializable {

    @FXML
    private HBox CardLayout;
    private List<Publication> recentlyAdded;
     private List<Publication> AllPosts;
     private List<Publication> SearchedPost;
     private List<Publication> PostedThisDay;
     private List<Publication> PostedThisMonth;
     private List<Publication> PostedThisYear;
    @FXML
    private GridPane PostContainer;
    private int id;
    @FXML
    private TextField searchbar;
    @FXML
    private ComboBox comboboxTri;
 
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList("This Year","This Month","This Day");
   comboboxTri.setItems(list);
recentlyAdded =new ArrayList<>(recentlyAdded()); 
AllPosts =new ArrayList<>(AllPosts()); 
int column=0;
int row=1;
try {
for (int i=0;i<recentlyAdded.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardController = fxmlloader.getController();
        cardController.setData(recentlyAdded.get(i));
        CardLayout.getChildren().add(cardBox);
    }
for (Publication p:AllPosts){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardcontroller = fxmlloader.getController();
        cardcontroller.setData(p);
       if (column == 3){
           column=0;
           ++row;
       }
       PostContainer.add(cardBox,column++,row);
}
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
    private List<Publication> recentlyAdded(){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getFourLast();
        return ls;
    }
    private List<Publication> AllPosts(){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getAll();
        return ls;
    }
    private List<Publication> SearchedPost(String s){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getSearchedPost(s);
        return ls;
    }
private List<Publication> PostedThisDay(){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getPostByDay();
        return ls;
    }
private List<Publication> PostedThisMonth(){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getPostByMonth();
        return ls;
    }
private List<Publication> PostedThisYear(){
        List<Publication> ls=new ArrayList<>();
        ls= new PublicationService().getPostByYear();
        return ls;
    }
    @FXML
    private void SearchForPosts(MouseEvent event) {
        recentlyAdded =new ArrayList<>(recentlyAdded()); 

        SearchedPost =new ArrayList<>(SearchedPost(searchbar.getText())); 
int column=0;
int row=1;
PostContainer.getChildren().clear();
recentlyAdded.clear();
try {
for (int i=0;i<recentlyAdded.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardController = fxmlloader.getController();
        cardController.setData(recentlyAdded.get(i));
        CardLayout.getChildren().add(cardBox);
    }

for (Publication p:SearchedPost){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardcontroller = fxmlloader.getController();
        cardcontroller.setData(p);
       if (column == 3){
           column=0;
           ++row;
       }
       PostContainer.add(cardBox,column++,row);
}
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void select(ActionEvent event) {
        String s= comboboxTri.getSelectionModel().getSelectedItem().toString();
        if (s.compareTo("This Year")==0)
        {
                   recentlyAdded =new ArrayList<>(recentlyAdded()); 

        PostedThisYear =new ArrayList<>(PostedThisYear()); 
int column=0;
int row=1;
PostContainer.getChildren().clear();
recentlyAdded.clear();
try {
for (int i=0;i<recentlyAdded.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardController = fxmlloader.getController();
        cardController.setData(recentlyAdded.get(i));
        CardLayout.getChildren().add(cardBox);
    }

for (Publication p:PostedThisYear){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardcontroller = fxmlloader.getController();
        cardcontroller.setData(p);
       if (column == 3){
           column=0;
           ++row;
       }
       PostContainer.add(cardBox,column++,row);
}
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        }
        else if (s.compareTo("This Month")==0)
        {
            recentlyAdded =new ArrayList<>(recentlyAdded()); 

        PostedThisMonth =new ArrayList<>(PostedThisMonth()); 
int column=0;
int row=1;
PostContainer.getChildren().clear();
recentlyAdded.clear();
try {
for (int i=0;i<recentlyAdded.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardController = fxmlloader.getController();
        cardController.setData(recentlyAdded.get(i));
        CardLayout.getChildren().add(cardBox);
    }

for (Publication p:PostedThisMonth){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardcontroller = fxmlloader.getController();
        cardcontroller.setData(p);
       if (column == 3){
           column=0;
           ++row;
       }
       PostContainer.add(cardBox,column++,row);
}
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        }
         else if (s.compareTo("This Day")==0)
        {
            recentlyAdded =new ArrayList<>(recentlyAdded()); 

        PostedThisDay =new ArrayList<>(PostedThisDay()); 
int column=0;
int row=1;
PostContainer.getChildren().clear();
recentlyAdded.clear();
try {
for (int i=0;i<recentlyAdded.size();i++){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardController = fxmlloader.getController();
        cardController.setData(recentlyAdded.get(i));
        CardLayout.getChildren().add(cardBox);
    }

for (Publication p:PostedThisDay){
    FXMLLoader fxmlloader=new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("CardFXML.fxml"));
    
        VBox cardBox = fxmlloader.load();
        CardFXMLController cardcontroller = fxmlloader.getController();
        cardcontroller.setData(p);
       if (column == 3){
           column=0;
           ++row;
       }
       PostContainer.add(cardBox,column++,row);
}
} catch (IOException ex) {
        Logger.getLogger(BlogHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        }
        
    }
}
