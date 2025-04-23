package itech2306.a1.GabrielleDwane;

/**
 * Represents an individual shareholder who owns shares in a company.
 * Tracks name, number of shares owned, and their voting state.
 * Used for vote recording and dividend calculation.
 */

public class Shareholder {
    private String name;
    private int numShares;
    
 // Flags to track the shareholder's vote status during an active vote
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
    
    // Check if this shareholder has already voted in the current vote
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

    /**
     * Resets the voting state so the shareholder can vote again
     * in a future voting round.
     */
    public void resetVote() {
        hasVoted = false;
        votedYes = false;
    }

}
