import org.testng.annotations.Test;

public class TestClass {

    @Test(priority = 1)
    void setUp()
    {
        // code that will be invoked when this test is instantiated
        //open window
        System.out.println("Setup");
    }

@Test(priority = 2)
    void test1()
    {
        System.out.println("test1");
    }

    @Test(priority = 3)
    void test2()
    {
        System.out.println("test2");
    }

}
