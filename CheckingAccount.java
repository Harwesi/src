public class CheckingAccount extends BankAccount implements Transferable {
    private double overdraft;

    public CheckingAccount(String id, String owner, double balance, double overdraft) {
        super(id, owner, balance);
        this.overdraft = overdraft;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount - getFee() >= -overdraft) {
            balance -= amount + getFee();
            System.out.println("Withdrawn " + amount);
            return true;
        }
        System.out.println("Overdraft limit exceeded");
        return false;
    }

    @Override
    public double getFee() {
        return 1;
    }

    @Override
    public boolean transfer(BankAccount to, double amount) {
        if (withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }

    public void showOverdraft() {
        System.out.println("Overdraft limit: " + overdraft);
    }

    @Override
    public void showInfo() {
        System.out.println("Checking account");
        super.showInfo();
    }
}
