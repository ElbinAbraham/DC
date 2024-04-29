import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ArithmeticServer {
    public static void main(String[] args) {
        try {
            ArithmeticService service = new ArithmeticServiceImpl();
            Registry registry = LocateRegistry.createRegistry(4321);
            registry.rebind("ArithmeticService", service);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
