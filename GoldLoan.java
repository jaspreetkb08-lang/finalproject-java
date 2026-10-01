public class GoldLoan extends Loan {

    private double goldWeight;

    public GoldLoan(
            int loanId,
            Customer customer,
            double loanAmount,
            int tenure,
            double goldWeight) {

        super(loanId, customer, loanAmount, tenure);

        if (goldWeight <= 0) {
            throw new IllegalArgumentException(
                    "Gold weight must be greater than 0."
            );
        }

        this.goldWeight = goldWeight;
    }

    public double getGoldWeight() {
        return goldWeight;
    }

    @Override
    public double calculateInterest() {
        return 10.0;
    }

    @Override
    public String getLoanType() {
        return "Gold Loan";
    }
}