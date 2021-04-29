/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.gui;
import Entities.Candidature;
import Service.ServiceCandidature;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import edu.test.entities.entretien; 
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.test.entities.Recruteur;
import edu.test.services.ServiceEntretien;
import utils.MaConnexion;
import Utils.JavaMail;
import com.jfoenix.controls.JFXComboBox;
import edu.test.services.ServiceRecruteur;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author fac
 */
public class EntretienController implements Initializable {
  public ObservableList<entretien> data = FXCollections.observableArrayList();
  ObservableList<entretien> data2;
    ServiceEntretien sec = new ServiceEntretien();
    @FXML
    private JFXComboBox<Candidature> txtidc;
    @FXML
    private JFXTextField searchTF;
    @FXML
    private TableView<entretien> e;
    @FXML
    private TableColumn<entretien, Integer> EIdc;
    @FXML
    private TableColumn<entretien, Integer> EIdr;
    @FXML
    private TableColumn<entretien, String> Edate;
    @FXML
    private TableColumn<entretien, String> Elieu;
    @FXML
    private TableColumn<entretien, Integer> Econfirmation;
    @FXML
    private TableColumn<entretien, Integer> Eetat;
    @FXML
    private TableColumn<entretien, Integer> id;
    @FXML
    private JFXComboBox<Recruteur> txtidr;
    @FXML
    private TextField txtlieu;
    @FXML
    private JFXDatePicker txtdate;
    @FXML
    private TextField txtconfirmation;
    @FXML
    private TextField txtetat;
    @FXML
    private Button excel;
    @FXML
    private Button mail;

