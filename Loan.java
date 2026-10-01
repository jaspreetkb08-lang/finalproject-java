import java.time.LocalDate;

public abstract class Loan {

    protected int loanId;
    protected Customer customer;
    protected double loanAmount;
    protected int tenure;
    protected String status;
    protected LocalDate disbursementDate;

    public Loan(
            int loanId,
            Customer customer,
            double loanAmount,
            int tenure) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        if (loanAmount <= 0) {
            throw new IllegalArgumentException(
                    "Loan amount must be greater than 0."
            );
        }

        if (tenure <= 0) {
            throw new IllegalArgumentException(
                    "Tenure must be greater than 0."
            );
        }

        this.loanId = loanId;
        this.customer = customer;
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.status = "PENDING";
        this.disbursementDate = null;
    }

    public abstract double calculateInterest();

    public abstract String getLoanType();

    public double calculateEMI() {

        double annualRate = calculateInterest();
        double monthlyRate = annualRate / 12 / 100;
        int months = tenure * 12;

        if (monthlyRate == 0) {
            return loanAmount / months;
        }

        return loanAmount
                * monthlyRate
                * Math.pow(1 + monthlyRate, months)
                / (Math.pow(1 + monthlyRate, months) - 1);
    }

    public int getLoanId() {
        return loanId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDisbursementDate() {
        return disbursementDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDisbursementDate(LocalDate disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    @Override
    public String toString() {

        return "Loan ID: " + loanId
                + "\nCustomer: " + customer.getName()
                + "\nType: " + getLoanType()
                + "\nAmount: ₹" + String.format("%.2f", loanAmount)
                + "\nTenure: " + tenure + " years"
                + "\nInterest Rate: " + calculateInterest() + "%"
                + "\nMonthly EMI: ₹"
                + String.format("%.2f", calculateEMI())
                + "\nStatus: " + status
                + "\nDisbursement Date: "
                + (disbursementDate == null ? "Not Disbursed" : disbursementDate);
    }
}