public enum InsuranceCompany {
    COMPANY_A("Company A", 0.85),
    COMPANY_B("Company B", 0.90),
    COMPANY_C("Company C", 0.80);

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