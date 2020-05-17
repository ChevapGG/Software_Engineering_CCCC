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
    private JTextArea FactArea;
    private JTextField textField1;
    private JButton codeEinloesenButton;
    private JButton fuetternButton;
    private JPanel panelMain;
    private JLabel lbl_Display;

    public CCCC_Dlg() {
        codeEinloesenButton.addActionListener(new ActionListener() { //Check if the code in textField1 is correct
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        fuetternButton.addActionListener(new ActionListener() { //feed Chipmunk and decrement food counter
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Timer t = new Timer(1000, new ActionListener() {
            int k = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                k++;
                System.out.println(k);

                if (k%10 == 0)
                {
                    System.out.println("new Facts!");
                    changeFact();
                }

                if (k%25 == 0)
                {
                    System.out.println("new Facts!");
                    changeDisplay();
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
        frame.setContentPane(new CCCC_Dlg().panelMain);
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
            public void windowClosing(WindowEvent e) {

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







    public void changeDisplay(){
        ImageIcon icon = new ImageIcon("./Projektordner Java/Produkt/src/main/resources/Images/Chipmunk_Logo.png");
        lbl_Display.setIcon(icon);
    }

    public void changeFact(){

        String str = "abc";
        String[] facts = {"Es gibt 25 Hörnchen untergattungen.","Streifenhörnchen wiegen zwischen 30 und 120 gramm.","Streifenhörnchen sind tagaktiv","Streifenhörnchen leben hauptsächlich in Wäldern.","Die Tunnel der Streifenhörnchen können über 3.5m lang werden.","Streifenhörnchen teilen ihre Tunnel in Schlaf- und Abfalltunnel auf."};
        String random = (facts[new Random().nextInt(facts.length)]);
        FactArea.setText(random);
    }


}
