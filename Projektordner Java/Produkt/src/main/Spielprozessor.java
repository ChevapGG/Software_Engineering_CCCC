package main;

import java.awt.*;

public class Spielprozessor
{
    private int cereal_counter = 4;



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
            lbl_wrongCode.setText("bereits eingelöst");
        }
        return false;
    }
}
