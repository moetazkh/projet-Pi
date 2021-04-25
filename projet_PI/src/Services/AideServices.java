package Services;

import Entities.Aide;
import utils.DataSource;
import java.sql.*;

public class AideServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    private static final String INSERT_QUERY = "insert into aide (titre,description,adresse) values (?,?,?) ";
    private static final  String UPDATE_QUERY = "UPDATE AIDE set titre= ? ,description= ? , adresse=? where id= ? ";
    //private static final  String DELETE_QUERY = "DELETE FROM   aide WHERE id = ? ";
    public boolean add(Aide u) {
        try {
            PreparedStatement st = cnx.prepareStatement(INSERT_QUERY);
            st.setString(1, u.getTitre());
            st.setString(2, u.getDescription());
            st.setString(3, u.getAdresse());

            st.execute();
            System.out.println("added");
            return true;


        } catch (SQLException e) {
            System.out.println("not added");
            return false;

        }
    }
    public void updateAide(Aide u, int id) throws SQLException {
        try {

            PreparedStatement st = cnx.prepareStatement(UPDATE_QUERY);
            st.setString(1,u.getTitre());
            st.setString(2, u.getDescription());
            st.setString(3, u.getAdresse());
            //st.setFloat(5, u.getCategory_id());

            st.setInt(4, id);

            st.executeUpdate();
            System.out.println("produit modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void delete(Aide p){
        String requete2 = "DELETE FROM aide WHERE id= ?";

        try {

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("produit supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printSQLException(SQLException e) {
    }
}
