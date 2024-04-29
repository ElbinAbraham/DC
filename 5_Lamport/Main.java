import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= numProcesses; i++) {
            processes.add(new Process(i));
        }

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleInternalEvent(processes);
                    break;
                case 2:
                    handleMessageEvent(processes);
                    break;
                case 3:
                    displayProcessClocks(processes);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nLamport's Clock Algorithm Menu:");
        System.out.println("1. Internal Event");
        System.out.println("2. Message Event");
        System.out.println("3. Display Process Clocks");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleInternalEvent(List<Process> processes) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter process ID (1-" + processes.size() + "): ");
        int processId = scanner.nextInt();

        if (processId < 1 || processId > processes.size()) {
            System.out.println("Invalid process ID.");
            return;
        }

        Process process = processes.get(processId - 1);
        process.incrementClock();
        System.out.println("Internal event occurred on process " + process.id + ".");
    }

    private static void handleMessageEvent(List<Process> processes) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sender process ID (1-" + processes.size() + "): ");
        int senderId = scanner.nextInt();
        System.out.print("Enter recipient process ID (1-" + processes.size() + "): ");
        int recipientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter message content: ");
        String message = scanner.nextLine();

        if (senderId < 1 || senderId > processes.size() ||
                recipientId < 1 || recipientId > processes.size()) {
            System.out.println("Invalid process ID(s).");
            return;
        }

        Process sender = processes.get(senderId - 1);
        Process recipient = processes.get(recipientId - 1);
        sender.sendEvent(recipient, message);
        System.out.println("Message sent from process " + sender.id + " to process " + recipient.id + ".");
    }

    private static void displayProcessClocks(List<Process> processes) {
        System.out.println("\nProcess Clocks:");
        // for (Process process : processes) {
        for (int i=0;i<processes.size();i++) {
            Process process = processes.get(i);
            System.out.println("Process " + process.id + ": " + process.getClock());
        }
    }
}


class Process {

    public final int id;
    private int clock;

    public Process(int id) {
        this.id = id;
        this.clock = 0;
    }

    public void incrementClock() {
        clock++;
    }

    public int getClock() {
        return clock;
    }

    public void sendEvent(Process recipient, String message) {
        incrementClock();
        Message msg = new Message(this.id, recipient.id, clock, message);
        recipient.receiveEvent(msg);
    }

    public void receiveEvent(Message message) {
        incrementClock();
        clock = Math.max(clock, message.getTimestamp() + 1);
        // Simulate processing the message (replace with your logic)
        System.out.println("Process " + id + " received message: " + message.getMessage() + " from process " + message.getSenderId());
    }
}

class Message {

    private final int senderId;
    private final int recipientId;
    private final int timestamp;
    private final String message;

    public Message(int senderId, int recipientId, int timestamp, String message) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.timestamp = timestamp;
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
