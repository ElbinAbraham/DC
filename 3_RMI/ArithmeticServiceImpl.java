import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithmeticServiceImpl extends UnicastRemoteObject implements ArithmeticService {
    public ArithmeticServiceImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    public double divide(int a, int b) throws RemoteException {
        return (double) a / b;
    }
}

