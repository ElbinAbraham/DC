import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost and port 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server");
            System.out.println("Enter the arithmetic operation");

            // Set up input and output streams for the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send mathematical expressions to the server and receive results
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String expression;
            while ((expression = userInput.readLine()) != null) {
                out.println(expression);
                String result = in.readLine();
                System.out.println("Server response: " + result);
            }

            // Close the connections
            userInput.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
