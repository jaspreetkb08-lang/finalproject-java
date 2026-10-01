public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private String email;
    private double income;
    private int creditScore;

    public Customer(
            int customerId,
            String name,
            String phone,
            String email,
            double income,
            int creditScore) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (income <= 0) {
            throw new IllegalArgumentException(
                    "Income must be greater than 0."
            );
        }

        if (creditScore < 300 || creditScore > 900) {
            throw new IllegalArgumentException(
                    "Credit score must be between 300 and 900."
            );
        }

        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.income = income;
        this.creditScore = creditScore;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public double getIncome() {
        return income;
    }

    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId
                + "\nName: " + name
                + "\nPhone: " + phone
                + "\nEmail: " + email
                + "\nIncome: ₹" + String.format("%.2f", income)
                + "\nCredit Score: " + creditScore;
    }
}