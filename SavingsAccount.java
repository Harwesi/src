public class SavingsAccount extends BankAccount implements Interest, Transferable {
    private double rate;

    public SavingsAccount(String id, String owner, double balance, double rate) {
        super(id, owner, balance);
        this.rate = rate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount + getFee() <= balance) {
            balance -= amount + getFee();
            System.out.println("Withdrawn " + amount);
            return true;
        }
        System.out.println("Not enough money");
        return false;
    }

    @Override
    public double getFee() {
        return 0;
    }

    @Override
    public double calculateInterest() {
        return balance * rate / 100;
    }

    @Override
    public boolean transfer(BankAccount to, double amount) {
        if (withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }

    public void addBonus(double bonus) {
        balance += bonus;
        System.out.println("Bonus added: " + bonus);
    }

    @Override
    public void showInfo() {
        System.out.println("Savings account");
        super.showInfo();
        System.out.println("Rate: " + rate + "%");
    }
}
