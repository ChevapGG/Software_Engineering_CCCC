import main.GUI_Controller;
import main.Maskottchen;
import main.Spielprozessor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestClass
{



    @Test(priority = 1)
    void setUp()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("Setup");
        Spielprozessor.main(null); //open Game
    }

    @Test(priority = 2)
    void test1()
    {
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
    void test2()
    {
        System.out.println("Teste Codes (+mehrmalige Eingabe dieser)");

        Spielprozessor SPZ = new Spielprozessor();
        GUI_Controller GUIC = new GUI_Controller();
        Maskottchen MSK = new Maskottchen();

        Assert.assertTrue(SPZ.checkcode("1337-1337-1336-1"));
        Assert.assertFalse(SPZ.checkcode("1337-1337-1336-1"));
    }

    @Test(priority = 4) //THIS TEST USES SIMULATED USER INPUTS! PLEASE USE A RESOLUTION OF 1920x1080 AND YOUR PRIMARY DISPLAY TO PREVENT DISPLAY COORDINATE ISSUES.
    void test3()
    {
        System.out.println("Teste Gameplay");

        //CHAPTER 1 PREPARE A SAVE FILE WITH SET VALUES. THIS OVERRIDES OLD SAVES.

        File file = new File("./Gamestate.dat");
        FileWriter fr = null;
        String save_data = "1" + "-" + "4";
        try
        {
            fr = new FileWriter(file);
            fr.write(save_data);
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            //close resources
            try
            {
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        //CHAPTER 2 INITIALISE THE GAME AND THE ROBOT

        Robot robot = null;
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        } catch (SecurityException e)
        {
            e.printStackTrace();
        }

        /*
        Thread t1 = new Thread(new Runnable() {
            public void run()
            {

                Spielprozessor.main(null);

            }});
        t1.start();
*/


        //CHAPTER 3 Enter and check Code

        robot.delay(300);
        robot.mouseMove(1041, 388);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        int counter = 0;
        for(int j = 0; j<3;j++)
        {
            counter++;
            robot.keyPress(49);
            robot.keyRelease(49);
            for (int i = 0; i < 2; i++)
            {
                robot.keyPress(51);
                robot.keyRelease(51);
            }

            robot.keyPress(55);
            robot.keyRelease(55);

            if (counter<3)
            {
                robot.keyPress(109);
                robot.keyRelease(109);
            }
            //CODE: 1337-1337-1337
        }

        robot.delay(300);
        robot.mouseMove(1055, 418);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        //CHAPTER 4 TEST THE FEEDING BUTTON

        robot.delay(300);
        robot.mouseMove(1055, 503);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        //CHAPTER 5 TEST THE ABOUT BUTTON
        robot.delay(300);
        robot.mouseMove(1055, 666);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        //CHAPTER 6 CLOSE THE ABOUT WINDOW

        robot.delay(300);
        robot.mouseMove(950, 560);
        robot.delay(100); //the redundancy is for aesthetic reasons. it would look a bit odd without the second delay.
        robot.mouseMove(950, 560);  //The second mouse move should prevent user interaction issues
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        //CHAPTER 7 CLOSE THE APPLICATION //THIS WOULD STOP TESTNG AS WELL..
/*
        robot.mouseMove(1111, 15);
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
*/

        //CHAPTER 8 PASS THE LAST CHECK AFTER A SHORT DELAY

        robot.delay(500);
        Assert.assertTrue(true); //This Assert wouldnt be called if the test runs into any runtime errors

    }


    @AfterTest
    public void endSession()
    {
            System.out.println("end of Test");
    }
}