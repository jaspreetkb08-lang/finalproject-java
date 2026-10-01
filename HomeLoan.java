public class HomeLoan extends Loan {

    public HomeLoan(
            int loanId,
            Customer customer,
            double loanAmount,
            int tenure) {

        super(loanId, customer, loanAmount, tenure);
    }

    @Override
    public double calculateInterest() {
        return 8.5;
    }

    @Override
    public String getLoanType() {
        return "Home Loan";
    }
}