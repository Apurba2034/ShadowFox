package Shadofox_project;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.InputMismatchException;
import java.util.Scanner;


public class console_based_calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean continuecalc = true;

        while (continuecalc) {
            System.out.println("\n===== CONSOLE CALCULATOR =====");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Temperature Conversion");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                switch (choice) {

                    case 1 -> add();
                    case 2 -> subtract();
                    case 3 -> multiply();
                    case 4 -> divide();
                    case 5 -> squareRoot();
                    case 6 -> power();
                    case 7 -> temperatureConversion();
                    case 0 -> {
                        System.out.println("Calculator closed.");
                        continuecalc = false;
                        continue;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                    sc.next(); 
                }

                System.out.print("\nDo you want to continue? (yes/no): ");
                String ans = sc.next();
                if (!ans.equalsIgnoreCase("yes")) {
                    continuecalc = false;
                    System.out.println("Thank you for using the calculator.");
                }
            }
        }




        static BigDecimal readNumber(String msg) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);
            return sc.nextBigDecimal();
        }

        static void add() {
            BigDecimal a = readNumber("Enter first number: ");
            BigDecimal b = readNumber("Enter second number: ");
            System.out.println("Result = " + a.add(b));
        }

        static void subtract() {
            BigDecimal a = readNumber("Enter first number: ");
            BigDecimal b = readNumber("Enter second number: ");
            System.out.println("Result = " + a.subtract(b));
        }

        static void multiply() {
            BigDecimal a = readNumber("Enter first number: ");
            BigDecimal b = readNumber("Enter second number: ");
            System.out.println("Result = " + a.multiply(b));
        }

        static void divide() {
            BigDecimal a = readNumber("Enter dividend: ");
            BigDecimal b = readNumber("Enter divisor: ");

            if (b.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Error: Division by zero is not allowed.");
                return;
            }

            System.out.println("Result = " + a.divide(b, MathContext.DECIMAL128));
        }

        static void squareRoot() {
            BigDecimal a = readNumber("Enter number: ");
            if (a.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Error: Cannot calculate square root of negative number.");
                return;
            }

            double result = Math.sqrt(a.doubleValue());
            System.out.println("Result = " + BigDecimal.valueOf(result));
        }

        static void power() {
            Scanner sc = new Scanner(System.in);
            BigDecimal base = readNumber("Enter base: ");
            System.out.print("Enter exponent (integer): ");
            int exp = sc.nextInt();

            System.out.println("Result = " + base.pow(exp));
        }


        static void temperatureConversion() {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1. Celsius to Fahrenheit");
            System.out.println("2. Fahrenheit to Celsius");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                BigDecimal c = readNumber("Enter Celsius: ");
                BigDecimal f = c.multiply(BigDecimal.valueOf(9))
                        .divide(BigDecimal.valueOf(5))
                        .add(BigDecimal.valueOf(32));
                System.out.println("Fahrenheit = " + f);

            } else if (ch == 2) {
                BigDecimal f = readNumber("Enter Fahrenheit: ");
                BigDecimal c = f.subtract(BigDecimal.valueOf(32))
                        .multiply(BigDecimal.valueOf(5))
                        .divide(BigDecimal.valueOf(9));
                System.out.println("Celsius = " + c);

            } else {
                System.out.println("Invalid option.");
            }
        }
}


