public class PersonalLoan extends Loan {

    public PersonalLoan(
            int loanId,
            Customer customer,
            double loanAmount,
            int tenure) {

        super(loanId, customer, loanAmount, tenure);
    }

    @Override
    public double calculateInterest() {
        return 12.0;
    }

    @Override
    public String getLoanType() {
        return "Personal Loan";
    }
}