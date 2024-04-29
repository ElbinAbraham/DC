import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BullyElec {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        ArrayList<Boolean> processes = new ArrayList<Boolean>();
        int prev_request = -1;
        for (int i = 0; i < n; i++) {
            processes.add(new Random().nextBoolean());
        }
        processes.set(n - 1, false);
        System.out.println("\nCo-ordinator Process: " + n + "\nCo-ordinator Process Now Dead: " + n);
        int request = new Random().nextInt(n - 1);
        System.out.println(processes);
        System.out.println(request);
        while (request < n - 1 && prev_request != request) {
            System.out.println("\n\nRequesting Process : " + (request + 1));
            for (int i = request + 1; i < n; i++) {
                System.out.println("\n\t" + (request + 1) + " === Election ===> " + (i + 1));
            }
            System.out.println();
            prev_request = request;
            request = bully(request, n, processes);
        }
        System.out.println("\n\nElected Co-ordinator Process : " + (request + 1) + "\nSending Message To All Other Process...");
        for (int i = 0; i < request; i++) {
            System.out.println("\n\tProcess " + (request + 1) + " ==== Co-Ordinator ===> " + (i + 1));
        }
        System.out.println("\nAll Messages Sent!!!");
    }

    public static int bully(int request, int n, ArrayList<Boolean> processes) {
        for (int j = request + 1; j < n; j++) {
            if (processes.get(j)) {
                System.out.println("\n\tReply from process: " + (j + 1) + " OK");
                request = j;
                break;
            }
        }
        return request;
    }
}
