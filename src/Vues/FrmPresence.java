package Vues;

import Controlers.CtrlActivite;
import Controlers.CtrlAgent;
import Controlers.CtrlFormation;
import Controlers.CtrlInscription;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmPresence extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblFormation;
    private JLabel lblAgentsInscrits;
    private JTable tblFormations;
    private JTable tblAgentsInscrits;
    private JButton btnValider;
    ModelJTable mdl;
    CtrlFormation ctrlFormation;
    String numFormation;

    CtrlActivite ctrlActivite;
    //Classe-> CtrlActivite et nom objet -> ctrlActivite
    CtrlAgent ctrlAgent;
    CtrlInscription ctrlInscription;
    public FrmPresence()
    {
        this.setTitle("Présence");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                mdl = new ModelJTable();
                ctrlFormation = new CtrlFormation();
                mdl.LoadDatasFormations(ctrlFormation.getAllByActivite());
                tblFormations.setModel(mdl);
                // A vous de jouer
            }
        });

        tblFormations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer
                numFormation = tblFormations.getValueAt(tblFormations.getSelectedRow(),0).toString();

                mdl = new ModelJTable();
                ctrlAgent=new CtrlAgent();
                mdl.LoadDatasAgentsInscrits(ctrlAgent.getAllAgentsInscritsFormation(numFormation));
                tblAgentsInscrits.setModel(mdl);
            }
        });
        
        btnValider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer

                if (tblFormations.getSelectedRowCount() == 0) {

                    JOptionPane.showMessageDialog(null, "Sélectionnez une formation", "Choix de la formaation", JOptionPane.ERROR_MESSAGE);
                } else if (tblAgentsInscrits.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Sélectionnez un agent", "Choix de l'agent", JOptionPane.ERROR_MESSAGE);
                } else {

                    ctrlInscription = new CtrlInscription();
for(int i = 0 ; i< tblAgentsInscrits.getRowCount(); i++) {
    String numFormation = tblFormations.getValueAt(tblFormations.getSelectedRow(),0).toString();
    String codeAgent = tblAgentsInscrits.getValueAt(i,0).toString();
    boolean present=Boolean.parseBoolean(tblAgentsInscrits.getValueAt(i,3).toString());
    ctrlInscription.ModifierPresence(numFormation,codeAgent,present);
}
                }

            }

        });
    }
}