import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass testClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int localNumberExpected  = 14;
        int localNumberActual    = testClass.getLocalNumber();

        Assert.assertEquals(
                "Numbers are not equal: " + localNumberExpected + " != " + localNumberActual + ".",
                localNumberExpected,
                localNumberActual
        );
    }

    @Test
    public void testGetClassNumber() {
        int classNumberLowLimit = 45;
        int classNumberActual   = testClass.getClassNumber();

        Assert.assertTrue(
                "Number " + classNumberActual + " is less than " + classNumberLowLimit + " or equal to it.",
                classNumberActual > classNumberLowLimit
        );
    }
}