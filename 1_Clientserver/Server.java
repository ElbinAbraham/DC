import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a ServerSocket that listens on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server listening on port 12345");

            // Accept client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Set up input and output streams for the client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read and process mathematical expressions from the client
            String expression;
            while ((expression = in.readLine()) != null) {
                System.out.println("Received from client: " + expression);

                // Perform mathematical operations
                try {
                    String result = evaluateExpression(expression);
                    out.println("Result: " + result);
                } catch (ArithmeticException e) {
                    out.println("Error: " + e.getMessage());
                }
            }

            // Close the connections
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to evaluate mathematical expressions
    private static String evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid expression format");
        }

        double operand1 = Double.parseDouble(tokens[0]);
        double operand2 = Double.parseDouble(tokens[2]);
        double result;

        switch (tokens[1]) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + tokens[1]);
        }

        return String.valueOf(result);
    }
}
