package Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AideStatsService {
    private Connection cnx = DataSource.getInstance().getCnx();

    private static final String ST_QUERY = "select adresse, count(*) from aide group by adresse ";

    public ObservableList<PieChart.Data> aideStats(){
        ObservableList<PieChart.Data> myList = FXCollections.observableArrayList();
        try {
            ResultSet res = cnx.createStatement().executeQuery(ST_QUERY);
            while (res.next()) {
                PieChart.Data c = new PieChart.Data(res.getString("adresse"),res.getInt("count(*)"));
                myList.add(c);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(myList);

        return myList;
    }
}








