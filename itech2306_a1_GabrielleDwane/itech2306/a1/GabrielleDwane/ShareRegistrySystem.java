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

        System.out.print("Enter founder's name: ");
        String founder = input.nextLine();

        System.out.print("How many shares are issued to the founder? ");
        int founderShares = input.nextInt();

        System.out.print("How many additional shares can be issued to others? ");
        int sharesAvailable = input.nextInt();

        System.out.print("What is the share price? ");
        float sharePrice = input.nextFloat();

        System.out.print("What is the minimum shares per investor? ");
        int minShares = input.nextInt();

        System.out.print("What is the maximum shares per investor? ");
        int maxShares = input.nextInt();
        input.nextLine(); // clear buffer

        Company company = new Company(name, founder, founderShares, sharesAvailable, sharePrice, minShares, maxShares);
        companies.add(company);

        System.out.println("Company added successfully!");
    }

    private void listCompanies() {
        if (companies.isEmpty()) {
            System.out.println("No companies yet.");
        } else {
            for (int i = 0; i < companies.size(); i++) {
                Company c = companies.get(i);
                System.out.println((i + 1) + ". " + c.getName() +
                                   " [" + c.getTotalSharesIssued() + " shares issued]");
            }
        }
    }
}
