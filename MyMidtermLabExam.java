import java.util.Scanner;

public class MyMidtermLabExam {

    // Maximum number of tickets allowed
    static final int MAX_TICKETS = 5;

    // Arrays to store ticket information
    static String[] issueDescriptions = new String[MAX_TICKETS];
    static String[] urgencyLevels = new String[MAX_TICKETS];
    static String[] statuses = new String[MAX_TICKETS];

    // Counter for the number of tickets added
    static int ticketCount = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                displayMenu();
                System.out.print("Enter your choice: ");
    
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter a number: ");
                    scanner.next(); // clear invalid input
                }
    
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
    
                switch (choice) {
                    case 1 -> addTicket(scanner);
                    case 2 -> updateTicketStatus(scanner);
                    case 3 -> showTickets();
                    case 4 -> generateReport();
                    case 5 -> System.out.println("Exiting program. Thank you for using IT Ticket System!");
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 5);
        }
    }
    

    public static void displayMenu() {
        System.out.println("\n=== IT Ticket Processing System ===");
        System.out.println("1. Add Ticket");
        System.out.println("2. Update Ticket Status");
        System.out.println("3. Show All Tickets");
        System.out.println("4. Generate Report");
        System.out.println("5. Exit");
    }

    public static void addTicket(Scanner scanner) {
        if (ticketCount >= MAX_TICKETS) {
            System.out.println("Maximum ticket limit reached. Cannot add more tickets.");
            return;
        }

        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();

        String urgency;
        do {
            System.out.print("Enter urgency (Low/Medium/High): ");
            urgency = scanner.nextLine();
        } while (!urgency.equalsIgnoreCase("Low") && !urgency.equalsIgnoreCase("Medium") && !urgency.equalsIgnoreCase("High"));

        issueDescriptions[ticketCount] = description;
        urgencyLevels[ticketCount] = urgency.substring(0, 1).toUpperCase() + urgency.substring(1).toLowerCase();
        statuses[ticketCount] = "Pending";
        ticketCount++;

        System.out.println("Ticket added successfully!");
    }

    public static void updateTicketStatus(Scanner scanner) {
        if (ticketCount == 0) {
            System.out.println("No tickets to update.");
            return;
        }

        showTickets();

        System.out.print("Enter ticket number to update (1 to " + ticketCount + "): ");
        int ticketNum = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (ticketNum < 1 || ticketNum > ticketCount) {
            System.out.println("Invalid ticket number.");
            return;
        }

        int index = ticketNum - 1;

        if (statuses[index].equalsIgnoreCase("Resolved")) {
            System.out.println("Cannot update a resolved ticket.");
            return;
        }

        System.out.print("Enter new status (In Progress/Resolved): ");
        String newStatus = scanner.nextLine();

        if (!newStatus.equalsIgnoreCase("In Progress") && !newStatus.equalsIgnoreCase("Resolved")) {
            System.out.println("Invalid status. Update failed.");
            return;
        }

        statuses[index] = newStatus.substring(0, 1).toUpperCase() + newStatus.substring(1).toLowerCase();
        System.out.println("Ticket status updated successfully.");
    }

    public static void showTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets to display.");
            return;
        }

        System.out.println("\n--- All Tickets ---");
        for (int i = 0; i < ticketCount; i++) {
            System.out.printf("Ticket %d: [%s] - Urgency: %s - Status: %s%n",
                    (i + 1), issueDescriptions[i], urgencyLevels[i], statuses[i]);
        }
    }

    public static void generateReport() {
        int pendingCount = 0;
        int resolvedCount = 0;

        for (int i = 0; i < ticketCount; i++) {
            if (statuses[i].equalsIgnoreCase("Resolved")) {
                resolvedCount++;
            } else {
                pendingCount++;
            }
        }

        System.out.println("\n--- Ticket Report ---");
        System.out.println("Total Tickets: " + ticketCount);
        System.out.println("Pending/In Progress Tickets: " + pendingCount);
        System.out.println("Resolved Tickets: " + resolvedCount);
    }
}
