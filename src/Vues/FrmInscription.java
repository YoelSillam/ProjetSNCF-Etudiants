package Vues;

import Controlers.CtrlActivite;
import Controlers.CtrlAgent;
import Controlers.CtrlFormation;
import Controlers.CtrlInscription;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class FrmInscription extends JFrame {
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblActivite;
    private JLabel lblAgentNonInscrit;
    private JTable tblActivites;
    private JTable tblAgentsNonInscrits;
    private JLabel lblFormation;
    private JTable tblFormations;
    private JButton btnInscription;
    CtrlFormation ctrlFormation;
    CtrlActivite ctrlActivite;
    //Classe-> CtrlActivite et nom objet -> ctrlActivite
    CtrlAgent ctrlAgent;
    CtrlInscription ctrlInscription;
    String numFormation;
    ModelJTable mdl;

    public FrmInscription() {
        this.setTitle("Inscription");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                // A vous de jouer
                mdl = new ModelJTable();
                ctrlActivite = new CtrlActivite();
                ctrlActivite.getAllActivites();
                mdl.LoadDatasActivites(ctrlActivite.getAllActivites());
                tblActivites.setModel(mdl);

            }
        });

        tblActivites.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer
                int numActivite = Integer.parseInt(tblActivites.getValueAt(tblActivites.getSelectedRow(), 0).toString());
                mdl = new ModelJTable();
                ctrlFormation = new CtrlFormation();
                mdl.LoadDatasFormations(ctrlFormation.getFormationsByActivite(numActivite));
                tblFormations.setModel(mdl);

            }
        });

        tblFormations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer
                numFormation = tblFormations.getValueAt(tblFormations.getSelectedRow(), 0).toString();
                mdl = new ModelJTable();
                ctrlAgent = new CtrlAgent();
                mdl.LoadDatasAgents(ctrlAgent.getAllAgentsNonInscrits(numFormation));
                tblAgentsNonInscrits.setModel(mdl);

            }
        });
        btnInscription.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // A vous de jouer
                if (tblAgentsNonInscrits.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un agent");
                } else {
                    ctrlInscription = new CtrlInscription();
                    for (int i = 0; i < tblAgentsNonInscrits.getSelectedRowCount(); i++) {
                        String numAgent = tblAgentsNonInscrits.getValueAt(tblAgentsNonInscrits.getSelectedRows()[i], 0).toString();
                        ctrlInscription.InsererInscription(numFormation, numAgent);
                    }
//Remettre a jour le Jtable des agents
                    mdl = new ModelJTable();
                    ctrlAgent = new CtrlAgent();
                    mdl.LoadDatasAgents(ctrlAgent.getAllAgentsNonInscrits(numFormation));
                    tblAgentsNonInscrits.setModel(mdl);
                }

            }
        });
    }
}
