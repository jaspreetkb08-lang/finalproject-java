public class VehicleLoan extends Loan {

    public VehicleLoan(
            int loanId,
            Customer customer,
            double loanAmount,
            int tenure) {

        super(loanId, customer, loanAmount, tenure);
    }

    @Override
    public double calculateInterest() {
        return 9.5;
    }

    @Override
    public String getLoanType() {
        return "Vehicle Loan";
    }
}