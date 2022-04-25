import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass testClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int expectedNumber  = 14;
        int actualNumber    = testClass.getLocalNumber();

        Assert.assertEquals(
                "Numbers are not equals: " + expectedNumber + " != " + actualNumber,
                expectedNumber,
                actualNumber
        );
    }
}