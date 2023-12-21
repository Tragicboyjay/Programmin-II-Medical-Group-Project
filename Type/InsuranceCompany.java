package Type;
public enum InsuranceCompany {
    COMPANY_A("Company A", 85),
    COMPANY_B("Company B", 90),
    COMPANY_C("Company C", 80);

    private final String companyName;
    private final double coverRate;

    InsuranceCompany(String companyName, double coverRate) {
        this.companyName = companyName;
        this.coverRate = coverRate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCoverRate() {
        return coverRate;
    }
}