    /**
     * Initializes the controller class
     */
    ServiceRecruteur DC= new ServiceRecruteur();
    ServiceCandidature AC= new ServiceCandidature();
    private void afficherCombo() {
      ObservableList<Candidature> data1 = FXCollections.observableArrayList(AC.afficherCandidature());
        txtidc.setItems(data1);
         
    }
    private void afficherCombo1() {
      ObservableList<Recruteur> data9 = FXCollections.observableArrayList(DC.readAll());
        txtidr.setItems(data9);
         
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {  
    afficherCombo();
        Afficher();
        afficherCombo1();
        

        e.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                edu.test.entities.entretien r = (edu.test.entities.entretien) e.getSelectionModel().getSelectedItem();
              if (e.getSelectionModel().getSelectedItem() != null) {
                    edu.test.entities.entretien entretien = (edu.test.entities.entretien) e.getSelectionModel().getSelectedItem();
                    
                   
ObservableList<Candidature> data1 = FXCollections.observableArrayList(AC.afficherCandidature());    
ObservableList<Recruteur> data9 = FXCollections.observableArrayList(DC.readAll());
                    System.out.println();
                    txtidc.setItems(data1);
                    txtidr.setItems(data9);
                    txtdate.setValue(entretien.getDate().toLocalDate());
                    txtlieu.setText(String.valueOf(entretien.getLieu()));
                    txtconfirmation.setText(Integer.toString(entretien.getConfirmation()));
                    txtetat.setText(Integer.toString(entretien.getEtat()));
                }
            }

        });
        
        
    }

    
      public void Afficher() {
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM entretien";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            entretien entretien;
            while (rs.next()) {
                entretien = new entretien(rs.getInt("Id"),rs.getInt("cadidature_id"),rs.getInt("recruteur_id"), rs.getDate("date"), rs.getString("lieu"), rs.getInt("confirmation"), rs.getInt("etat"));
                data.add(entretien);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        EIdc.setCellValueFactory(new PropertyValueFactory<>("cadidature_id"));
        EIdr.setCellValueFactory(new PropertyValueFactory<>("recruteur_id"));
        Edate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Elieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        Econfirmation.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
        Eetat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        e.setItems(data);

    }

    public void Refresh() {
        data.removeAll(data);
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM entretien";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            entretien entretien;
            while (rs.next()) {
               entretien = new entretien(rs.getInt("Id"),rs.getInt("cadidature_id"),rs.getInt("recruteur_id"), rs.getDate("date"), rs.getString("lieu"), rs.getInt("confirmation"), rs.getInt("etat"));
                data.add(entretien);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        EIdc.setCellValueFactory(new PropertyValueFactory<>("cadidature_id"));
        EIdr.setCellValueFactory(new PropertyValueFactory<>("recruteur_id"));
        Edate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Elieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        Econfirmation.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
        Eetat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        e.setItems(data);
    }
    
    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // BorderPane.setCenter(root); 
    }

    @FXML
    private void ajouteron(ActionEvent event) throws SQLException,IOException {
         
         ServiceEntretien aa = new ServiceEntretien();
        Scanner sc = new Scanner(System.in);
        Connection cnx = MaConnexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        
        
         entretien t = new entretien();
        afficherCombo();
        afficherCombo1();
            t.setIdc((txtidc.getValue().getId()));
            t.setIdr(txtidr.getValue().getId());
            t.setDate(Date.valueOf(txtdate.getValue()));
            t.setLieu(txtlieu.getText());
            t.setConfirmation(Integer.parseInt(txtconfirmation.getText()));
            t.setEtat(Integer.parseInt(txtetat.getText()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();
           // Date now = new Date();
            LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
            
            if(
                     txtlieu.getText().isEmpty() || txtetat.getText().isEmpty() || txtconfirmation.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
            
               else if (
                        txtdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
               
               else {
                    sec.ajouterpp(t);
                    Refresh();
                    txtidc.getSelectionModel().clearSelection();
                    txtidr.getSelectionModel().clearSelection();
                    txtdate.getEditor().clear();
                    txtlieu.clear();
                    txtconfirmation.clear();
                    txtetat.clear();
                   
               }
      
         
        
 }
    
    
    @FXML
    public void modifier(ActionEvent event) throws SQLException {
        data2 = e.getSelectionModel().getSelectedItems();
        Connection cnx = MaConnexion.getInstance().getConnection();
        
        ServiceEntretien sr = new ServiceEntretien();
        entretien t = new entretien();
        LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
        int id;
        id = data.get(0).getId();
        System.out.println(id);
        afficherCombo1();
        afficherCombo();
        t.setIdc(txtidc.getValue().getId());
        t.setIdr(txtidr.getValue().getId());
        t.setLieu(txtlieu.getText());
        t.setDate(Date.valueOf(txtdate.getValue()));
        t.setConfirmation(Integer.parseInt(txtconfirmation.getText()));
        t.setEtat(Integer.parseInt(txtetat.getText()));
        
        
        
        if(
                    txtlieu.getText().isEmpty() || txtetat.getText().isEmpty() || txtconfirmation.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
            
               else if (
                        txtdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               } else {
            sr.updateEntretien(id, t);
                    txtidc.getSelectionModel().clearSelection();
                    txtidr.getSelectionModel().clearSelection();
                    txtlieu.clear();
                    txtdate.getEditor().clear();
                    txtconfirmation.clear();
                    txtetat.clear();
        }
        Refresh();

    }
    
    
    

    @FXML
    private void supprimer(ActionEvent event) {
        data2 = e.getSelectionModel().getSelectedItems();
        Connection cnx = MaConnexion.getInstance().getConnection();
        int id;
        id = data2.get(0).getId();
        System.out.println(id);
        /*for (entretien e : data2) {
            System.err.println(e.toString());
        }*/ // debug

        
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("supression produit");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous voulez supprimer ce Recruteur ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                try {
                    String query = "delete from entretien where id = ?";
                    PreparedStatement preparedStmt = cnx.prepareStatement(query);
                    preparedStmt.setInt(1, id);
                    preparedStmt.execute();
                       
                    //Alert Delete Blog :
                    Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                    succDeleteBookAlert.setTitle("suppression Recruteur");
                    succDeleteBookAlert.setHeaderText("Results:");
                    succDeleteBookAlert.setContentText("Recruteur supprime!");
                    succDeleteBookAlert.showAndWait();

                    txtidc.getSelectionModel().clearSelection();
                    txtidr.getSelectionModel().clearSelection();
                    txtlieu.clear();
                    txtconfirmation.clear();
                    txtetat.clear();
                }
                catch (SQLException ex) {
            Logger.getLogger(EntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
             Refresh();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            } 
    }
    
    
    


    @FXML
    private void filter(KeyEvent event) {
         data.clear();
        data.addAll(sec.readAll().stream().filter((entretien art)
                -> art.getLieu().toLowerCase().contains(searchTF.getText().toLowerCase()) 
        ).collect(Collectors.toList()));

    }

    @FXML
    private void excel(ActionEvent event) {
        
        try {

            data2 = e.getSelectionModel().getSelectedItems();
        Connection cnx = MaConnexion.getInstance().getConnection();
            Statement stmt1 = cnx.createStatement();
            //Variable counter for keeping track of number of rows inserted.  
            int counter = 1;
            FileOutputStream fileOut = null;

            String sql = "Select * from entretien";

            //Creation of New Work Book in Excel and sheet.  
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            //Creating Headings in Excel sheet.  
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 1).setCellValue("Id");//Row Name1  
            rowhead.createCell((short) 2).setCellValue("cadidature_id");//Row Name2  
            rowhead.createCell((short) 3).setCellValue("recruteur_id");//Row Name3  
            rowhead.createCell((short) 4).setCellValue("date");//Row Name4
            rowhead.createCell((short) 5).setCellValue("lieu");//Row Name5 
            rowhead.createCell((short) 6).setCellValue("confirmation");//Row Name5 
            rowhead.createCell((short) 7).setCellValue("etat");//Row Name5 
           
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

                row.createCell((short) 1).setCellValue(rs.getInt("Id"));
                row.createCell((short) 2).setCellValue(rs.getInt("cadidature_id"));
                row.createCell((short) 3).setCellValue(rs.getInt("recruteur_id"));
                row.createCell((short) 4).setCellValue(rs.getString("date"));
                row.createCell((short) 5).setCellValue(rs.getString("lieu"));
                row.createCell((short) 6).setCellValue(rs.getInt("confirmation"));
                row.createCell((short) 7).setCellValue(rs.getInt("etat"));
                

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
                    fileOut = new FileOutputStream("entretien.xls");
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
        Refresh();
                   
    }
    
    
    
    @FXML
    public void notifier(ActionEvent event) throws SQLException, Exception {
    String mailquery = "SELECT email FROM users WHERE id = ( SELECT candidat_id From candidature where id = "+txtidc.getValue().getId() +")"; // remplacer 1 avec le user connecter
    Connection cnx = MaConnexion.getInstance().getConnection();
        Statement st;
       
        ResultSet mailing;
                               System.out.println(mailquery);
    
    
    PreparedStatement stm1l = cnx.prepareStatement(mailquery);
                            mailing = stm1l.executeQuery(mailquery);
                            
                            
                            
                            if (mailing.next()) {
                                String Email = mailing.getString("email");
                                 
                                System.out.println(mailquery);
                                System.out.println(Email); // debug
                            

                                JavaMail.sendMail(Email);
                                //showevent2();
                            }
                            
            JOptionPane.showMessageDialog(
                    null, " Mail envoyé avec succés ");
           
}
    

    
}
