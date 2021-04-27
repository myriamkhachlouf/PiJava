/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.donnéeevent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import entities.evenement;
import entities.formateur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import service.evenementCrud;
import service.formateurCrud;
import tools.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EventController implements Initializable {

    @FXML
    private TextField enom;
    @FXML
    private TextField edesc;
    @FXML
    private TextField eemail;
    @FXML
    private TextField elogo;
    @FXML
    private DatePicker edate;
    @FXML
    private TableView<evenement> tableauevent;
    @FXML
    private TableColumn<evenement, Integer> afid;
    @FXML
    private TableColumn<evenement, String> afnom;
    @FXML
    private TableColumn<evenement, String> afdesc;
    @FXML
    private TableColumn<evenement, String> afemail;
    @FXML
    private TableColumn<evenement, String> aflogo;
    @FXML
    private TableColumn<evenement, String> afdate;
    evenementCrud eventCRUD = new evenementCrud();
    ObservableList<evenement> listevent;
    @FXML
    private TextField research;
    @FXML
    private ImageView photo_view;
    @FXML
    private TextField photo_p;
    private Image image;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
       listevent = (ObservableList<evenement>) eventCRUD.listerevent();
        afid.setCellValueFactory(new PropertyValueFactory<>("id"));
        afnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        afdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        afemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        aflogo.setCellValueFactory(new PropertyValueFactory<>("logo"));
        afdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
       
        tableauevent.setItems(listevent);
    }   catch (SQLException ex) {
            System.out.println("hhhhhh ");
        }
        
        
        // TODO
    }    

    @FXML
    private void ajoutevent(ActionEvent event) throws IOException {
if ((enom.getText().equals(""))&&(edesc.getText().equals(""))&&(eemail.getText().equals(""))&&(elogo.getText().equals("")))
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
alert.setHeaderText("veuillez vous vraiment ajouter ce evenement");
alert.setContentText("vous étes sure ?");
               String title ="evenemnt ajouter avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK){
         String Nom = enom.getText();
        String description = edesc.getText();
        String Email = eemail.getText();
        String  logo= elogo.getText();
        LocalDate date = edate.getValue();
        String Date_event = date.toString();

        evenement e = new evenement(1,Nom,description,Email,photo_p.getText().replace('\\', '/'),Date_event);
        evenementCrud pcd = new evenementCrud();
        pcd.addevenement(e);
        notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 

        Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
        
         }else
        {Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
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
    private void suppevent(ActionEvent event) throws IOException {
        evenement TypeSelect = (evenement) tableauevent.getSelectionModel().getSelectedItem();
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
            alert.setHeaderText("veuillez vous vraiment supprimer evenemen");
            alert.setContentText("vous étes sure ?");
            String title = "evenement supprimer avec succés";
            TrayNotification notif = new TrayNotification();
            AnimationType Type = AnimationType.POPUP;
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//        Typesport TypeSelect = (Typesport) tableauType.getSelectionModel().getSelectedItem();
                eventCRUD.supprimerEvenement(TypeSelect.getId());

                notif.setAnimationType(Type);
                notif.setTitle(title);
                notif.setNotificationType(NotificationType.SUCCESS);
                notif.showAndDismiss(javafx.util.Duration.millis(3000));

                Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
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
        
        evenement newc = tableauevent.getSelectionModel().getSelectedItem();        
        if (newc == null) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erruer !");
            alert.setHeaderText("Aucun élément n'est sélectionné.");
            alert.setContentText(" Veuillez sélectionner l'evenemenent à modifier.");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("Voulez-vous vraiment modifier cet evenement ?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK){
            try {
                
                evenement c = new evenement();
                
                c.setId(newc.getId());
                c.setNom(newc.getNom());
                c.setDescription(newc.getDescription());
                c.setEmail(newc.getEmail());
                c.setLogo(newc.getLogo());

                c.setDate(newc.getDate());
                
                donnéeevent.setEvent(c);
                
                Parent loader = FXMLLoader.load(getClass().getResource("modifierevent.fxml"));
                //  Parent root  = loader.load();
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Modifier le client");
                window.setScene(scene);
                window.show();
            }
             catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            }
            else{
                
                  try {
            Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les Clients");
            window.setScene(scene);
            window.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
                    
        }

    }
    }

    @FXML
    private void excel(ActionEvent event) {
        
         try {

            MyConnection cn = MyConnection.getinstance();
            Statement stmt1 = cn.getConnexion().createStatement();
            //Variable counter for keeping track of number of rows inserted.  
            int counter = 1;
            FileOutputStream fileOut = null;

            String sql = "Select * from evenement";

            //Creation of New Work Book in Excel and sheet.  
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            //Creating Headings in Excel sheet.  
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 1).setCellValue("id");//Row Name1  
            rowhead.createCell((short) 2).setCellValue("nom");//Row Name2  
            rowhead.createCell((short) 3).setCellValue("description");//Row Name3  
            rowhead.createCell((short) 4).setCellValue("email");//Row Name4
            rowhead.createCell((short) 5).setCellValue("logo");//Row Name5 
            rowhead.createCell((short) 6).setCellValue("date");//Row Name5 
           
            ResultSet rs = stmt1.executeQuery(sql);
            while (rs.next()) {
                //Insertion in corresponding row  
                HSSFRow row = sheet.createRow((int) counter);
                /* Activity, Username, TIME_OF_EVENT are row names  
          * corresponding to table  
          * in related Database. */
                CellStyle dateCellStyle = hwb.createCellStyle();
                CellStyle dateCellStyle1 = hwb.createCellStyle();
                CreationHelper createHelper = hwb.getCreationHelper();
                //Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//                dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

                row.createCell((short) 1).setCellValue(rs.getInt("id"));
                row.createCell((short) 2).setCellValue(rs.getString("nom"));
                row.createCell((short) 3).setCellValue(rs.getString("description"));
                row.createCell((short) 4).setCellValue(rs.getString("email"));
                row.createCell((short) 5).setCellValue(rs.getString("logo"));
                row.createCell((short) 6).setCellValue(rs.getInt("date"));
                

//                row.createCell((short) 3).setCellStyle(dateCellStyle);
//                row.createCell((short) 3).setCellValue(rs.getDate("date"));
//                Cell dateS = row.createCell((short) 4);
//                dateS.setCellValue(rs.getDate("dates"));
//                dateS.setCellStyle(dateCellStyle);
//
//
//                Cell dateE = row.createCell((short) 5);
//                dateE.setCellValue(rs.getDate("datee"));
//                dateE.setCellStyle(dateCellStyle1);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);

                counter++;
                try {
                    //For performing write to Excel file  
                    fileOut = new FileOutputStream("evenement.xls");
                    hwb.write(fileOut);
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Close all the parameters once writing to excel is compelte.  

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("All courses Has Been Exported in Excel Sheet");
            alert.showAndWait();
            rs.close();
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }

    @FXML
    private void searchRecord(KeyEvent event) {
        
         FilteredList<evenement> filterData = new FilteredList<>(listevent, p -> true);
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
                if (pers.getDescription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getEmail().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getLogo().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                
                if (pers.getDate().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }


                return false;
            });
            SortedList<evenement> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableauevent.comparatorProperty());
            tableauevent.setItems(sortedList);

        });
        
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

    @FXML
    private void uploadimage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (.JPG)", ".JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (.jpg)", ".jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (.PNG)", ".PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (.png)", ".png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_view.setImage(image);
                photo_p.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                
            }
    }

   
    }
    

