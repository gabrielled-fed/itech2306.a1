package itech2306.a1.GabrielleDwane;

import java.util.Scanner;
import java.util.List;

/**
 * Acts as the controller for user interaction.
 * Displays the menu, processes user input, and coordinates actions
 * like creating companies, adding investors, handling votes, and showing reports.
 */

public class ShareRegistrySystem {
	private CompanyDatabase database = new CompanyDatabase(); // Handles data storage/retrieval
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
            System.out.println("6. Start a Vote");
            System.out.println("7. Record Shareholder Vote");
            System.out.println("8. End Vote and Show Results");
            System.out.println("9. View Full Company Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); 

            // Menu dispatch
            switch (choice) {
                case 1 -> addCompany();
                case 2 -> listCompanies();
                case 3 -> addInvestor();
                case 4 -> viewInvestors();
                case 5 -> declareDividend();
                case 6 -> startVote();
                case 7 -> recordVote();
                case 8 -> endVote();
                case 9 -> viewCompanyReport();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    // COMPANY
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
        input.nextLine(); 

        Company company = new Company(name, founder, founderShares, sharesAvailable, sharePrice, minShares, maxShares);

        Shareholder founderInvestor = new Shareholder(founder, founderShares);
        company.addFounder(founderInvestor);

        database.addCompany(company);
        System.out.println("Company added successfully!");
    }

    private void listCompanies() {
    	List<Company> companies = database.getAllCompanies();
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
    
    // INVESTORS
    private void addInvestor() {
    	List<Company> companies = database.getAllCompanies();
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
    	List<Company> companies = database.getAllCompanies();
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
    	List<Company> companies = database.getAllCompanies();
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
    
    // VOTING BELOW 
    private void startVote() {
    	List<Company> companies = database.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies exist.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number to start a vote: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);

        System.out.print("Enter the topic for shareholders to vote on: ");
        String topic = input.nextLine();

        selected.startNewVote(topic);
        System.out.println("Vote topic set: \"" + topic + "\"");
    }
    
    private void recordVote() {
    	List<Company> companies = database.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid company.");
            return;
        }

        Company selected = companies.get(index - 1);
        String topic = selected.getCurrentVoteTopic();

        if (topic == null) {
            System.out.println("No vote topic is currently set for this company.");
            return;
        }

        List<Shareholder> investors = selected.getInvestors();

        if (investors.isEmpty()) {
            System.out.println("No shareholders to vote.");
            return;
        }

        System.out.println("Vote topic: " + topic);

        for (int i = 0; i < investors.size(); i++) {
            Shareholder s = investors.get(i);
            System.out.println((i + 1) + ". " + s.getName() +
                    (s.hasVoted() ? " (Already Voted)" : ""));
        }

        System.out.print("Choose a shareholder number to vote: ");
        int sIndex = input.nextInt();
        input.nextLine();

        if (sIndex < 1 || sIndex > investors.size()) {
            System.out.println("Invalid shareholder.");
            return;
        }

        Shareholder voter = investors.get(sIndex - 1);

        if (voter.hasVoted()) {
            System.out.println("This shareholder has already voted.");
            return;
        }

        System.out.print("Vote yes or no? (y/n): ");
        String vote = input.nextLine().trim().toLowerCase();

        if (vote.equals("y")) {
            voter.vote(true);
            System.out.println("Vote recorded: YES");
        } else if (vote.equals("n")) {
            voter.vote(false);
            System.out.println("Vote recorded: NO");
        } else {
            System.out.println("Invalid vote input.");
        }
    }
    
    private void endVote() {
    	List<Company> companies = database.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);
        if (selected.getCurrentVoteTopic() == null) {
            System.out.println("No active vote to end.");
            return;
        }

        selected.endCurrentVote();  
    }
    
    // COMPANY REPORT
    private void viewCompanyReport() {
    	List<Company> companies = database.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        listCompanies();
        System.out.print("Select a company number to view report: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(index - 1);
        int totalShares = selected.getTotalSharesIssued();
        List<Shareholder> investors = selected.getInvestors();

        System.out.println("\n--- Company Report: " + selected.getName() + " ---");
        System.out.println("Founder: " + selected.getFounder());
        System.out.println("Share Price: $" + selected.getSharePrice());
        System.out.println("Total Shares Issued: " + totalShares);

        if (investors.isEmpty()) {
            System.out.println("No investors yet.");
        } else {
            System.out.println("Shareholders:");
            for (Shareholder s : investors) {
                int shares = s.getNumShares();
                double percent = 100.0 * shares / totalShares;
                System.out.printf("- %s: %d shares (%.2f%%)%n", s.getName(), shares, percent);
            }
        }
    }
}
