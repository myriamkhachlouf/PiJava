/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.donnéeevent;
import entities.evenement;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.evenementCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import service.formationCrud;
import entities.formation;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.donnéeformation;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Scale;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormationController implements Initializable {

    @FXML
    private TextField periode;
    @FXML
    private TextField capacite;
    @FXML
    private TextField obj;
    @FXML
    private TextField dure;
    @FXML
    private TextField ref;
    @FXML
    private TextField rating;
    @FXML
    private TableView<formation> tableauformation;
    @FXML
    private TableColumn<formation,Integer> afid;
    @FXML
    private TableColumn<formation, Integer> afref;
    @FXML
    private TableColumn<formation, Integer> afperiode;
    @FXML
    private TableColumn<formation, String> afobj;
    @FXML
    private TableColumn<formation, Integer> afdure;
    @FXML
    private TableColumn<formation, Integer> afcap;
    @FXML
    private TableColumn<formation, Integer> afrating;
    
    formationCrud formationCRUD = new formationCrud();
    ObservableList<formation> listeformationt;
    @FXML
    private TextField research;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try{
       listeformationt = (ObservableList<formation>) formationCRUD.listertype();
        afid.setCellValueFactory(new PropertyValueFactory<>("id"));
        afref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        afperiode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        afobj.setCellValueFactory(new PropertyValueFactory<>("objectif"));
        afdure.setCellValueFactory(new PropertyValueFactory<>("dure"));
        afcap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        afrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        
       
        tableauformation.setItems(listeformationt);
    }   catch (SQLException ex) {
            System.out.println("hhhhhh ");
        }
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        if ((ref.getText().equals(""))&&(periode.getText().equals(""))&&(obj.getText().equals(""))&&(dure.getText().equals(""))&&(capacite.getText().equals(""))&&(rating.getText().equals("")))
            {
               Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Vérifier les champs");
         alert.setHeaderText("Look, verifier les chapms");
         alert.setContentText("Ooops, ");

             alert.showAndWait();
             String title ="verifier les champs ";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.FADE;
              notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.WARNING);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 
            }else{
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("veuillez vous vraiment ajouter ce formation");
alert.setContentText("vous étes sure ?");
               String title ="formation ajouter avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK){
         int ppassword = Integer.parseInt(ref.getText());
         int pperiode = Integer.parseInt(periode.getText());
         String Nom = obj.getText();
        int pdure = Integer.parseInt(dure.getText());
        int pcapacite = Integer.parseInt(capacite.getText());
        int prating = Integer.parseInt(rating.getText());
       

        formation e = new formation(1,ppassword,pperiode,Nom,pdure,pcapacite,prating);
        formationCrud pcd = new formationCrud();
        pcd.addformation(e);
        notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 

        Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
        
         }else
        {Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
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
    private void supprimer(ActionEvent event) throws IOException {
        
         formation TypeSelect = (formation) tableauformation.getSelectionModel().getSelectedItem();
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
            alert.setHeaderText("veuillez vous vraiment supprimer cette formation");
            alert.setContentText("vous étes sure ?");
            String title = "formation supprimer avec succés";
            TrayNotification notif = new TrayNotification();
            AnimationType Type = AnimationType.POPUP;
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//        Typesport TypeSelect = (Typesport) tableauType.getSelectionModel().getSelectedItem();
                formationCRUD.supprimerFormation(TypeSelect.getId());

                notif.setAnimationType(Type);
                notif.setTitle(title);
                notif.setNotificationType(NotificationType.SUCCESS);
                notif.showAndDismiss(javafx.util.Duration.millis(3000));

                Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // AdminPageController apc= loader.getController();
                window.setTitle("Affichage page");
                window.setScene(scene);
                window.show();
            } else {
                Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
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
        
        
        formation newc = tableauformation.getSelectionModel().getSelectedItem();        
        if (newc == null) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erruer !");
            alert.setHeaderText("Aucun élément n'est sélectionné.");
            alert.setContentText(" Veuillez sélectionner la formation à modifier.");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("Voulez-vous vraiment modifier cette formation ?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK){
            try {
                
                formation c = new formation();
                
                c.setId(newc.getId());
                c.setReference(newc.getReference());
                c.setPeriode(newc.getPeriode());
                c.setObjectif(newc.getObjectif());
                c.setDure(newc.getDure());
            c.setCapacite(newc.getCapacite());
            c.setRating(newc.getRating());
                
                donnéeformation.setFormation(c);
                
                Parent loader = FXMLLoader.load(getClass().getResource("modifierformation.fxml"));
                //  Parent root  = loader.load();
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Modifier la formation");
                window.setScene(scene);
                window.show();
            }
             catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            }
            else{
                
                  try {
            Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les formations");
            window.setScene(scene);
            window.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
                    
        }

    }
    }

    @FXML
    private void pdf(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(tableauformation);
    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);
    }

    @FXML
    private void searchRecord(KeyEvent event) {
        
        FilteredList<formation> filterData = new FilteredList<>(listeformationt, p -> true);
        research.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

               
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (String.valueOf(pers.getId()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                
                if (String.valueOf(pers.getReference()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                
                if (String.valueOf(pers.getPeriode()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                
                if (pers.getObjectif().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (String.valueOf(pers.getDure()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
               if (String.valueOf(pers.getCapacite()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                if (String.valueOf(pers.getRating()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

               
                return false;
            });
            SortedList<formation> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableauformation.comparatorProperty());
            tableauformation.setItems(sortedList);

        });
        
    }

    @FXML
    private void statistique(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("statistiqueFormation.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("gestion.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
    }
    

    

    
    
    
}
