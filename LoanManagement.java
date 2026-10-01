import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Comparator;

public class LoanManagement {

    // ArrayList - loan applications
    private ArrayList<Loan> loanApplications =
            new ArrayList<>();

    // LinkedList - repayment history
    private LinkedList<Repayment> repaymentHistory =
            new LinkedList<>();

    // HashMap - Loan ID -> Loan
    private HashMap<Integer, Loan> loanMap =
            new HashMap<>();

    // TreeMap - date -> loans
    private TreeMap<LocalDate, List<Loan>> loansByDate =
            new TreeMap<>();

    // ArrayList - customers
    private ArrayList<Customer> customers =
            new ArrayList<>();

    // HashMap - Loan ID -> EMI schedule
    private HashMap<Integer, ArrayList<EMI>> emiSchedules =
            new HashMap<>();

    private int nextCustomerId = 1001;
    private int nextLoanId = 5001;


    // ==================================================
    // CUSTOMER REGISTRATION
    // ==================================================

    public Customer registerCustomer(
            String name,
            String phone,
            String email,
            double income,
            int creditScore) {

        Customer customer =
                new Customer(
                        nextCustomerId,
                        name,
                        phone,
                        email,
                        income,
                        creditScore
                );

        nextCustomerId++;

        customers.add(customer);

        return customer;
    }


    public ArrayList<Customer> getCustomers() {
        return customers;
    }


    // ==================================================
    // LOAN APPLICATION
    // ==================================================

    public Loan applyPersonalLoan(
            Customer customer,
            double amount,
            int tenure) {

        Loan loan =
                new PersonalLoan(
                        nextLoanId,
                        customer,
                        amount,
                        tenure
                );

        nextLoanId++;

        addLoan(loan);

        return loan;
    }


    public Loan applyHomeLoan(
            Customer customer,
            double amount,
            int tenure) {

        Loan loan =
                new HomeLoan(
                        nextLoanId,
                        customer,
                        amount,
                        tenure
                );

        nextLoanId++;

        addLoan(loan);

        return loan;
    }


    public Loan applyVehicleLoan(
            Customer customer,
            double amount,
            int tenure) {

        Loan loan =
                new VehicleLoan(
                        nextLoanId,
                        customer,
                        amount,
                        tenure
                );

        nextLoanId++;

        addLoan(loan);

        return loan;
    }


    // EXTRA FEATURE
    public Loan applyGoldLoan(
            Customer customer,
            double amount,
            int tenure,
            double goldWeight) {

        Loan loan =
                new GoldLoan(
                        nextLoanId,
                        customer,
                        amount,
                        tenure,
                        goldWeight
                );

        nextLoanId++;

        addLoan(loan);

        return loan;
    }


    private void addLoan(Loan loan) {

        loanApplications.add(loan);

        loanMap.put(
                loan.getLoanId(),
                loan
        );
    }


    // ==================================================
    // SEARCH
    // ==================================================

    public Loan searchLoanById(int loanId) {
        return loanMap.get(loanId);
    }


    public ArrayList<Loan> searchLoansByCustomer(
            int customerId) {

        ArrayList<Loan> result =
                new ArrayList<>();

        for (Loan loan : loanApplications) {

            if (
                    loan.getCustomer()
                            .getCustomerId()
                    == customerId
            ) {

                result.add(loan);
            }
        }

        return result;
    }


    // ==================================================
    // LOAN APPROVAL
    // ==================================================

    public boolean approveLoan(int loanId) {

        Loan loan =
                loanMap.get(loanId);

        if (loan == null) {
            return false;
        }

        Customer customer =
                loan.getCustomer();

        if (
                customer.getCreditScore() >= 650
                &&
                customer.getIncome() >= 15000
        ) {

            loan.setStatus("APPROVED");

            return true;

        } else {

            loan.setStatus("REJECTED");

            return false;
        }
    }


    // ==================================================
    // LOAN DISBURSEMENT
    // ==================================================

    public boolean disburseLoan(int loanId) {

        Loan loan =
                loanMap.get(loanId);

        if (loan == null) {
            return false;
        }

        if (!loan.getStatus().equals("APPROVED")) {
            return false;
        }

        LocalDate date =
                LocalDate.now();

        loan.setDisbursementDate(date);
        loan.setStatus("DISBURSED");

        loansByDate
                .computeIfAbsent(
                        date,
                        key -> new ArrayList<>()
                )
                .add(loan);

        generateEMISchedule(loan);

        return true;
    }


    // ==================================================
    // EMI GENERATION
    // ==================================================

    private void generateEMISchedule(
            Loan loan) {

        ArrayList<EMI> schedule =
                new ArrayList<>();

        double emiAmount =
                loan.calculateEMI();

        int totalMonths =
                loan.getTenure() * 12;

        LocalDate firstDueDate =
                LocalDate.now().plusMonths(1);

        for (
                int i = 1;
                i <= totalMonths;
                i++
        ) {

            LocalDate dueDate =
                    firstDueDate.plusMonths(i - 1);

            EMI emi =
                    new EMI(
                            i,
                            loan.getLoanId(),
                            emiAmount,
                            dueDate
                    );

            schedule.add(emi);
        }

        emiSchedules.put(
                loan.getLoanId(),
                schedule
        );
    }


