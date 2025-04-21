package itech2306.a1.GabrielleDwane;

public class Shareholder {
    private String name;
    private int numShares;
    // add: votedInCurrentVote, votedYes, dividend tracking, etc.

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

    // add: vote(), receiveDividend(), track voting state
}
