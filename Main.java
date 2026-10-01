import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    private static LoanManagement system =
            new LoanManagement();

    private static JFrame frame;

    private static JTextArea output;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> createGUI()
        );
    }


    private static void createGUI() {

        frame = new JFrame(
                "Loan Application & EMI Management System"
        );

        frame.setSize(900, 700);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);


        JLabel title =
                new JLabel(
                        "LOAN APPLICATION & EMI MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );


        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                10,
                                10
                        )
                );


        JButton customerButton =
                new JButton("1. Customer Registration");

        JButton loanButton =
                new JButton("2. Loan Application");

        JButton approvalButton =
                new JButton("3. Loan Approval");

        JButton emiButton =
                new JButton("4. EMI Schedule");

        JButton paymentButton =
                new JButton("5. Repayment Tracking");

        JButton overdueButton =
                new JButton("6. Overdue Detection");

        JButton searchButton =
                new JButton("7. Loan Search");

        JButton reportButton =
                new JButton("8. Report Generation");

        JButton goldButton =
                new JButton("9. Gold Loan - Extra");

        JButton allLoansButton =
                new JButton("10. View All Loans");


        buttonPanel.add(customerButton);
        buttonPanel.add(loanButton);
        buttonPanel.add(approvalButton);
        buttonPanel.add(emiButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(overdueButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(goldButton);
        buttonPanel.add(allLoansButton);


        output =
                new JTextArea();

        output.setEditable(false);

        output.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(output);


        customerButton.addActionListener(
                e -> registerCustomer()
        );

        loanButton.addActionListener(
                e -> applyLoan()
        );

        approvalButton.addActionListener(
                e -> approveLoan()
        );

        emiButton.addActionListener(
                e -> showEMI()
        );

        paymentButton.addActionListener(
                e -> makePayment()
        );

        overdueButton.addActionListener(
                e -> showOverdue()
        );

        searchButton.addActionListener(
                e -> searchLoan()
        );

        reportButton.addActionListener(
                e -> showReport()
        );

        goldButton.addActionListener(
                e -> applyGoldLoan()
        );

        allLoansButton.addActionListener(
                e -> showAllLoans()
        );


        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.add(
                title,
                BorderLayout.CENTER
        );

        top.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        frame.add(
                top,
                BorderLayout.NORTH
        );

        frame.add(
                scrollPane,
                BorderLayout.CENTER
        );


        frame.setVisible(true);
    }


    // ==================================================
    // 1. CUSTOMER REGISTRATION
    // ==================================================

    private static void registerCustomer() {

        try {

            String name =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter customer name:"
                    );

            if (name == null) return;


            String phone =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter phone:"
                    );

            if (phone == null) return;


            String email =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter email:"
                    );

            if (email == null) return;


            double income =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter monthly income:"
                            )
                    );


            int creditScore =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter credit score:"
                            )
                    );


            Customer customer =
                    system.registerCustomer(
                            name,
                            phone,
                            email,
                            income,
                            creditScore
                    );


            output.setText(
                    "CUSTOMER REGISTERED SUCCESSFULLY\n\n"
                    + customer
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 2. LOAN APPLICATION
    // ==================================================

    private static void applyLoan() {

        try {

            if (system.getCustomers().isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please register a customer first."
                );

                return;
            }


            int customerId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Customer ID:"
                            )
                    );


            Customer customer =
                    findCustomer(customerId);


            if (customer == null) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer not found."
                );

                return;
            }


            String[] types = {
                    "Personal Loan",
                    "Home Loan",
                    "Vehicle Loan"
            };


            String type =
                    (String) JOptionPane.showInputDialog(
                            frame,
                            "Select loan type:",
                            "Loan Type",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            types,
                            types[0]
                    );


            if (type == null) return;


            double amount =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter loan amount:"
                            )
                    );


            int tenure =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter tenure in years:"
                            )
                    );


            Loan loan;


            if (type.equals("Personal Loan")) {

                loan =
                        system.applyPersonalLoan(
                                customer,
                                amount,
                                tenure
                        );

            } else if (
                    type.equals("Home Loan")
            ) {

                loan =
                        system.applyHomeLoan(
                                customer,
                                amount,
                                tenure
                        );

            } else {

                loan =
                        system.applyVehicleLoan(
                                customer,
                                amount,
                                tenure
                        );
            }


            output.setText(
                    "LOAN APPLICATION SUCCESSFUL\n\n"
                    + loan
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 3. APPROVAL
    // ==================================================

    private static void approveLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Loan ID:"
                            )
                    );


            Loan loan =
                    system.searchLoanById(
                            loanId
                    );


            if (loan == null) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Loan not found."
                );

                return;
            }


            boolean approved =
                    system.approveLoan(
                            loanId
                    );


            if (approved) {

                output.setText(
                        "LOAN APPROVED\n\n"
                        + loan
                );

            } else {

                output.setText(
                        "LOAN REJECTED\n\n"
                        + loan
                );
            }


            if (approved) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                frame,
                                "Loan approved.\nDisburse loan now?",
                                "Disbursement",
                                JOptionPane.YES_NO_OPTION
                        );


                if (
                        choice
                        == JOptionPane.YES_OPTION
                ) {

                    system.disburseLoan(
                            loanId
                    );

                    output.setText(
                            "LOAN DISBURSED\n\n"
                            + loan
                    );
                }
            }


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 4. EMI SCHEDULE
    // ==================================================

    private static void showEMI() {

        try {

            int loanId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Loan ID:"
                            )
                    );


            ArrayList<EMI> schedule =
                    system.getEMISchedule(
                            loanId
                    );


            if (schedule == null) {

                output.setText(
                        "No EMI schedule found.\n"
                        + "Loan may not be disbursed yet."
                );

                return;
            }


            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "========== EMI SCHEDULE ==========\n\n"
            );


            for (EMI emi : schedule) {

                result.append(
                        emi
                )
                .append("\n");
            }


            output.setText(
                    result.toString()
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 5. REPAYMENT
    // ==================================================

    private static void makePayment() {

        try {

            int loanId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Loan ID:"
                            )
                    );


            int emiNumber =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter EMI Number:"
                            )
                    );


            boolean success =
                    system.makePayment(
                            loanId,
                            emiNumber
                    );


            if (success) {

                output.setText(
                        "PAYMENT SUCCESSFUL\n\n"
                        + "Loan ID: "
                        + loanId
                        + "\nEMI Number: "
                        + emiNumber
                );

            } else {

                output.setText(
                        "PAYMENT FAILED.\n"
                        + "Check Loan ID or EMI Number."
                );
            }


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 6. OVERDUE
    // ==================================================

    private static void showOverdue() {

        try {

            int loanId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Loan ID:"
                            )
                    );


            ArrayList<EMI> overdue =
                    system.getOverdueEMIs(
                            loanId
                    );


            if (overdue.isEmpty()) {

                output.setText(
                        "No overdue EMI found."
                );

                return;
            }


            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "========== OVERDUE EMIs ==========\n\n"
            );


            for (EMI emi : overdue) {

                result.append(
                        emi
                )
                .append("\n");
            }


            output.setText(
                    result.toString()
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 7. SEARCH
    // ==================================================

    private static void searchLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Loan ID:"
                            )
                    );


            Loan loan =
                    system.searchLoanById(
                            loanId
                    );


            if (loan == null) {

                output.setText(
                        "Loan not found."
                );

            } else {

                output.setText(
                        "========== LOAN DETAILS ==========\n\n"
                        + loan
                );
            }


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 8. REPORT
    // ==================================================

    private static void showReport() {

        output.setText(
                system.generateReport()
        );
    }


    // ==================================================
    // 9. GOLD LOAN EXTRA FEATURE
    // ==================================================

    private static void applyGoldLoan() {

        try {

            if (system.getCustomers().isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Register customer first."
                );

                return;
            }


            int customerId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter Customer ID:"
                            )
                    );


            Customer customer =
                    findCustomer(customerId);


            if (customer == null) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer not found."
                );

                return;
            }


            double amount =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter gold loan amount:"
                            )
                    );


            int tenure =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter tenure in years:"
                            )
                    );


            double goldWeight =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter gold weight in grams:"
                            )
                    );


            Loan loan =
                    system.applyGoldLoan(
                            customer,
                            amount,
                            tenure,
                            goldWeight
                    );


            output.setText(
                    "GOLD LOAN APPLICATION SUCCESSFUL\n\n"
                    + loan
                    + "\nGold Weight: "
                    + goldWeight
                    + " grams"
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // ==================================================
    // 10. VIEW ALL LOANS
    // ==================================================

    private static void showAllLoans() {

        ArrayList<Loan> loans =
                system.getAllLoans();


        if (loans.isEmpty()) {

            output.setText(
                    "No loans available."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "========== ALL LOANS ==========\n\n"
        );


        for (Loan loan : loans) {

            result.append(
                    "Loan ID: "
            )
            .append(
                    loan.getLoanId()
            )
            .append(
                    "\nCustomer: "
            )
            .append(
                    loan.getCustomer().getName()
            )
            .append(
                    "\nType: "
            )
            .append(
                    loan.getLoanType()
            )
            .append(
                    "\nAmount: ₹"
            )
            .append(
                    String.format(
                            "%.2f",
                            loan.getLoanAmount()
                    )
            )
            .append(
                    "\nStatus: "
            )
            .append(
                    loan.getStatus()
            )
            .append(
                    "\n------------------------------\n"
            );
        }


        output.setText(
                result.toString()
        );
    }


    // ==================================================
    // FIND CUSTOMER
    // ==================================================

    private static Customer findCustomer(
            int customerId) {

        for (
                Customer customer :
                system.getCustomers()
        ) {

            if (
                    customer.getCustomerId()
                    == customerId
            ) {

                return customer;
            }
        }

        return null;
    }


    // ==================================================
    // ERROR HANDLING
    // ==================================================

    private static void showError(
            Exception ex) {

        JOptionPane.showMessageDialog(
                frame,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}