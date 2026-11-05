import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("S-001", "Anna", 1000, 5));
        accounts.add(new SavingsAccount("S-002", "Aida", 300, 4));
        accounts.add(new CheckingAccount("C-001", "Bek", 500, 200));
        accounts.add(new CheckingAccount("C-002", "Timur", 1500, 300));
        accounts.add(new CreditAccount("CR-001", "Aibek", 1000));
        accounts.add(new CreditAccount("CR-002", "Samat", 2000));

        // Show the whole list
        System.out.println("=== All accounts ===");
        for (BankAccount acc : accounts) {
            acc.showInfo();
            System.out.println();
        }

        // Filter 1: balance more than 500
        double minBalance = 500;
        int count = 0;
        System.out.println("=== Balance more than " + minBalance + " ===");
        for (BankAccount acc : accounts) {
            if (acc.getBalance() > minBalance) {
                acc.showInfo();
                System.out.println();
                count++;
            }
        }
        System.out.println("Found: " + count);

        // Filter 2: only savings accounts
        count = 0;
        System.out.println("=== Only savings accounts ===");
        for (BankAccount acc : accounts) {
            if (acc instanceof SavingsAccount) {
                acc.showInfo();
                System.out.println();
                count++;
            }
        }
        System.out.println("Found: " + count);

        // Filter 3: owner name starts with A
        count = 0;
        System.out.println("=== Owner name starts with A ===");
        for (BankAccount acc : accounts) {
            if (acc.getOwner().startsWith("A")) {
                acc.showInfo();
                System.out.println();
                count++;
            }
        }
        System.out.println("Found: " + count);

        // Filter 4: accounts that have interest
        count = 0;
        System.out.println("=== Accounts with interest ===");
        for (BankAccount acc : accounts) {
            if (acc instanceof Interest) {
                acc.showInfo();
                System.out.println("Interest: " + ((Interest) acc).calculateInterest());
                System.out.println();
                count++;
            }
        }
        System.out.println("Found: " + count);
    }
}
