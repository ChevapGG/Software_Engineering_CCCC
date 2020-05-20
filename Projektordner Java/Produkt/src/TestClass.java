import main.GUI_Controller;
import main.Maskottchen;
import main.Spielprozessor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import javax.swing.*;

public class TestClass {

    @Test(priority = 1)
    void setUp() {
        // code that will be invoked when this test is instantiated
        //open window
        System.out.println("Setup");
        String[] args = new String[1];
        Spielprozessor.main(args);
    }

    @Test(priority = 2)
    void test1() {
        System.out.println("test save feature");

        Spielprozessor SPZ = new Spielprozessor();
        GUI_Controller GUIC = new GUI_Controller();
        Maskottchen MSK = new Maskottchen();
        MSK.setState(2);
        Spielprozessor.Save();
        Spielprozessor.Load();
        Assert.assertEquals(MSK.getState(),2);
        MSK.setState(1);
        Spielprozessor.Save();
        Spielprozessor.Load();
        Assert.assertEquals(MSK.getState(),1);



    }

    @Test(priority = 3)
    void test2() {
        System.out.println("Teste Codes (+mehrmalige Eingabe dieser");

        Spielprozessor SPZ = new Spielprozessor();
        GUI_Controller GUIC = new GUI_Controller();
        Maskottchen MSK = new Maskottchen();

        Assert.assertTrue(SPZ.checkcode("1337-1337-1337"));
        Assert.assertFalse(SPZ.checkcode("1337-1337-1337"));


    }


    @AfterTest
    public void endSession() {
            System.out.println("end of Test");
    }

}