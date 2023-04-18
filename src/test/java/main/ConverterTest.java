package main;

import org.assertj.core.data.Percentage;
import org.testng.annotations.*;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class ConverterTest {

    public final static double EPS = 0.001;

    Converter converter;

    @DataProvider(name = "celsius_3")
    public static Object[][] createData() {
        return new Object[][]{
                {10, 50},
                {0, 32},
                {40, 104}
        };
    }

    @BeforeClass
    public void beforeClass() {
        converter = new Converter();
    }

    @AfterClass
    public void afterClass() {
        converter = null;
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Before method");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After method");
    }

    @Test
    public void testConvertCelsiusToFahrenheit() {
        double actual = converter.convertCelsiusToFahrenheit(10.0);
        double expected = 50.0;
        assertEquals(actual, expected, EPS, "Test failed as...");
    }

    @Test
    public void testCheckCelsiusFalse() {
//        boolean condition = converter.checkCelsius(-300);
        //assertFalse(condition, "test false failed as...");
        assertThat(converter.checkCelsius(-300))
                .as("test false failed as...")
                .isFalse();
    }

    @Test(enabled = true, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "error data")
    public void testConverterCelsiusToFahrenheitException() {
        converter.convertCelsiusToFahrenheit(-300);
    }

    @Test
    public void testConvertCelsiusToFahrenheitExceptionMessage() {
        double celsius = -300.0;
        try {
            converter.convertCelsiusToFahrenheit(celsius);
            fail("Test for celsius " + celsius + "should have thrown a IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "error data");
        }
    }

    @Test(timeOut = 500)
    public void testTime() {
        for (int t = -273; t < 100_000_000; t++) {
            converter.convertCelsiusToFahrenheit(t);
        }
    }

    @Test(dataProvider = "celsius_3")
    public void testParamsConvert(double celsius, double expectedFahrenheit) {
        double actual = converter.convertCelsiusToFahrenheit(celsius);
        assertEquals(actual, expectedFahrenheit, EPS);
    }

    @Test
    public void testConvertFahrenheitToCelsius() {
        double actual = converter.convertFahrenheitToCelsius(50.0);
        double expected = 10.0;
        assertEquals(actual, expected, EPS);
    }

    @Test
    public void testConvertFahrenheitToCelsiusVersion2() {
        assertThat(converter.convertFahrenheitToCelsius(50.0))
                .isCloseTo(10.0, Percentage.withPercentage(0.1));
    }

    @Test
    public void testCopy() {
        String str = "hello";
        String actual = converter.copy(str);
        assertSame(actual, "hello");
    }
}