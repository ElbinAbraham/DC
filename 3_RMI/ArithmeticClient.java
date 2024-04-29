import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ArithmeticClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4321);
            ArithmeticService service = (ArithmeticService) registry.lookup("ArithmeticService");

            // Display menu
            System.out.println("Menu:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.print("Enter your choice: ");

            // Get user choice
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get user input for numbers
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            // Perform selected operation
            int result = 0;
            switch (choice) {
                case 1:
                    result = service.add(num1, num2);
                    break;
                case 2:
                    result = service.subtract(num1, num2);
                    break;
                case 3:
                    result = service.multiply(num1, num2);
                    break;
                case 4:
                    double divisionResult = service.divide(num1, num2);
                    System.out.println("Result: " + divisionResult);
                    return;
                default:
                    System.out.println("Invalid choice");
                    return;
            }

            // Display result
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
