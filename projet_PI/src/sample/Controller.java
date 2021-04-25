package sample;
import Entities.Aide;
import Services.AideServices;
import Services.AideStatsService;
import Services.MailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.DataSource;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private TextField tftitre;

    @FXML
    private Button btnupdate1;

    @FXML
    private TextField tfid1;

    @FXML
    private TextField tftitre1;

    @FXML
    private TextField tfdescription1;

    @FXML
    private TextField tfadresse1;

    @FXML
    private ComboBox<?> cbcategorie1;


    @FXML
    private AnchorPane updatePane;


    @FXML
    private AnchorPane managePane;


    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfadresse;

    @FXML
    private ComboBox<?> cbcategorie;

    @FXML
    private TableView<Aide> tvaide;

    @FXML
    private TableColumn<Aide, String> coltitre;

    @FXML
    private TableColumn<Aide, String> coldescription;

    @FXML
    private TableColumn<Aide, String> coladresse;

    @FXML
    private TableColumn<Aide, Integer> colcategorie;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnsupprimer;

    @FXML
    private AnchorPane statsPane;



    @FXML
    private Button statsbackBtn;

    @FXML
    private Button statsBtn;

    @FXML
    private PieChart aidePie;



    public void initialize(URL url, ResourceBundle rb) {
        showAide();
    }
    @FXML
    void statsClicked(ActionEvent event) {
        managePane.setVisible(false);
        statsPane.setVisible(true);
        AideStatsService x = new AideStatsService();
        aidePie.setTitle("AIDE STATS");
        aidePie.setLabelLineLength(15);
        aidePie.setLabelsVisible(false);
        aidePie.setLegendSide(Side.BOTTOM);
        aidePie.setStartAngle(0);
        aidePie.setClockwise(false);
        aidePie.setData(x.aideStats());
    }


    @FXML
    void backBtn(ActionEvent event) {

        managePane.setVisible(true);
        statsPane.setVisible(false);
    }

    public ObservableList<Aide> getAideList() {
        ObservableList<Aide> AideList = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();

        String query = "SELECT * FROM aide";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Aide aide;
            while (rs.next()) {
                aide = new Aide();
                aide.setId(rs.getInt("id"));
                aide.setTitre(rs.getString("titre"));
                aide.setDescription(rs.getString("description"));
                aide.setAdresse(rs.getString("adresse"));
                aide.setCategory_id(rs.getInt("category_id"));
                AideList.add(aide);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AideList;
    }
    public void showAide(){
        ObservableList<Aide> List = getAideList();
        coltitre.setCellValueFactory(new PropertyValueFactory<Aide, String>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Aide, String>("description"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Aide, String>("adresse"));
        /*cbcategorie.setSelectionModel().select(Integer.parseInt("category_id"));*/

        tvaide.setItems(List);
    }


    private void insertAide() throws SQLException {
        String titre = tftitre.getText();
        String description = tfdescription.getText();
        String adresse = tfadresse.getText();
        Aide u = new Aide();
        u.setTitre(tftitre.getText());
        u.setDescription(tfdescription.getText());
        u.setAdresse(tfadresse.getText());
        AideServices t = new AideServices();
        t.add(u);
        showAide();
        String from = "ihebmbarki420@gmail.com";
        String pass = "@P4r4lys!s09gm";
        String[] to = { "iheb.mbarki@esprit.tn" }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "NEW AIDE DONE ! TITRE : "+titre.toUpperCase()+" DESCRIPTION : "+description.toUpperCase()+" ADRESSE : "+adresse.toUpperCase();
        MailService serv = new MailService();
        serv.sendFromGMail(from, pass, to, subject, body);

    }




@FXML
    public void handleButtonActionAjout(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btnajouter) {
            insertAide();
        } else {

        }
    }

    public void update1(){
        AideServices servicep= new AideServices();
        //nteger id, Integer id_categorie, String titre, String description, String type, float prix_unitaire
        try {
            Aide a = new Aide();
            a.setTitre(tftitre1.getText());
            a.setDescription(tfdescription1.getText());
            a.setAdresse(tfadresse1.getText());

            servicep.updateAide(a,Integer.parseInt(tfid1.getText()));
            System.out.println("UPDATED");
            String from = "ihebmbarki420@gmail.com";
            String pass = "@P4r4lys!s09gm";
            String[] to = { "iheb.mbarki@esprit.tn" }; // list of recipient email addresses
            String subject = "Java send mail example";
            String body = "AIDE WITH ID "+Integer.parseInt(tfid1.getText())+" HAS BEEN MODIFIED";
            MailService serv = new MailService();
            serv.sendFromGMail(from, pass, to, subject, body);
            tfid1.clear();
            managePane.setVisible(true);
            updatePane.setVisible(false);
            showAide();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void handleButtonActionmodifier(ActionEvent event) throws IOException {
        Aide aideselected = (Aide) tvaide.getSelectionModel().getSelectedItem();
        System.out.print(aideselected.getId());
        managePane.setVisible(false);
        updatePane.setVisible(true);
        tfid1.setText(String.valueOf(aideselected.getId()));
        tfid1.setDisable(true);
        tftitre1.setText(aideselected.getTitre());
        tfdescription1.setText(aideselected.getDescription());
        tfadresse1.setText(aideselected.getAdresse());


       /* updateController.setItems("update1",aideselected.getId(),aideselected.getTitre(),
                aideselected.getDescription(),aideselected.getAdresse());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(updateController.class.getResource("update.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("Aide");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );

        stage.show();*/


    }
    @FXML
    void supp(ActionEvent event) {
        Aide produitselected = (Aide) tvaide.getSelectionModel().getSelectedItem();

        AideServices PService= new AideServices();
        PService.delete(produitselected);
        ObservableList<Aide> list = FXCollections.observableArrayList();
        //col.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("category_id"));


        //list.addAll(PService.(1));
        tvaide.setItems(list);
        showAide();

    }
}

