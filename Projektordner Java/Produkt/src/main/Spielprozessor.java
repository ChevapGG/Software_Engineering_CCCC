package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Spielprozessor
{

    private int displayInterval = 25;
    private int factInterval = 50;
    private int cereal_counter = 4;
    private String lastCode = "1337";
    static GUI_Controller GUIC;
    static Spielprozessor SPZ;
    static Maskottchen MSK;


    public int getCereal_counter() {
        return cereal_counter;
    }

    public void setCereal_counter(int cereal_counter) {
        this.cereal_counter = cereal_counter;
    }

    public boolean checkcode(String text)
    {
        int i = 0;
        String[] result = text.split("-");
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
        if (!text.equalsIgnoreCase(SPZ.lastCode))
        {
            System.out.println("-----");
            System.out.println("Debug:");
            System.out.println(SPZ.lastCode);
            System.out.println(text);
            System.out.println("-----");

            SPZ.lastCode = text;

            if (i / 3 == 1337)
            {
                GUIC.setCodeMode((byte) 0);
                System.out.println(GUIC.getCodeMode());
                return true;
            }
            else {
                GUIC.setCodeMode((byte) 1);
                System.out.println(GUIC.getCodeMode());
            }

        }
        else
        {
            GUIC.setCodeMode((byte) 2);
            System.out.println(GUIC.getCodeMode());
        }
        return false;
    }
    public void add_cerial(boolean a)
    {
        if (a)
        {
            SPZ.cereal_counter += 4;
        }
    }

    public void changeDisplay(int state)
    {
        ImageIcon icon0 = new ImageIcon("./Images/happy.png");
        ImageIcon icon1 = new ImageIcon("./Images/neutral.png");
        ImageIcon icon2 = new ImageIcon("./Images/hungry.png");
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

    public static void Save() {
       // File file = new File("./Projektordner Java/Produkt/src/main/resources/Saves/Gamestate.dat");
        File file = new File("./Gamestate.dat");
        FileWriter fr = null;
        String save_data = MSK.getState() + "-" + SPZ.getCereal_counter();
        try {
            fr = new FileWriter(file);
            fr.write(save_data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Load()
    {
        String data = "";
        try
        {
            //data = new String(Files.readAllBytes(Paths.get("./Projektordner Java/Produkt/src/main/resources/Saves/Gamestate.dat")));
           data = new String(Files.readAllBytes(Paths.get("./Gamestate.dat")));
            System.out.println("loading...");
            System.out.println(data);
            String[] result = data.split("-");
            MSK.setState(Integer.valueOf(result[0]));
            SPZ.setCereal_counter(Integer.valueOf(result[1]));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Execution started.");
        MSK = new Maskottchen();
        SPZ = new Spielprozessor();
        GUIC = new GUI_Controller();

        GUIC.initGUI();
        SPZ.Load();

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
                    System.out.println("More Hunger!");
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
            }
        });
        t.start();
    }

}
