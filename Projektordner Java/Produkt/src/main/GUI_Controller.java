package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Random;

import static main.Spielprozessor.MSK;
import static main.Spielprozessor.SPZ;

public class GUI_Controller {
    private int displayInterval = 25;
    private int factInterval = 50;
    private int state = 1;
    private int cereal_counter = 4;
    private String lastCode = "1337";
    private byte codeMode;

    private JTextArea ta_FactArea;


    public byte getCodeMode() {
        return codeMode;
    }

    public void setCodeMode(byte codeMode) {
        this.codeMode = codeMode;
    }

    public String getTa_FactArea() {
        return this.ta_FactArea.getText();
    }

    public void setTa_FactArea(String a) {
        this.ta_FactArea.setText(a);
    }

    public String getTf_CodeField() {
        System.out.println("CODEFIELDTEXT:" + tf_CodeField.getText());
        return this.tf_CodeField.getText();
    }

    public void setTf_CodeField(String a) {
        this.tf_CodeField.setText(a);
    }

    public Icon getLbl_Display() {
        return this.lbl_Display.getIcon();
    }

    public void setLbl_Display(ImageIcon a) {
        this.lbl_Display.setIcon(a);
    }

    public Icon getLbl_counter() {
        return this.lbl_counter.getIcon();
    }

    public void setLbl_counter(String a) {
        this.lbl_counter.setText(a);
    }

    public String getLbl_wrongCode() {
        return this.lbl_wrongCode.getText();
    }

    public void setLbl_wrongCode(String a) {
        this.lbl_wrongCode.setText(a);
    }

    public void green_Lbl_wrongCode() {
        this.lbl_wrongCode.setForeground(Color.green);
    }

    public void red_Lbl_wrongCode() {
        this.lbl_wrongCode.setForeground(Color.red);
    }

    public void enable_btn(boolean a) {
        this.btn_fuettern.setEnabled(a);
    }


    private JTextField tf_CodeField;
    private JButton btn_CodeEinloesen;
    private JButton btn_fuettern;
    public JPanel pnl_Main;
    private JLabel lbl_Display;
    private JLabel lbl_counter;
    public JLabel lbl_wrongCode;
    private JFrame frame;

    public GUI_Controller() {
        Timer t = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon0 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/happy.png");
                ImageIcon icon1 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/neutral.png");
                ImageIcon icon2 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/hungry.png");
                if (MSK.getState() == 0)
                    setLbl_Display(icon0);
                if (MSK.getState() == 1)
                    setLbl_Display(icon1);
                if (MSK.getState() == 2)
                    setLbl_Display(icon2);
                setTa_FactArea(Spielprozessor.GUIC.getTa_FactArea());
                setLbl_counter(Integer.toString(SPZ.getCereal_counter()));
            }
        });
        t.start();
        btn_CodeEinloesen.addActionListener(new ActionListener() //Check if the code in textField1 is correct
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                SPZ.add_cerial(SPZ.checkcode(tf_CodeField.getText()));
                switch (Spielprozessor.GUIC.getCodeMode()) {
                    case 0: {
                        green_Lbl_wrongCode();
                        setLbl_wrongCode("Code eingelöst");
                        System.out.println(codeMode);
                        break;
                    }
                    case 1: {
                        red_Lbl_wrongCode();
                        setLbl_wrongCode("ungültiger Code");
                        System.out.println(codeMode);
                        break;
                    }
                    case 2: {
                        red_Lbl_wrongCode();
                        setLbl_wrongCode("bereits Eingelöst");
                        System.out.println(codeMode);
                        break;
                    }

                }
                setLbl_counter(((toString().valueOf(SPZ.getCereal_counter()))));
            }
        });
        btn_fuettern.addActionListener(new ActionListener() //feed Chipmunk and decrement food counter
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SPZ.getCereal_counter() > 0 && MSK.getState() > 0) {
                    SPZ.setCereal_counter(SPZ.getCereal_counter() - 1);
                    MSK.setState(MSK.getState() - 1);
                    lbl_counter.setText(String.valueOf(SPZ.getCereal_counter()));
                    SPZ.changeDisplay(MSK.getState());
                }
            }
        });
        /*
        Timer t = new Timer(200, new ActionListener() //adds a timer to change game state after a certain amount of time
        {
            int k = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                k++;
                System.out.println(k);

                if (k%factInterval == 0)
                {
                    System.out.println("new Facts!");
                    SPZ.change_facts();
                }

                if (k%displayInterval == 0)
                {
                    System.out.println("new Facts!");
                    if (state < 2)
                    state++;
                    SPZ.changeDisplay(state);
                }

                if (cereal_counter == 0 || state == 0)
                {
                    btn_fuettern.setEnabled(false);
                }
                else
                {
                    btn_fuettern.setEnabled(true);
                }

                // CCCC_Dlg.lbl_Display.setIcon(new ImageIcon(myPicture));
            }
        });

        t.start();
*/

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }




public void initGUI()
{
    frame = new JFrame("Cherry Chipmunks Cereal Choice");
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
            SPZ.Save();
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


/*
    public void changeDisplay(int state)
    {
        ImageIcon icon0 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/happy.png");
        ImageIcon icon1 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/neutral.png");
        ImageIcon icon2 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/hungry.png");
        if (state == 0)
            lbl_Display.setIcon(icon0);
        if (state == 1)
            lbl_Display.setIcon(icon1);
        if (state == 2)
            lbl_Display.setIcon(icon2);
    }
*/

    /*
    public void changeFact()
    {
        String[] facts = {"Es gibt 25 Hörnchen Untergattungen.","Streifenhörnchen wiegen zwischen 30 und 120 Gramm.","Streifenhörnchen sind tagaktiv","Streifenhörnchen leben hauptsächlich in Wäldern.","Die Tunnel der Streifenhörnchen können über 3.5m lang werden.","Streifenhörnchen teilen ihre Tunnel in Schlaf- und Abfalltunnel auf."};
        String random = (facts[new Random().nextInt(facts.length)]);
        ta_FactArea.setText(random);
    }
*/
    /*
public boolean checkcode()
{
    int i = 0;
    String[] result = tf_CodeField.getText().split("-");
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
    if (!tf_CodeField.getText().equalsIgnoreCase(lastCode))
    {
        System.out.println("-----");
        System.out.println("Debug:");
        System.out.println(lastCode);
        System.out.println(tf_CodeField.getText());
        System.out.println("-----");

        lastCode = tf_CodeField.getText();

        if (i / 3 == 1337)
        {
            lbl_wrongCode.setForeground(Color.green);
            lbl_wrongCode.setText("Code eingelöst");
            return true;
        }
        else {
            lbl_wrongCode.setForeground(Color.red);
            lbl_wrongCode.setText("ungültiger Code");
        }

    }
    else
    {
        lbl_wrongCode.setForeground(Color.red);
        lbl_wrongCode.setText("bereits eingegeben");
    }
    return false;
};
*/
}
