package itech2306.a1.GabrielleDwane;

import java.util.List;
import java.util.ArrayList;

public class Company {
    private String name;
    private String founder;
    private int founderShares;
    private int sharesAvailable;
    private float sharePrice;
    private int minShares;
    private int maxShares;
    
    private List<Shareholder> investors = new ArrayList<>();
    
    private String currentVoteTopic = null;

    public Company(String name, String founder, int founderShares, int sharesAvailable,
            float sharePrice, int minShares, int maxShares) {
 this.name = name;
 this.founder = founder;
 this.founderShares = founderShares;
 this.sharesAvailable = sharesAvailable;
 this.sharePrice = sharePrice;
 this.minShares = minShares;
 this.maxShares = maxShares;
}

    public String getName() {
        return name;
    }

    public int getTotalSharesIssued() {
        int investorTotal = investors.stream().mapToInt(Shareholder::getNumShares).sum();
        return founderShares + investorTotal;
    }
    
    public boolean addInvestor(Shareholder s) {
        int requestedShares = s.getNumShares();
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
        for (Shareholder s : investors) {
            s.resetVote(); // Clear previous vote state
        }
    }

    public String getCurrentVoteTopic() {
        return currentVoteTopic;
    }

    // add getters as needed
}
