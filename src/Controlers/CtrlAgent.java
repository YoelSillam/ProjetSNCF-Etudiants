package Controlers;

import Entities.Agent;
import Entities.AgentPresent;
import Tools.ConnexionBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlAgent {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlAgent() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Agent> getAllAgentsNonInscrits(String idFormation) {
        ArrayList<Agent> lesAgents = new ArrayList<>();
//Ecrire la requete qui permet de recuperer les agents non inscirts
        try {
            ps = cnx.prepareStatement("SELECT agent.code, agent.nom, agent.prenom\n" +
                    "FROM agent\n" +
                    "WHERE agent.code NOT IN (\n" +
                    "SELECT agent.code\n" +
                    "FROM agent\n" +
                    "INNER JOIN inscription on agent.code = inscription.codeAgent\n" +
                    "WHERE inscription.numeroFormation =?)"
            );
            ps.setString(1, idFormation);
            rs = ps.executeQuery();
            while (rs.next()) {
                Agent unAgentPasPresent = new Agent(rs.getString(1), rs.getString(2), rs.getString(3));
                lesAgents.add(unAgentPasPresent);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur requete sql");
            throw new RuntimeException(ex);
        }
        return lesAgents;


    }

    public ArrayList<AgentPresent> getAllAgentsInscritsFormation(String idFormation) {
        ArrayList<AgentPresent> lesAgentsPresents = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select code, nom, prenom,presence from agent inner join inscription on code=codeAgent where numeroFormation = ?");
            ps.setString(1, idFormation);
            rs = ps.executeQuery();
            while (rs.next()) {
                AgentPresent agt = new AgentPresent(rs.getString("code"), rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("presence"));
                lesAgentsPresents.add(agt);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesAgentsPresents;
    }
}
