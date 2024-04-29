import java.util.ArrayList;
import java.util.List;

// Processor class representing a processing unit in a server
class Processor {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

// Server class representing a server with one or more processors
class Server {
    private int id;
    private List<Processor> processors;

    public Server(int id) {
        this.id = id;
        this.processors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    // Method to add a new processor to the server
    public void addProcessor(Processor processor) {
        processors.add(processor);
    }

    // Method to remove a processor from the server
    public void removeProcessor(int processorId) {
        processors.removeIf(p -> p.getId() == processorId);
    }

    public List<Processor> getProcessors() {
        return processors;
    }
}

// LoadBalancer class representing the load balancer managing multiple servers
class LoadBalancer {
    private List<Server> servers;

    public LoadBalancer() {
        this.servers = new ArrayList<>();
    }

    // Method to add a new server to the load balancer
    public void addServer(Server server) {
        servers.add(server);
    }

    // Method to remove a server from the load balancer
    public void removeServer(int serverId) {
        servers.removeIf(s -> s.getId() == serverId);
    }

    // Method to add a processor to a specific server
    public void addProcessorToServer(int serverId, Processor processor) {
        for (Server server : servers) {
            if (server.getId() == serverId) {
                server.addProcessor(processor);
                break;
            }
        }
    }

    // Method to remove a processor from a specific server
    public void removeProcessorFromServer(int serverId, int processorId) {
        for (Server server : servers) {
            if (server.getId() == serverId) {
                server.removeProcessor(processorId);
                break;
            }
        }
    }

    public List<Server> getServers() {
        return servers;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new instance of LoadBalancer
        LoadBalancer loadBalancer = new LoadBalancer();

        // Create five new servers
        for (int i = 1; i <= 5; i++) {
            Server server = new Server(i);
            loadBalancer.addServer(server);
        }

        // Create ten new processors and add them to servers in round-robin fashion
        for (int i = 1; i <= 10; i++) {
            Processor processor = new Processor(i);
            loadBalancer.addProcessorToServer((i % 5) + 1, processor);
        }

        // Print the servers and their processors after adding processors
        System.out.println("Servers after adding processors:");
        for (Server server : loadBalancer.getServers()) {
            System.out.println("Server " + server.getId() + ": Processors " + server.getProcessors().toString());
        }

        // Remove processor 8 from server 3
        loadBalancer.removeProcessorFromServer(3, 8);

        // Print the servers and their processors after removing processor 8
        System.out.println("Servers after removing processor 8 from server 3:");
        for (Server server : loadBalancer.getServers()) {
            System.out.println("Server " + server.getId() + ": Processors " + server.getProcessors().toString());
        }

        // Remove server 4 from the load balancer
        loadBalancer.removeServer(4);

        // Print the servers and their processors after removing server 4
        System.out.println("Servers after removing server 4:");
        for (Server server : loadBalancer.getServers()) {
            System.out.println("Server " + server.getId() + ": Processors " + server.getProcessors().toString());
        }
    }
}