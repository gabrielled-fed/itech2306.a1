package itech2306.a1.GabrielleDwane;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class responsible for storing and retrieving companies.
 * Provides a controlled way to access and manage the list of companies
 * without exposing the internal data structure directly.
 */

public class CompanyDatabase {
    private ArrayList<Company> companies = new ArrayList<>();

    public void addCompany(Company c) {
        companies.add(c);
    }

    public Company getCompanyByName(String name) {
        for (Company c : companies) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null; 
    }
    
    // Check if there's space to add another company (max 6)
    public boolean canAddMoreCompanies() {
        return companies.size() < 6;
    }
    
    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies); // Defensive copy for encapsulation
    }

}

