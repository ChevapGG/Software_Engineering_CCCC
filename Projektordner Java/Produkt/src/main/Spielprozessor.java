package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class Spielprozessor
{
    private int cereal_counter = 4;
    private String lastCode = "1337";
    static CCCC_Dlg CDLG;


    public boolean checkcode()
    {
        int i = 0;
        String[] result = CDLG.getTf_CodeField().split("-");
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
        if (!CDLG.getTf_CodeField().equalsIgnoreCase(lastCode))
        {
            System.out.println("-----");
            System.out.println("Debug:");
            System.out.println(lastCode);
            System.out.println(CDLG.getTf_CodeField());
            System.out.println("-----");

            lastCode = CDLG.getTf_CodeField();

            if (i / 3 == 1337)
            {
                CDLG.green_Lbl_wrongCode();
                CDLG.setLbl_wrongCode("Code eingelöst");
                return true;
            }
            else {
               CDLG.red_Lbl_wrongCode();
                CDLG.setLbl_wrongCode("ungültiger Code");
            }

        }
        else
        {
            CDLG.red_Lbl_wrongCode();
            CDLG.setLbl_wrongCode("bereits Eingelöst");
        }
        return false;
    }



    public static void main(String[] args)
    {
        System.out.println("Execution started.");

        GUI_Controller GUIC = new GUI_Controller();
        Maskottchen MSK = new Maskottchen();
        //Spielprozessor SPZ = new Spielprozessor();
        CDLG = new CCCC_Dlg();

        JFrame frame = new JFrame("Cherry Chipmunks Cereal Choice");
        frame.setContentPane(new CCCC_Dlg().pnl_Main);
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


    }





}
