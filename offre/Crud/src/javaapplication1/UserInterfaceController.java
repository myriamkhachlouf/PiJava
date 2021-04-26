/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javaapplication1.entities.UserSession;
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
import java.text.MessageFormat;
import java.util.ArrayList;
import javaapplication1.utils.MaConnexion;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javaapplication1.services.ChartLib;
import javaapplication1.services.PDFLib;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class UserInterfaceController implements Initializable {
    private int UserId;
    String query= null;
    int userId;
    private boolean update;
    Connection connection = null ;
    AuthController authController;  

    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TableView<users> table;
    @FXML
    private TableColumn<users, Integer> col_id;
    @FXML
    private TableColumn<users, String> col_nom;
    @FXML
    private TableColumn<users, String> col_email;
    @FXML
    private TableColumn<users, String> col_telephone;
    @FXML
    private TableColumn<users, String> col_adresse;
    @FXML
    private TableColumn<users, String> col_domaine;
    @FXML
    private TableColumn<users, String> col_role;

    ObservableList<users> oblist= FXCollections.observableArrayList();
    @FXML
    private Button DeleteUserBTN;
    @FXML
    private ImageView printBtn;
    @FXML
    private Label username;
    @FXML
    private TextField filterField;
    @FXML
    private Button statisticsBtn;

    private void LoadData() {
        
        users connectedUser = new UsersService().getUserByID (MaConnexion.getInstance().connectedUserID);
        username.setText(connectedUser.getNom());
        oblist.clear(); 
         ArrayList<users> p=new UsersService().getAll();
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
    }
    /**
     * Initializes the controller class.
     */
    
   
    private void LoadData1() {
        oblist.clear();
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        /*UsersService US=new UsersService();
        UserSession USE= new UserSession();
        String Email=USE.getuserEmail();
        System.out.print(Email);
        US.getUser(Email);*/
       // ResultSet rs =con.createStatement().executeQuery("Select * from users")
       FilteredList<users> filteredData = new FilteredList<>(oblist, p -> true);
       LoadData();
       table.setEditable(true);
       col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
       col_email.setCellFactory(TextFieldTableCell.forTableColumn());
       col_telephone.setCellFactory(TextFieldTableCell.forTableColumn());
       col_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
       col_domaine.setCellFactory(TextFieldTableCell.forTableColumn());
       // 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(users -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (users.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (users.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (users.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (users.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (users.getDomaine().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (users.getRoles().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                
                                
                                
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<users> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
	}
    
    @FXML
    public void DeleteUser(ActionEvent event){
        DeleteUserBTN.setOnAction(e->{
            new UsersService().Delete(table.getSelectionModel().getSelectedItem().getId());
        });
    }
    
    
    public void setID(int id){
        this.UserId=id;
    }
    
    @FXML
    public void changeNomCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setNom(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    @FXML
    public void changeEmailCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setEmail(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    
    @FXML
    public void changeTelephoneCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setTelephone(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    @FXML
    public void changeAdresseCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setAdresse(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }
    
    @FXML
    public void changeDomaineCellEvent(CellEditEvent edittedCell){
        users userSelected = table.getSelectionModel().getSelectedItem();
        users userdb = new UsersService().getUserByID(userSelected.getId());
        userdb.setDomaine(edittedCell.getNewValue().toString());
        
        //
        Integer ret = new UsersService().Save(userdb);
        System.out.println("javaapplication1.UserInterfaceController.changeNomCellEvent() "+ ret );
    }


    @FXML
    private void print(MouseEvent event) {
        try {
             
      File htmlf = File.createTempFile("userlist", ".html", new File("c:\\temp"));
      
       // deletes file when the virtual machine terminate
       htmlf.deleteOnExit();
         
      System.out.println("File created: " + htmlf.getName());
      
      FileWriter myWriter = new FileWriter(htmlf.getAbsolutePath());
      
      myWriter.write("<h1 style=\"text-align:center\"><strong>Liste des utilisateurs enregistr&eacute;s</strong></h1>");
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");  
       LocalDateTime now = LocalDateTime.now();   
      myWriter.write("<h2>Date : ");
      myWriter.write(dtf.format(now));
      myWriter.write("</h2>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<table class=\"editorDemoTable\" style=\"width:100%\">\n" +
"	<thead>\n" +
"		<tr>\n" +
"			<th style=\"width: 30px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">ID</span></th>\n" +
"			<th style=\"width: 120px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Nom</span></th>\n" +
"			<th style=\"width: 197px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Email</span></th>\n" +
"			<th style=\"width: 203px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Telephone</span></th>\n" +
"			<th style=\"width: 227px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Adresse</span></th>\n" +
"			<th style=\"width: 241px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Domaine</span></th>\n" +
"			<th style=\"width: 125px; background-color: rgb(51, 51, 153);\"><span style=\"color:#ffffff\">Role</span></th>\n" +
"		</tr>\n" +
"	</thead>\n" +
"	<tbody>");
      
      //getall
      ArrayList<users> p=new UsersService().getAll();
      for (users u:p){
          myWriter.write ("<tr>\n" +
"			<td style=\"width:30px\">");
          myWriter.write (String.valueOf(u.getId()));
          myWriter.write ("</td>\n" +
"			<td style=\"width:120px\">");
          myWriter.write (u.getNom());
          myWriter.write ("</td>\n" +
"			<td style=\"width:197px\">");
          myWriter.write (u.getEmail());
          myWriter.write ("</td>\n" +
"			<td style=\"width:203px\">");
          myWriter.write (u.getTelephone());
          myWriter.write ("</td>\n" +
"			<td style=\"width:227px\">");
          myWriter.write (u.getAdresse());
          myWriter.write ("</td>\n" +
"			<td style=\"width:241px\">");
          myWriter.write (u.getDomaine());
          myWriter.write ("</td>\n" +
"			<td style=\"width:125px\">");
          myWriter.write (u.getRoles());
          myWriter.write ("</td>\n" +
"		</tr>");
      }        
      
      myWriter.write ("</tbody>\n" +
"</table>\n" +
"\n" +
"<p><strong>&nbsp;</strong></p>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<p><strong>&nbsp;</strong></p>");
      
      
      myWriter.close();

      File pdff = File.createTempFile("userlist", ".pdf", new File("c:\\temp"));
      //samplePDF(outputFileName);
      PDFLib pdflib = new PDFLib();
      
      pdflib.htmltopdf(htmlf.getAbsolutePath(), pdff.getAbsolutePath());
      Application a = new Application() {

                    @Override
                    public void start(Stage stage)
                    {
                    }
                };
      HostServices hostServices = a.getHostServices();
      hostServices.showDocument(pdff.getAbsolutePath());

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }

    @FXML
    private void refreshTable(MouseEvent event) {
            LoadData();
    }

    @FXML
    private void showStatistics(ActionEvent event) throws IOException {
            
            Stage parentStage = (Stage) statisticsBtn.getScene().getWindow();
            // do what you have to do
            Stage dialog = new Stage();

            // populate dialog with controls.
            

            dialog.initOwner(parentStage);
            dialog.initModality(Modality.APPLICATION_MODAL); 

            ChartLib chartLib = new ChartLib();
            chartLib.demo(dialog);
    }

   
    
}
