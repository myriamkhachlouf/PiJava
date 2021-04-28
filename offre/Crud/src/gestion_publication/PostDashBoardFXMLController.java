/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_publication;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Publication;
import Service.PublicationService;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
//import javafx.scene.chart.XYChart.Data;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class PostDashBoardFXMLController implements Initializable {

    @FXML
    private Label PostNumber;
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
    @FXML
    private Label PosterNumber;
     ObservableList<Publication> PostList= FXCollections.observableArrayList();
    @FXML
    private BarChart<String,Integer> PostsBarChart;
    @FXML
    private NumberAxis PostsNumberBar;
    @FXML
    private CategoryAxis MonthsIdBar;
    @FXML
    private PieChart PostsPerEntreprisePie;
    @FXML
    private Label valueNumber;
    public int sum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    
    private void loadData(){
       //////////////barChart
       //in thi function exceptionally i will use the id attribute to set count
       ArrayList<Publication> publications=new ArrayList<Publication>();
       publications=new PublicationService().getNumberPosterPerMonth();
       XYChart.Series<String,Integer> series = new XYChart.Series<>();
       series.setName("Posts Per Month");
       for (Publication p:publications)
       {
           Date date = Calendar.getInstance().getTime();  
DateFormat dateFormat = new SimpleDateFormat("MMMM");  
String strDate = dateFormat.format(p.getCreatedAt());  
           
          series.getData().add(new XYChart.Data<>(strDate,p.getTotalPostForChart()));
       }
       PostsBarChart.getData().add(series);
       //////////////
       //////////////////////////pieChart
       ArrayList<Publication> publicationPie=new ArrayList<Publication>();
       publicationPie=new PublicationService().getNumberPostsPerEntreprise();
       
       ObservableList<Data> list=FXCollections.observableArrayList();
       for (Publication p:publicationPie)
       {
           System.out.println(p.getNomEntrepriseForChart());
           System.out.println(sum);
           System.out.println(p.getTotalEntrpriseForChart());
        list.add(new PieChart.Data(p.getNomEntrepriseForChart(),p.getTotalEntrpriseForChart()));   
       sum+=p.getTotalEntrpriseForChart();
       }
       PostsPerEntreprisePie.setData(list);
       for (final PieChart.Data data: PostsPerEntreprisePie.getData()){
           data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event){
               valueNumber.setText(String.valueOf((data.getPieValue()/sum)*100+"%"));
           }
       });
       }
       /////////////////////////
        PostNumber.setText(Integer.toString(new PublicationService().getAllPostNumber())+" New");
        PosterNumber.setText(Integer.toString(new PublicationService().getAllPostersNumber())+" New");
         ArrayList<Publication> p=new PublicationService().getTopFive();
          for (Publication pub:p){
           PostList.add(pub);
           PostsTable.setItems(PostList);
        postidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title") );
        createdatcol.setCellValueFactory(new PropertyValueFactory<>("createdAt") );
        updatedatcol.setCellValueFactory(new PropertyValueFactory<>("updatedAt") );
        viewscol.setCellValueFactory(new PropertyValueFactory<>("views") );
    }
    
}
}
