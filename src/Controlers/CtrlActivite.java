package Controlers;

import Entities.Activite;
import Tools.ConnexionBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlActivite {

    //L'objet cnx permet de saisir les identifiants au serveur, à la BDD, au serv + mdp
    private Connection cnx;
    //L'objet ps==> créer la Requete sql
    private PreparedStatement ps;
    private ResultSet rs;
    //L'objet rs => permet de récuper le resultat de la requete

    public CtrlActivite() {
        cnx = ConnexionBDD.getCnx();
    }

    // Permet de récupérer toutes les activités
    public ArrayList<Activite> getAllActivites()  {

        ArrayList<Activite> lesActivites = new ArrayList<>();
        try{
        ps = cnx.prepareStatement("SELECT activite.numero, activite.libelle\n"+" FROM activite");
        rs = ps.executeQuery();
        while (rs.next())
        {
            Activite uneActivite = new Activite(rs.getInt(1), rs.getString(2));
            lesActivites.add(uneActivite);

        }rs.close();
        ps.close();}
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erreur requete sql");
        }
        return lesActivites;
    }
}
