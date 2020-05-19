package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Random;

public class CCCC_Dlg {
    private int displayInterval = 5;
    private int factInterval = 10;
    private int state = 1;
    private int foodCounter = 0;
    private String lastCode = "1337";

    private JTextArea ta_FactArea;
    private JTextField tf_CodeField;
    private JButton btn_CodeEinloesen;
    private JButton btn_fuettern;
    private JPanel pnl_Main;
    private JLabel lbl_Display;
    private JLabel lbl_counter;



    public CCCC_Dlg() {
        btn_CodeEinloesen.addActionListener(new ActionListener() { //Check if the code in textField1 is correct
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CodeCheck())
                {
                    foodCounter += 4;
                    lbl_counter.setText(String.valueOf(foodCounter));
                }

            }
        });
        btn_fuettern.addActionListener(new ActionListener() { //feed Chipmunk and decrement food counter
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foodCounter > 0 && state > 0)
                {
                    foodCounter--;
                    state--;
                    lbl_counter.setText(String.valueOf(foodCounter));
                    changeDisplay(state);
                }
            }
        });
        Timer t = new Timer(1000, new ActionListener() {    //adds a timer to change game state after a certain amount of time
            int k = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                k++;
                System.out.println(k);

                if (k%factInterval == 0)
                {
                    System.out.println("new Facts!");
                    changeFact();
                }

                if (k%displayInterval == 0)
                {
                    System.out.println("new Facts!");
                    if (state < 2)
                    state++;
                    changeDisplay(state);
                }


                // CCCC_Dlg.lbl_Display.setIcon(new ImageIcon(myPicture));
            }
        });

        t.start();

    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }


    public static void main(String[] args) {
        System.out.println("Hello, World !!!");
        JFrame frame = new JFrame("Cherry Chipmunks Cereal Choice");
        frame.setContentPane(new CCCC_Dlg().pnl_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("./Projektordner Java/Produkt/src/main/resources/Images/Chipmunk_Logo.png"); //set logo
        frame.setIconImage(icon);

        File directory = new File("./");        //Debug to see where we are
        System.out.println(directory.getAbsolutePath());

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) { //Save Gamestate [int state, int healthyFood, string lastCode]

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


    }


    public void changeDisplay(int state){
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

    public void changeFact(){

        String[] facts = {"Es gibt 25 Hörnchen untergattungen.","Streifenhörnchen wiegen zwischen 30 und 120 gramm.","Streifenhörnchen sind tagaktiv","Streifenhörnchen leben hauptsächlich in Wäldern.","Die Tunnel der Streifenhörnchen können über 3.5m lang werden.","Streifenhörnchen teilen ihre Tunnel in Schlaf- und Abfalltunnel auf."};
        String random = (facts[new Random().nextInt(facts.length)]);
        ta_FactArea.setText(random);
    }

public boolean CodeCheck(){
    int i = 0;
    String[] result = tf_CodeField.getText().split("-");
    for (int x=0; x<result.length; x++) {
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
    if (!tf_CodeField.getText().equalsIgnoreCase(lastCode)) {

        System.out.println("-----");
        System.out.println("Debug:");
        System.out.println(lastCode);
        System.out.println(tf_CodeField.getText());
        System.out.println("-----");

        lastCode = tf_CodeField.getText();

        if (i / 3 == 1337)
            return true;
    }
    return false;
};

}
