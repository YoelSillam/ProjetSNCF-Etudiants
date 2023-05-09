package Controlers;

import Entities.Agent;
import Entities.Formation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlInscription
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlInscription()
    {
        cnx = ConnexionBDD.getCnx();
    }


    public void InsererInscription(String codeFormation, String codeAgent)
    {
        try {
            ps =cnx.prepareStatement("insert into inscription VALUES (?,?,?)");
            ps.setString(1,codeFormation);
            ps.setString(2,codeAgent);
            ps.setInt(3,0);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void ModifierPresence(String idFormation, String idAgent, boolean present)
    {
        try {
            if(present)
            {
                ps = cnx.prepareStatement("update inscription set presence = 1 where numeroFormation = ? and codeAgent = ?");
            }
            else
            {
                ps = cnx.prepareStatement("update inscription set presence = 0 where numeroFormation = ? and codeAgent = ?");
            }
            ps.setString(1,idFormation);
            ps.setString(2,idAgent);
            ps.executeUpdate();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