    public ArrayList<EMI> getEMISchedule(
            int loanId) {

        return emiSchedules.get(loanId);
    }


    // ==================================================
    // REPAYMENT
    // ==================================================

    public boolean makePayment(
            int loanId,
            int emiNumber) {

        ArrayList<EMI> schedule =
                emiSchedules.get(loanId);

        if (schedule == null) {
            return false;
        }

        for (EMI emi : schedule) {

            if (emi.getEmiNumber() == emiNumber) {

                if (emi.isPaid()) {
                    return false;
                }

                emi.markPaid();

                Repayment repayment =
                        new Repayment(
                                loanId,
                                emiNumber,
                                emi.getAmount(),
                                LocalDate.now()
                        );

                repaymentHistory.add(
                        repayment
                );

                return true;
            }
        }

        return false;
    }


    // ==================================================
    // OVERDUE DETECTION
    // ==================================================

    public ArrayList<EMI> getOverdueEMIs(
            int loanId) {

        ArrayList<EMI> overdue =
                new ArrayList<>();

        ArrayList<EMI> schedule =
                emiSchedules.get(loanId);

        if (schedule == null) {
            return overdue;
        }

        for (EMI emi : schedule) {

            if (emi.isOverdue()) {
                overdue.add(emi);
            }
        }

        return overdue;
    }


    // ==================================================
    // GET ALL LOANS
    // ==================================================

    public ArrayList<Loan> getAllLoans() {
        return loanApplications;
    }


    public LinkedList<Repayment>
    getRepaymentHistory() {

        return repaymentHistory;
    }


    public TreeMap<LocalDate, List<Loan>>
    getLoansByDate() {

        return loansByDate;
    }


    // ==================================================
    // SORTING
    // ==================================================

    public ArrayList<Loan> sortByAmount() {

        ArrayList<Loan> result =
                new ArrayList<>(loanApplications);

        result.sort(
                Comparator.comparingDouble(
                        Loan::getLoanAmount
                )
        );

        return result;
    }


    public ArrayList<Loan> sortByTenure() {

        ArrayList<Loan> result =
                new ArrayList<>(loanApplications);

        result.sort(
                Comparator.comparingInt(
                        Loan::getTenure
                )
        );

        return result;
    }


    // ==================================================
    // DELETE LOAN
    // ==================================================

    public boolean deleteLoan(int loanId) {

        Loan loan =
                loanMap.remove(loanId);

        if (loan == null) {
            return false;
        }

        loanApplications.remove(loan);

        emiSchedules.remove(loanId);

        return true;
    }


    // ==================================================
    // REPORT
    // ==================================================

    public String generateReport() {

        StringBuilder report =
                new StringBuilder();

        report.append(
                "========== LOAN MANAGEMENT REPORT ==========\n\n"
        );

        report.append(
                "Total Customers: "
        )
        .append(customers.size())
        .append("\n");

        report.append(
                "Total Loans: "
        )
        .append(loanApplications.size())
        .append("\n\n");

        int approved = 0;
        int rejected = 0;
        int disbursed = 0;

        double totalAmount = 0;

        for (Loan loan : loanApplications) {

            totalAmount += loan.getLoanAmount();

            if (loan.getStatus().equals("APPROVED")) {
                approved++;
            }

            if (loan.getStatus().equals("REJECTED")) {
                rejected++;
            }

            if (loan.getStatus().equals("DISBURSED")) {
                disbursed++;
            }
        }

        report.append(
                "Approved Loans: "
        )
        .append(approved)
        .append("\n");

        report.append(
                "Rejected Loans: "
        )
        .append(rejected)
        .append("\n");

        report.append(
                "Disbursed Loans: "
        )
        .append(disbursed)
        .append("\n");

        report.append(
                "Total Loan Value: ₹"
        )
        .append(
                String.format(
                        "%.2f",
                        totalAmount
                )
        )
        .append("\n\n");


        for (Loan loan : loanApplications) {

            report.append(
                    "Loan ID: "
            )
            .append(loan.getLoanId())
            .append(" | Customer: ")
            .append(loan.getCustomer().getName())
            .append(" | Type: ")
            .append(loan.getLoanType())
            .append(" | Amount: ₹")
            .append(
                    String.format(
                            "%.2f",
                            loan.getLoanAmount()
                    )
            )
            .append(" | Status: ")
            .append(loan.getStatus())
            .append("\n");
        }

        return report.toString();
    }


    // ==================================================
    // DASHBOARD - EXTRA FEATURE
    // ==================================================

    public String getDashboard() {

        int overdueCount = 0;

        for (Loan loan : loanApplications) {

            ArrayList<EMI> schedule =
                    emiSchedules.get(
                            loan.getLoanId()
                    );

            if (schedule == null) {
                continue;
            }

            for (EMI emi : schedule) {

                if (emi.isOverdue()) {
                    overdueCount++;
                }
            }
        }

        return
                "========== DASHBOARD ==========\n\n"
                + "Total Customers: "
                + customers.size()
                + "\n"
                + "Total Loans: "
                + loanApplications.size()
                + "\n"
                + "Overdue EMIs: "
                + overdueCount;
    }
}