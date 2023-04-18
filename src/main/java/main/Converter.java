package main;

public class Converter {
    private static final double ABSOLUTE_ZERO = -273.15;

    public double convertCelsiusToFahrenheit(double celsius) {
        if(celsius < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException("error data");
        }
        return celsius * 1.8 + 32;
    }

    public boolean checkCelsius(double celsius) {
        return celsius >= ABSOLUTE_ZERO;
    }

    public String copy(String s) {
        return s;
    }

    public double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit-32)/1.8; // 9/5
    }
}
