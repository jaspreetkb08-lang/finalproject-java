import java.time.LocalDate;

public class EMI {

    private int emiNumber;
    private int loanId;
    private double amount;
    private LocalDate dueDate;
    private boolean paid;

    public EMI(
            int emiNumber,
            int loanId,
            double amount,
            LocalDate dueDate) {

        this.emiNumber = emiNumber;
        this.loanId = loanId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = false;
    }

    public int getEmiNumber() {
        return emiNumber;
    }

    public int getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markPaid() {
        paid = true;
    }

    public boolean isOverdue() {
        return !paid && dueDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {

        return "EMI " + emiNumber
                + " | Amount: ₹"
                + String.format("%.2f", amount)
                + " | Due Date: "
                + dueDate
                + " | Status: "
                + (paid ? "PAID" : isOverdue() ? "OVERDUE" : "PENDING");
    }
}