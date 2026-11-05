public abstract class BankAccount implements Comparable<BankAccount> {
    private String id;          
    private String owner;
    protected double balance;

    public BankAccount(String id, String owner, double balance) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Account id is empty");
        }
        this.id = id;
        setOwner(owner);
        setBalance(balance);
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if (owner == null || owner.isEmpty()) {
            System.out.println("Error: owner name is empty");
        } else {
            this.owner = owner;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Error: balance can't be negative");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount);
        } else {
            System.out.println("Wrong amount");
        }
    }

    public abstract boolean withdraw(double amount);

    public abstract double getFee();

    public void showInfo() {
        System.out.println("Id: " + id + ", owner: " + owner + ", balance: " + balance);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount other = (BankAccount) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

   
    @Override
    public int compareTo(BankAccount other) {
        return id.compareTo(other.id);
    }
}
