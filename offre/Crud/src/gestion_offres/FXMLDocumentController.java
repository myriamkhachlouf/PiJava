/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;
import Entities.users;
import Entities.Offres;
import Service.ServiceOffres;
import utils.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import Service.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author 21624
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField tfid_offre;
    @FXML
    private Button Btn_modifier;
    @FXML
    private TextField tfnom_offre;
    @FXML
    private ComboBox<users> idpromotion_offre;
    private TextField tfimage_offre;
    @FXML
    private ComboBox<String> idtype_offre;
    @FXML
    private Button Btn_suivant;
    @FXML
    private Button Btn_ajouter;
    @FXML
    private AnchorPane parent;
    Image img=new Image("/images/icon 1.png");
    @FXML
    private Button image;
    String urlInserted;

   
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    UsersService serv=new UsersService();
      private void afficherCombo() {
      ObservableList<users> data = FXCollections.observableArrayList(serv.getAll());
        idpromotion_offre.setItems(data);
    }
    ObservableList<String> data2 = FXCollections.observableArrayList("stage","emploi");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        List<Integer>  list =new ArrayList<>();
        
          afficherCombo();
        //idpromotion_offre.setItems(data);
        idtype_offre.setItems(data2);
    }    

    @FXML
    private void ajouter_offre(ActionEvent event) throws SQLException {
         ServiceOffres aa = new ServiceOffres();
        Scanner sc = new Scanner(System.in);
        Connection cnx = MaConnexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        String SQL = "SELECT * FROM offre   WHERE id ='" + tfid_offre.getText() + "' ";
        rs = stm.executeQuery(SQL);
        if (!rs.next()) {
            if ((!tfnom_offre.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                    ) {
                String req = "insert into offre (id,entreprise_id,nom_offre,image_name,type)values('" + tfid_offre.getText() + "','" + idpromotion_offre.getValue().getId() + "','" + tfnom_offre.getText() + "' ,'" + urlInserted + "','" + idtype_offre.getValue() + "','')";
               
               try {
                    st = cnx.createStatement();
                    st.executeQuery(req);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                

            } 

            Offres a = new Offres();
            a.setId(Integer.parseInt(tfid_offre.getText()));
            a.setEntreprise_id(idpromotion_offre.getValue().getId());
            a.setNom_offre(tfnom_offre.getText());
            a.setImage_name(urlInserted);
            a.setType(idtype_offre.getValue());
            try {
                    
                aa.AddOffre(a);             
                tfid_offre.clear();
                  tfnom_offre.clear();
                                   
                
            } catch (SQLException ex) {

            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title("Offre ajouté")
                .text("Votre Offre a été ajouté avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();

    }

    @FXML
    private void modifier_offre(ActionEvent event) throws SQLException {
        ServiceOffres sr = new ServiceOffres();
        Offres a = new Offres();
        int id;
        id = Integer.parseInt(tfid_offre.getText());
        a.setId(Integer.parseInt(tfid_offre.getText()));
            a.setEntreprise_id(idpromotion_offre.getValue().getId());
            a.setNom_offre(tfnom_offre.getText());
            a.setImage_name(urlInserted);
            a.setType(idtype_offre.getValue());

        sr.ModifierOffre(id, a);
        
        tfid_offre.clear();
                  tfnom_offre.clear();
                   Notifications notificationBuilder = Notifications.create()
                .title("Offre Modifié")
                .text("Votre Offre a été modifié avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
                        
    }

    @FXML
    private void Suivant(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiers.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(" ");
        fc.getExtensionFilters().addAll();
        //--for single file
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //listView.getItems().add(selectedFile.getAbsolutePath());
            urlInserted = "file:///" + selectedFile.getAbsolutePath();
            String a="";
            for (int i=0;i<urlInserted.length();i++){
                
                
                a=a+urlInserted.charAt(i);
                if (urlInserted.charAt(i) =='\\'){
                    
                     a=a+"\\" ;
                }
            
            
            }
            
            urlInserted=a;
            
            
            image.setText(selectedFile.getAbsolutePath());
            

            //imageView1.setText(selectedFile.getName());
            /*
            Image image1 = new Image(selectedFile.toURI().toString());
            imageView1.setImage(image1);
             */
        } else {
            System.out.println("Not valid file");
        }
    }

    
}
