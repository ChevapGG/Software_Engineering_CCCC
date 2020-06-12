package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import static main.Spielprozessor.MSK;
import static main.Spielprozessor.SPZ;

public class GUI_Controller
{
    private JTextField tf_CodeField;
    public JButton btn_CodeEinloesen;
    private JButton btn_fuettern;
    public JPanel pnl_Main;
    private JLabel lbl_Display;
    private JLabel lbl_counter;
    public JLabel lbl_wrongCode;
    private JFrame frame;
    private byte codeMode;
    private JTextArea ta_FactArea;
    private JButton btn_about;

    public byte getCodeMode()
    {
        return codeMode;
    }

    public void setCodeMode(byte codeMode)
    {
        this.codeMode = codeMode;
    }

    public String getTa_FactArea()
    {
        return this.ta_FactArea.getText();
    }

    public void setTa_FactArea(String a)
    {
        this.ta_FactArea.setText(a);
    }

    public String getTf_CodeField()
    {
        System.out.println("CODEFIELDTEXT:" + tf_CodeField.getText());
        return this.tf_CodeField.getText();
    }

    public void setTf_CodeField(String a)
    {
        this.tf_CodeField.setText(a);
    }

    public Icon getLbl_Display()
    {
        return this.lbl_Display.getIcon();
    }

    public void setLbl_Display(ImageIcon a)
    {
        this.lbl_Display.setIcon(a);
    }

    public Icon getLbl_counter()
    {
        return this.lbl_counter.getIcon();
    }

    public void setLbl_counter(String a)
    {
        this.lbl_counter.setText(a);
    }

    public String getLbl_wrongCode()
    {
        return this.lbl_wrongCode.getText();
    }

    public void setLbl_wrongCode(String a)
    {
        this.lbl_wrongCode.setText(a);
    }

    public void green_Lbl_wrongCode()
    {
        this.lbl_wrongCode.setForeground(Color.green);
    }

    public void red_Lbl_wrongCode()
    {
        this.lbl_wrongCode.setForeground(Color.red);
    }

    public void enable_btn(boolean a)
    {
        this.btn_fuettern.setEnabled(a);
    }

    public void about()
    {
        JOptionPane.showMessageDialog(null,
                "Cherry Chipmunks Cereal Choice\n V1.0.1\n LDJ Software im Auftrag von CCCC AG",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public GUI_Controller()
    {
        Timer t = new Timer(200, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /*
                ImageIcon icon0 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/happy.png");
                ImageIcon icon1 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/neutral.png");
                ImageIcon icon2 = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/hungry.png");
                *///1337-1337-1337
                ImageIcon icon0 = new ImageIcon("./Images/happy.png");
                ImageIcon icon1 = new ImageIcon("./Images/neutral.png");
                ImageIcon icon2 = new ImageIcon("./Images/hungry.png");
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
            public void actionPerformed(ActionEvent e)
            {
                SPZ.add_cerial(SPZ.checkcode(tf_CodeField.getText()));
                switch (Spielprozessor.GUIC.getCodeMode())
                {
                    case 0:
                        {
                        green_Lbl_wrongCode();
                        setLbl_wrongCode("Code eingelöst");
                        System.out.println(codeMode);
                        break;
                    }
                    case 1:
                        {
                        red_Lbl_wrongCode();
                        setLbl_wrongCode("ungültiger Code");
                        System.out.println(codeMode);
                        break;
                    }
                    case 2:
                        {
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
            public void actionPerformed(ActionEvent e)
            {
                if (SPZ.getCereal_counter() > 0 && MSK.getState() > 0)
                {
                    SPZ.setCereal_counter(SPZ.getCereal_counter() - 1);
                    MSK.setState(MSK.getState() - 1);
                    lbl_counter.setText(String.valueOf(SPZ.getCereal_counter()));
                    SPZ.changeDisplay(MSK.getState());
                }
            }
        });

        btn_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

public void initGUI()
{
    frame = new JFrame("Cherry Chipmunks Cereal Choice");
    frame.setContentPane(new GUI_Controller().pnl_Main);
    //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            //JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
        }

        @Override
        public void windowClosing(WindowEvent e) //Save Gamestate [int state, int healthyFood, string lastCode]
        {
            SPZ.Save();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e)
        {
            //System.exit(0);
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
