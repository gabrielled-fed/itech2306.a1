package itech2306.a1.GabrielleDwane;

public class Company {
    private String name;
    private String founder;
    private int founderShares;
    private int sharesAvailable;
    private float sharePrice;
    private int minShares;
    private int maxShares;

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
        return founderShares;
    }

    // add getters as needed
}
