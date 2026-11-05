public class CreditAccount extends BankAccount implements Interest {
    private double creditLimit;
    private double debt;

    public CreditAccount(String id, String owner, double creditLimit) {
        super(id, owner, 0);
        this.creditLimit = creditLimit;
        this.debt = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && debt + amount + getFee() <= creditLimit) {
            debt += amount + getFee();
            System.out.println("Withdrawn " + amount + " from credit");
            return true;
        }
        System.out.println("Credit limit exceeded");
        return false;
    }

    @Override
    public double getFee() {
        return 2;
    }

    @Override
    public double calculateInterest() {
        return debt * 0.02;
    }

    public void showDebt() {
        System.out.println("Current debt: " + debt);
    }

    @Override
    public void showInfo() {
        System.out.println("Credit account");
        super.showInfo();
        System.out.println("Credit limit: " + creditLimit);
    }
}
