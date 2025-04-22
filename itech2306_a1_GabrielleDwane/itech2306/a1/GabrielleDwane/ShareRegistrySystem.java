package itech2306.a1.GabrielleDwane;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("3. Add Investor");
            System.out.println("4. View Investors of Company");
            System.out.println("5. Declare Dividend");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); // Clear newline from buffer

            switch (choice) {
                case 1 -> addCompany();
                case 2 -> listCompanies();
                case 3 -> addInvestor();
                case 4 -> viewInvestors();
                case 5 -> declareDividend();
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
    
    private void addInvestor() {
        if (companies.isEmpty()) {
            System.out.println("No companies exist yet.");
            return;
        }

        listCompanies();
        System.out.print("Enter company number to invest in: ");
        int index = input.nextInt();
        input.nextLine(); 

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);

        System.out.print("Enter investor's full name: ");
        String name = input.nextLine();

        System.out.print("Enter number of shares to buy: ");
        int numShares = input.nextInt();
        input.nextLine();

        Shareholder investor = new Shareholder(name, numShares);

        if (selected.addInvestor(investor)) {
            System.out.println("Investor added successfully!");
        } else {
            System.out.println("Could not add investor. Check min/max/available shares.");
        }
    }
    
    private void viewInvestors() {
        if (companies.isEmpty()) {
            System.out.println("No companies yet.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number to view its investors: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);
        List<Shareholder> investors = selected.getInvestors();

        if (investors.isEmpty()) {
            System.out.println("This company has no investors.");
            return;
        }

        int totalShares = selected.getTotalSharesIssued();

        System.out.println("Investors for: " + selected.getName());
        for (int i = 0; i < investors.size(); i++) {
            Shareholder s = investors.get(i);
            double percentage = 100.0 * s.getNumShares() / totalShares;
            System.out.printf("%d. %s [%d shares, %.1f%%]%n",
                              i + 1, s.getName(), s.getNumShares(), percentage);
        }
        System.out.println("Total shares issued: " + totalShares);
    }
    
    private void declareDividend() {
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number to declare a dividend: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);
        List<Shareholder> investors = selected.getInvestors();

        if (investors.isEmpty()) {
            System.out.println("This company has no investors.");
            return;
        }

        System.out.print("Enter dividend per share (e.g., 0.25): ");
        float dividendPerShare = input.nextFloat();
        input.nextLine();

        float total = 0;
        System.out.println("Dividend payouts:");
        for (Shareholder s : investors) {
            float payout = s.getNumShares() * dividendPerShare;
            total += payout;
            System.out.printf("• %s: $%.2f%n", s.getName(), payout);
        }

        System.out.printf("Total dividend payout: $%.2f%n", total);
    }
}
