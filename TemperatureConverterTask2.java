import java.util.Scanner;

public class TemperatureConverterTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();
        
        System.out.print("Enter the original unit of measurement (Celsius, Fahrenheit, or Kelvin): ");
        String originalUnit = scanner.next().toLowerCase();

        if (originalUnit.equals("celsius")) {
            double fahrenheit = celsiusToFahrenheit(temperature);
            double kelvin = celsiusToKelvin(temperature);
            System.out.println(temperature + " Celsius is equal to " + fahrenheit + " Fahrenheit and " + kelvin + " Kelvin.");
        } else if (originalUnit.equals("fahrenheit")) {
            double celsius = fahrenheitToCelsius(temperature);
            double kelvin = celsiusToKelvin(celsius);
            System.out.println(temperature + " Fahrenheit is equal to " + celsius + " Celsius and " + kelvin + " Kelvin.");
        } else if (originalUnit.equals("kelvin")) {
            double celsius = kelvinToCelsius(temperature);
            double fahrenheit = celsiusToFahrenheit(celsius);
            System.out.println(temperature + " Kelvin is equal to " + celsius + " Celsius and " + fahrenheit + " Fahrenheit.");
        } else {
            System.out.println("Invalid input. Please enter Celsius, Fahrenheit, or Kelvin.");
        }

        scanner.close();
    }

    // Conversion functions
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
