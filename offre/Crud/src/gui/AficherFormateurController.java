/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.test.utils.mailxd;
import entities.formateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.formateurCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import entities.donnéeformateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AficherFormateurController implements Initializable {

    @FXML
    private TableView<formateur> tableauformateur;
    @FXML
    private TableColumn<formateur, Integer> affid;
    @FXML
    private TableColumn<formateur, String> affnom;
    @FXML
    private TableColumn<formateur, String> affprenom;
    @FXML
    private TableColumn<formateur, String> affstatut;
    @FXML
    private TableColumn<formateur, String> affccontrat;
    @FXML
    private TableColumn<formateur, String> affemail;
    @FXML
    private TableColumn<formateur, Integer> affpass;
    
    formateurCrud typeCRUD = new formateurCrud();
    ObservableList<formateur> listType;
    @FXML
    private TextField research;
    @FXML
    private Button retour;
    @FXML
    private Button mail;
    @FXML
    private TextField maail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
       listType = (ObservableList<formateur>) typeCRUD.listertype();
        affid.setCellValueFactory(new PropertyValueFactory<>("id"));
        affnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        affprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        affstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        affccontrat.setCellValueFactory(new PropertyValueFactory<>("typecontrat"));
        affemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        affpass.setCellValueFactory(new PropertyValueFactory<>("password"));
       
        tableauformateur.setItems(listType);
    }   catch (SQLException ex) {
            System.out.println("hhhhhh ");
        }
tableauformateur.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
    entities.formateur r = (entities.formateur) tableauformateur.getSelectionModel().getSelectedItem();
              if (tableauformateur.getSelectionModel().getSelectedItem() != null) {
                    entities.formateur entretien = (entities.formateur) tableauformateur.getSelectionModel().getSelectedItem();
                    
                    
                    System.out.println();
                    maail.setText(String.valueOf(entretien.getId()));
              System.out.println(maail.getText());
              }
    }    
        });
 }

    @FXML
    private void suppFormateur(ActionEvent event) throws IOException {
        
        formateur TypeSelect = (formateur) tableauformateur.getSelectionModel().getSelectedItem();
        if ((TypeSelect == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vérifier les champs");
            alert.setHeaderText("Look, verifier les chapms");
            alert.setContentText("Ooops, ");

            alert.showAndWait();
            String title = "verifier les champs ";
            TrayNotification notif = new TrayNotification();
            AnimationType Type = AnimationType.FADE;
            notif.setAnimationType(Type);
            notif.setTitle(title);
            notif.setNotificationType(NotificationType.WARNING);
            notif.showAndDismiss(javafx.util.Duration.millis(3000));

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("veuillez vous vraiment supprimer ce formateur");
            alert.setContentText("vous étes sure ?");
            String title = "formateur supprimer avec succés";
            TrayNotification notif = new TrayNotification();
            AnimationType Type = AnimationType.POPUP;
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//        Typesport TypeSelect = (Typesport) tableauType.getSelectionModel().getSelectedItem();
                typeCRUD.supprimerFormateur(TypeSelect.getId());

                notif.setAnimationType(Type);
                notif.setTitle(title);
                notif.setNotificationType(NotificationType.SUCCESS);
                notif.showAndDismiss(javafx.util.Duration.millis(3000));

                Parent loader = FXMLLoader.load(getClass().getResource("aficherFormateur.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // AdminPageController apc= loader.getController();
                window.setTitle("Affichage page");
                window.setScene(scene);
                window.show();
            } else {
                Parent loader = FXMLLoader.load(getClass().getResource("aficherFormateur.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // AdminPageController apc= loader.getController();
                window.setTitle("Affichage page");
                window.setScene(scene);
                window.show();
            }

          
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        formateur TypeSelect = tableauformateur.getSelectionModel().getSelectedItem();

        if ((TypeSelect == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vérifier les champs");
            alert.setHeaderText("Look, verifier les chapms");
            alert.setContentText("Ooops, ");

            alert.showAndWait();
            String title = "verifier les champs ";
            TrayNotification notif = new TrayNotification();
            AnimationType Type = AnimationType.FADE;
            notif.setAnimationType(Type);
            notif.setTitle(title);
            notif.setNotificationType(NotificationType.WARNING);
            notif.showAndDismiss(javafx.util.Duration.millis(3000));

        } else {

            try {

                formateur t = new formateur();
                t.setId(TypeSelect.getId());
                t.setNom(TypeSelect.getNom());
                t.setPrenom(TypeSelect.getPrenom());
                t.setStatut(TypeSelect.getStatut());
                t.setTypecontrat(TypeSelect.getTypecontrat());
                t.setEmail(TypeSelect.getEmail());
                t.setPassword(TypeSelect.getPassword());
                donnéeformateur.setFormaeur(t);
                Parent loader = FXMLLoader.load(getClass().getResource("modifierFormateur.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // AdminPageController apc= loader.getController();
                window.setTitle("Affichage page");
                window.setScene(scene);
                window.show();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void research(ActionEvent event) {
    }

    @FXML
    private void searchRecord(KeyEvent event) {
         FilteredList<formateur> filterData = new FilteredList<>(listType, p -> true);
        research.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (String.valueOf(pers.getId()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                if (pers.getNom().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getPrenom().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getStatut().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getTypecontrat().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                if (pers.getEmail().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                
                if (String.valueOf(pers.getPassword()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                

                return false;
            });
            SortedList<formateur> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableauformateur.comparatorProperty());
            tableauformateur.setItems(sortedList);

        });
        
    }
public void mail() throws SQLException, Exception {
    String mailquery = "SELECT email FROM formateur WHERE id = "+maail.getText()+""; // remplacer 1 avec le user connecter
    Connection cnx = MyConnection.getinstance().getConnexion();
        Statement st;
       
        ResultSet mailing;
                               System.out.println(mailquery);
    
    
    PreparedStatement stm1l = cnx.prepareStatement(mailquery);
                            mailing = stm1l.executeQuery(mailquery);
                            
                            
                            
                            if (mailing.next()) {
                                String Email = mailing.getString("email");
                                 
                                System.out.println(mailquery);
                                System.out.println(Email); // debug
                            

                                mailxd.sendMail(Email);
                                //showevent2();
                            }
}
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("formateur.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void mail(ActionEvent event) throws Exception {
        mail();
        
    }
    
}
