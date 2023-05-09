package Controlers;

import Entities.Formation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlFormation
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlFormation()
    {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Formation> getFormationsByActivite(int idActivite)
    {
ArrayList<Formation> lesFormations = new ArrayList<>();
try{
ps = cnx.prepareStatement("SELECT formation.Code, formation.Intitule\n" +
        "FROM formation\n" +
        "WHERE formation.numeroActivite =?");
ps.setInt(1,idActivite);
rs = ps.executeQuery();
while (rs.next()){
    Formation uneFormation = new Formation(rs.getString("Code"),rs.getString("Intitule"));
lesFormations.add(uneFormation);
}
rs.close();
ps.close();
}
    catch(SQLException ex){

}
return lesFormations;

    }

    public ArrayList<Formation> getAllByActivite(){
        ArrayList<Formation> lesFormations = new ArrayList<>();
        try{
            ps = cnx.prepareStatement("SELECT formation.code, formation.intitule\n" +
                    "FROM formation ");

            rs = ps.executeQuery();
            while (rs.next()){
                Formation uneFormation = new Formation(rs.getString(1),rs.getString(2));
                lesFormations.add(uneFormation);
            }
            rs.close();
            ps.close();
        }
        catch(SQLException ex){

        }
        return lesFormations;

    }
}
