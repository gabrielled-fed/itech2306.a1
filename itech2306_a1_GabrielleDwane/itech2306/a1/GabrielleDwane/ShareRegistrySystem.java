package itech2306.a1.GabrielleDwane;

import java.util.Scanner;
import java.util.ArrayList;

public class ShareRegistrySystem {
    private ArrayList<Company> companies = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // Main program loop
    public void run() {
        int choice;
        do {
            System.out.println("\n--- Share Registry Menu ---");
            System.out.println("1. Add Company");
            System.out.println("2. List Companies");
            // Future options:
            // 3. Add Investor
            // 4. Declare Dividend
            // 5. Start Vote, etc.
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); // Clear newline from buffer

            switch (choice) {
                case 1 -> addCompany();
                case 2 -> listCompanies();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void addCompany() {
        System.out.print("Enter company name: ");
        String name = input.nextLine();

        // add: prompt for founder name, share price, min/max shares
        Company c = new Company(name);
        companies.add(c);

        System.out.println("Company added.");
    }

    private void listCompanies() {
        if (companies.isEmpty()) {
            System.out.println("No companies yet.");
        } else {
            for (Company c : companies) {
                System.out.println("- " + c.getName());
                // add: show # of shares, founder, etc.
            }
        }
    }
}
