package itech2306.a1.GabrielleDwane;

import java.util.ArrayList;

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
        return null; // Not found
    }

    // add: removeCompany(), listCompanies(), save/load if needed
}

