/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import edu.test.entities.Donnee;
import edu.test.entities.Recruteur;
import edu.test.services.ServiceRecruteur;
import utils.MaConnexion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RecruteurController implements Initializable {

    @FXML
    private JFXTextField searchTF;
    public ObservableList<Recruteur> data = FXCollections.observableArrayList();
    ServiceRecruteur sec = new ServiceRecruteur();

    @FXML
    private TableView<Recruteur> a;
    @FXML

    private TableColumn<Recruteur, String> nom;
    @FXML
    private TableColumn<Recruteur, String> prenom;
    @FXML
    private TableColumn<Recruteur, String> domaine;
    @FXML
    private TextField txtnnomp;
    @FXML
    private TextField txtnpren;
    @FXML
    private TextField txtdomaine;
    Recruteur CLmod = Donnee.getRecruteur();
    Recruteur newCL = new Recruteur();
    @FXML
    private TableColumn<Recruteur, Integer> nom1;
    @FXML
    private TextField txtid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();

        //initializeAll();
        a.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                edu.test.entities.Recruteur r = (edu.test.entities.Recruteur) a.getSelectionModel().getSelectedItem();
                if (a.getSelectionModel().getSelectedItem() != null) {
                    edu.test.entities.Recruteur e = (edu.test.entities.Recruteur) a.getSelectionModel().getSelectedItem();
                    System.out.println();
                    txtid.setText(String.valueOf(e.getId()));
                    txtnnomp.setText(String.valueOf(e.getNom()));
                    txtnpren.setText(String.valueOf(e.getPrenom()));
                    txtdomaine.setText(String.valueOf(e.getDomaine()));
                    //tfdate.setValue(e.getDate().toLocalDate());

                }
            }

        });
    }

    public void Afficher() {
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM recruteur";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Recruteur recruteur;
            while (rs.next()) {
                recruteur = new Recruteur(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("domaine"));
                data.add(recruteur);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        nom1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));

        a.setItems(data);

    }

    public void Refresh() {
        data.removeAll(data);
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM recruteur";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Recruteur recruteur;
            while (rs.next()) {
                recruteur = new Recruteur(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("domaine")); //rs.getDate
                data.add(recruteur);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        nom1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));

        a.setItems(data);;
    }

    /* public void initializeAll(){
        
        txtnnomp.setText(CLmod.getNom());
        txtnpren.setText(CLmod.getPrenom());
        txtdomaine.setText(CLmod.getDomaine());
     
        
        
    } */
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
    private void filter(KeyEvent event) {
        data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(sec.readAll().stream().filter((art)
                -> art.getNom().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getPrenom().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getDomaine().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));

    }

    @FXML
    private void ajouteron(ActionEvent event) {
        String nom = txtnnomp.getText();
        String prenom = txtnpren.getText();
        String domaine = txtdomaine.getText();

        Recruteur r = new Recruteur(nom, prenom, domaine);
        sec.ajouterpp(r);
        Refresh();
        txtnnomp.clear();
        txtnpren.clear();
        txtdomaine.clear();

    }

    @FXML
    private void changenomCellEvent(TableColumn.CellEditEvent edittedCell) {
        Recruteur produitslected = a.getSelectionModel().getSelectedItem();
        produitslected.setNom(edittedCell.getNewValue().toString());
    }

    @FXML
    private void changeprenomCellEvent(TableColumn.CellEditEvent edittedCell) {
        Recruteur produitslected = a.getSelectionModel().getSelectedItem();
        produitslected.setPrenom(edittedCell.getNewValue().toString());
    }

    @FXML
    private void changedomaineCellEvent(TableColumn.CellEditEvent edittedCell) {
        Recruteur produitslected = a.getSelectionModel().getSelectedItem();
        produitslected.setDomaine(edittedCell.getNewValue().toString());
    }

    @FXML
    public void modifier(ActionEvent event) throws SQLException {

        ServiceRecruteur sr = new ServiceRecruteur();
        Recruteur t = new Recruteur();
        
        int id;
        id = Integer.parseInt(txtid.getText());
        t.setNom(txtnnomp.getText());
        t.setPrenom(txtnpren.getText());
        t.setDomaine(txtdomaine.getText());
        
        
        
        if (txtnnomp.getText().isEmpty() || txtnpren.getText().isEmpty() || txtdomaine.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerte !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le formulaire");
            alert.showAndWait();
        } else {
            sr.updateRecruteur(id, t);
        }
        Refresh();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if (a.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("supression produit");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous voulez supprimer ce Recruteur ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Recruteur r = a.getSelectionModel().getSelectedItem();
                sec.supprimer(r);
                data.clear();
                data.addAll(sec.readAll());
                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression Recruteur");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("Recruteur supprime!");
                succDeleteBookAlert.showAndWait();
                Refresh();
                txtnnomp.clear();
                txtnpren.clear();
                txtdomaine.clear();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }

        }
    }

    @FXML
    private void pdff(ActionEvent event) {
        try {
            Document document = new Document() {
            };
            PdfWriter.getInstance(document, new FileOutputStream("D:\\wamp64\\www\\PiJava\\offre\\Crud\\Recruteur.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(2);
            table.addCell("nom");
            table.addCell("prenom");
            table.addCell("domaine");

            com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des Recruteurs");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/khedmtech", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `Recruteur`");
            while (rs.next()) {
                table.addCell(rs.getString("nom"));
                table.addCell(rs.getString("prenom"));
                table.addCell(rs.getString("domaine"));

            }
            document.add(table);
            JOptionPane.showMessageDialog(
                    null, " données exportées en pdf avec succés ");
            document.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void statis(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BarChartEvent.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

//    private void lister(ActionEvent event) {
//
//        Recruteur newc = a.getSelectionModel().getSelectedItem();
//        if (newc == null) {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Erruer !");
//            alert.setHeaderText("Aucun élément n'est sélectionné.");
//            alert.setContentText(" Veuillez sélectionner l'evenemenent à modifier.");
//
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setTitle("Modification");
//            alert.setHeaderText("Voulez-vous vraiment modifier cet evenement ?");
//            alert.setContentText("Are you ok with this?");
//
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//                try {
//
//                    Recruteur c = new Recruteur();
//
//                    c.setId(newc.getId());
//                    c.setNom(newc.getNom());
//                    c.setPrenom(newc.getPrenom());
//                    c.setDomaine(newc.getDomaine());
//                    Donnee.setRecruteur(c);
//
//                    Parent loader = FXMLLoader.load(getClass().getResource("Recruteur.fxml"));
//                    //  Parent root  = loader.load();
//                    Scene scene = new Scene(loader);
//                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    window.setTitle("Modifier le recruteur");
//                    window.setScene(scene);
//                    window.show();
//                } catch (IOException ex) {
//                    System.err.println(ex.getMessage());
//                }
//            } else {
//
//                loadUi("Recruteur");
//
//            }
//
//        }
//    }
}
