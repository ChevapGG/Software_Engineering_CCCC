package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Random;

public class Spielprozessor
{
    public int getCereal_counter() {
        return cereal_counter;
    }

    public void setCereal_counter(int cereal_counter) {
        this.cereal_counter = cereal_counter;
    }

    private int displayInterval = 25;
    private int factInterval = 50;
    private int cereal_counter = 4;
    private String lastCode = "1337";
    static GUI_Controller GUIC;
    static Spielprozessor SPZ;
    static Maskottchen MSK;


    public boolean checkcode()
    {
        int i = 0;
        String[] result = GUIC.getTf_CodeField().split("-");
        for (int x=0; x<result.length; x++)
        {
            System.out.println(result[x]);

            try
            {
                i += Integer.parseInt(result[x].trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        }
        if (!GUIC.getTf_CodeField().equalsIgnoreCase(SPZ.lastCode))
        {
            System.out.println("-----");
            System.out.println("Debug:");
            System.out.println(SPZ.lastCode);
            System.out.println(GUIC.getTf_CodeField());
            System.out.println("-----");

            SPZ.lastCode = GUIC.getTf_CodeField();

            if (i / 3 == 1337)
            {
                GUIC.green_Lbl_wrongCode();
                GUIC.setLbl_wrongCode("Code eingelöst");
                return true;
            }
            else {
               GUIC.red_Lbl_wrongCode();
                GUIC.setLbl_wrongCode("ungültiger Code");
            }

        }
        else
        {
            GUIC.red_Lbl_wrongCode();
            GUIC.setLbl_wrongCode("bereits Eingelöst");
        }
        return false;
    }





    public void add_cerial(boolean a)
    {
        if (a) {
            SPZ.cereal_counter += 4;
            GUIC.setLbl_counter((String.valueOf(cereal_counter)));
        }
    }

    public void changeDisplay(int state)
    {
        ImageIcon icon0 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/happy.png");
        ImageIcon icon1 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/neutral.png");
        ImageIcon icon2 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/hungry.png");
        if (state == 0)
            GUIC.setLbl_Display(icon0);
        if (state == 1)
            GUIC.setLbl_Display(icon1);
        if (state == 2)
            GUIC.setLbl_Display(icon2);
    }


    public void change_state()
    {

    }

    public void change_facts()
    {
        String[] facts = {"Es gibt 25 Hörnchen Untergattungen.","Streifenhörnchen wiegen zwischen 30 und 120 Gramm.","Streifenhörnchen sind tagaktiv","Streifenhörnchen leben hauptsächlich in Wäldern.","Die Tunnel der Streifenhörnchen können über 3.5m lang werden.","Streifenhörnchen teilen ihre Tunnel in Schlaf- und Abfalltunnel auf."};
        String random = (facts[new Random().nextInt(facts.length)]);
        GUIC.setTa_FactArea(random);
    }

    public static void main(String[] args)
    {
        System.out.println("Execution started.");

       // GUI_Controller123 GUIC = new GUI_Controller123();
        MSK = new Maskottchen();
        SPZ = new Spielprozessor();
        GUIC = new GUI_Controller();

        JFrame frame = new JFrame("Cherry Chipmunks Cereal Choice");
        frame.setContentPane(new GUI_Controller().pnl_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("./Projektordner Java/Produkt/src/main/resources/Images/Chipmunk_Logo.png"); //set logo
        frame.setIconImage(icon);

        File directory = new File("./");        //Debug to see where we are
        System.out.println(directory.getAbsolutePath());

        frame.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {

            }

            @Override
            public void windowClosing(WindowEvent e) //Save Gamestate [int state, int healthyFood, string lastCode]
            {

            }

            @Override
            public void windowClosed(WindowEvent e)
            {

            }

            @Override
            public void windowIconified(WindowEvent e)
            {

            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {

            }

            @Override
            public void windowActivated(WindowEvent e)
            {

            }

            @Override
            public void windowDeactivated(WindowEvent e)
            {

            }
        });

        Timer t = new Timer(200, new ActionListener() //adds a timer to change game state after a certain amount of time
        {
            int k = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                k++;
                System.out.println(k);

                if (k%SPZ.factInterval == 0)
                {
                    System.out.println("new Facts!");
                    SPZ.change_facts();
                }

                if (k%SPZ.displayInterval == 0)
                {
                    System.out.println("new Facts!");
                    if (MSK.getState() < 2)
                        MSK.setState(MSK.getState() + 1);
                    SPZ.changeDisplay(MSK.getState());
                }

                if (SPZ.getCereal_counter() == 0 || MSK.getState() == 0)
                {
                    GUIC.enable_btn(false);
                }
                else
                {
                    GUIC.enable_btn(true);
                }

                // CCCC_Dlg.lbl_Display.setIcon(new ImageIcon(myPicture));
            }
        });

        t.start();


    }





}
