import java.util.*;

public class RicartAgrawala {
    private static int[] timestamps = new int[3];
    private static boolean[] replies = new boolean[3];
    private static boolean[] usingResource = new boolean[3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of requests:");
        int numRequests = scanner.nextInt();
        int[] requests = new int[numRequests];
        for (int i = 0; i < numRequests; i++) {
            System.out.println("Enter process (0, 1, or 2) requesting the resource:");
            requests[i] = scanner.nextInt();
        }
        for (int i = 0; i < numRequests; i++) {
            request(requests[i]);
        }
        scanner.close();
    }

    private static void request(int processId) {
        timestamps[processId] = new Random().nextInt(1000);
        // Send request to all other processes
        for (int i = 0; i < 3; i++) {
            if (i != processId) {
                sendRequest(processId, i);
            }
        }
        // Wait for replies from all other processes
        while (!allReplies(processId)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Enter critical section
        enterCriticalSection(processId);
        // Release resource
        usingResource[processId] = false;
        System.out.println("Process P" + processId + " released the resource.");
        // Reset replies for next request
        Arrays.fill(replies, false);
    }

    private static void sendRequest(int sender, int receiver) {
        System.out.println("Process P" + sender + " is requesting the resource from P" +
                receiver + ".");
        // Simulate sending request message
        receiveRequest(sender, receiver);
    }

    private static void receiveRequest(int sender, int receiver) {
        // Simulate receiving request message
        if (!usingResource[receiver]) {
            replies[receiver] = true;
        } else if (timestamps[sender] < timestamps[receiver]) {
            replies[receiver] = true;
        } else {
            // Defer request
        }
    }

    private static boolean allReplies(int processId) {
        for (int i = 0; i < 3; i++) {
            if (i != processId && !replies[i]) {
                return false;
            }
        }
        return true;
    }

    private static void enterCriticalSection(int processId) {
        usingResource[processId] = true;
        System.out.println("Process P" + processId + " is using the resource.");
    }
}