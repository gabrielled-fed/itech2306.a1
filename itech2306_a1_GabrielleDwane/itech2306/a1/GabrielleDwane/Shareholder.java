package itech2306.a1.GabrielleDwane;

public class Shareholder {
    private String name;
    private int numShares;
    
    private boolean hasVoted = false;
    private boolean votedYes = false;


    public Shareholder(String name, int numShares) {
        this.name = name;
        this.numShares = numShares;
    }

    public String getName() {
        return name;
    }

    public int getNumShares() {
        return numShares;
    }
    
    public boolean hasVoted() {
        return hasVoted;
    }

    public boolean votedYes() {
        return votedYes;
    }

    public void vote(boolean inFavour) {
        votedYes = inFavour;
        hasVoted = true;
    }

    public void resetVote() {
        hasVoted = false;
        votedYes = false;
    }

}
