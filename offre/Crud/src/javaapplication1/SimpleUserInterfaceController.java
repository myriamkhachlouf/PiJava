/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javaapplication1.entities.users;
import javaapplication1.services.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.services.sendEmail2;
import javaapplication1.utils.MaConnexion;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SimpleUserInterfaceController implements Initializable {
    String query= null;
    int userId;
    Connection connection = null ;
    AuthController authController;  

    ResultSet rs = null;
    PreparedStatement pst = null;
    private TableView<users> table;
    private TableColumn<users, String> col_nom;
    private TableColumn<users, String> col_email;
    private TableColumn<users, String> col_telephone;
    private TableColumn<users, String> col_adresse;
    private TableColumn<users, String> col_domaine;

    ObservableList<users> oblist= FXCollections.observableArrayList();
    private Button DeleteUserBTN;
    @FXML
    private Label lab_nom;
    @FXML
    private Label lab_email;
    @FXML
    private Label lab_adresse;
    @FXML
    private Label lab_tel;
    @FXML
    private Label lab_domain;
    @FXML
    private Button save;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField tel;
    @FXML
    private TextField domain;
    

    private void LoadData1() throws Exception {
        oblist.clear();
        
        users connectedUser = new UsersService().getUserByID (MaConnexion.getInstance().connectedUserID);
        nom.setText(connectedUser.getNom());
        email.setText(connectedUser.getEmail());
        adresse.setText(connectedUser.getAdresse());
        domain.setText(connectedUser.getDomaine());
        tel.setText(connectedUser.getTelephone());
        
        /*
        ArrayList<users> p;
        p = new UsersService().getOneUserById(authController.getId());
       for (users u:p){
           oblist.add(u);
           table.setItems(oblist);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    */
        }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /*UsersService US=new UsersService();
            UserSession USE= new UserSession();
            String Email=USE.getuserEmail();
            System.out.print(Email);
            US.getUser(Email);*/
            // ResultSet rs =con.createStatement().executeQuery("Select * from users")
            LoadData1();
        } catch (Exception ex) {
            Logger.getLogger(SimpleUserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteUser(ActionEvent event){
        DeleteUserBTN.setOnAction(e->{
            new UsersService().Delete(table.getSelectionModel().getSelectedItem().getId());
        });
    }
    
    
    
    
    public void changeNomCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setNom(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    public void changeEmailCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setEmail(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    
    public void changeTelephoneCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setTelephone(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    public void changeAdresseCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setAdresse(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    public void changeDomaineCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setDomaine(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }


    private void print(MouseEvent event) {
        String path="";
        JFileChooser j=new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x;  
     //   x = j.showSaveDialog(this);
        
        
    }

    private void refreshTable(MouseEvent event) throws Exception {
            LoadData1();
    }

    @FXML
    private void updateMyProfile(MouseEvent event) {
        
        UsersService usersService = new UsersService();
        
        users currentUser = new users();
        
        try {
                currentUser.setId(MaConnexion.getInstance().connectedUserID);

                currentUser.setNom(nom.getText());
                currentUser.setAdresse(adresse.getText());
                currentUser.setDomaine(domain.getText());
                currentUser.setTelephone(tel.getText());
                currentUser.setEmail(email.getText());

                usersService.Save (currentUser);
                JOptionPane.showMessageDialog(null,"Your profile is saved.");
                sendEmail2 sendEmail = new sendEmail2();
                sendEmail.sendToken("mohamed.hachicha@esprit.tn", 01234567);

        } catch (Exception e) {
            System.out.println("javaapplication1.SimpleUserInterfaceController.updateMyProfile()");
        }
        
    }
   
    
}
