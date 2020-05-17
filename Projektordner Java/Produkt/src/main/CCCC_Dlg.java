package main;

import javax.swing.*;

public class CCCC_Dlg {
    private JTextArea esGibt23UnterartenTextArea;
    private JTextField textField1;
    private JButton codeEinloesenButton;
    private JButton fuetternButton;
    private JPanel panelMain;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }




    public static void main(String[] args) {
        System.out.println("Hello, World !!!");

        JFrame frame = new JFrame("Cherry Chipmunks Cereal Choice");
        frame.setContentPane(new CCCC_Dlg().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
