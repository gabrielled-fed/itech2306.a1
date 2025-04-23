package itech2306.a1.GabrielleDwane;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Company in the share registry system.
 * Stores information about its name, founder, share limits, investors,
 * and any current voting activity. Includes methods to manage investors,
 * voting, and reporting.
 */

public class Company {
    private String name;
    private String founder;
    private int sharesAvailable;
    private float sharePrice;
    private int minShares;
    private int maxShares;
    
    private List<Shareholder> investors = new ArrayList<>();
    
    private String currentVoteTopic = null;

    /**
     * Constructs a new Company with all relevant attributes.
     */
    public Company(String name, String founder, int founderShares, int sharesAvailable,
            float sharePrice, int minShares, int maxShares) {
 this.name = name;
 this.founder = founder;
 this.sharesAvailable = sharesAvailable;
 this.sharePrice = sharePrice;
 this.minShares = minShares;
 this.maxShares = maxShares;
}

    public String getName() {
        return name;
    }

    public int getTotalSharesIssued() {
        return investors.stream().mapToInt(Shareholder::getNumShares).sum();
    }
    
    public void addFounder(Shareholder s) {
        investors.add(s); // Founder bypasses min/max share limits
    }
    
    public boolean addInvestor(Shareholder s) {
        int requestedShares = s.getNumShares();
        
     // Must meet company rules and not exceed available shares
        if (requestedShares >= minShares &&
            requestedShares <= maxShares &&
            requestedShares <= sharesAvailable) {

            investors.add(s);
            sharesAvailable -= requestedShares;
            return true;
        }
        return false;
    }

    public List<Shareholder> getInvestors() {
        return new ArrayList<>(investors); 
    }

    public void startNewVote(String topic) {
        currentVoteTopic = topic;
        
     // Reset vote state before new vote begins
        for (Shareholder s : investors) {
            s.resetVote();
        }
    }

    public String getCurrentVoteTopic() {
        return currentVoteTopic;
    }

    public void endCurrentVote() {
        int yesCount = 0;
        int noCount = 0;

        System.out.println("Vote Results for topic: " + currentVoteTopic);
        for (Shareholder s : investors) {
            if (s.hasVoted()) {
                String result = s.votedYes() ? "YES" : "NO";
                System.out.println("- " + s.getName() + ": " + result);
                if (s.votedYes()) yesCount += s.getNumShares();
                else noCount += s.getNumShares();
            }
        }

        System.out.println("Total YES votes (by shares): " + yesCount);
        System.out.println("Total NO votes (by shares): " + noCount);

        currentVoteTopic = null; // Clear vote after reporting
    }
    
    public String getFounder() {
        return founder;
    }

    public float getSharePrice() {
        return sharePrice;
    }
}
