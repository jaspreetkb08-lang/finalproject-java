import java.time.LocalDate;

public class Repayment {

    private int loanId;
    private int emiNumber;
    private double amount;
    private LocalDate paymentDate;

    public Repayment(
            int loanId,
            int emiNumber,
            double amount,
            LocalDate paymentDate) {

        this.loanId = loanId;
        this.emiNumber = emiNumber;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getEmiNumber() {
        return emiNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {

        return "Loan ID: " + loanId
                + " | EMI: " + emiNumber
                + " | Amount: ₹"
                + String.format("%.2f", amount)
                + " | Payment Date: "
                + paymentDate;
    }
}