import java.util.List;

public class Main {
    public static void main(String[] args) {
        SavingsAccount s = new SavingsAccount("S-001", "Anna", 1000, 5);
        CheckingAccount c = new CheckingAccount("C-001", "Bek", 500, 200);
        CreditAccount cr = new CreditAccount("CR-001", "Aibek", 1000);

    
        Bank bank = new Bank("KBank");
        bank.addAccount(s);
        bank.addAccount(c);
        bank.addAccount(cr);
        bank.addAccount(new SavingsAccount("S-002", "Anna", 300, 4));   // Anna has 2 accounts (1:N)

        System.out.println("=== Inheritance and interfaces ===");
        for (BankAccount acc : bank.getAccounts()) {
            acc.showInfo();
            if (acc instanceof Interest) {
                System.out.println("Interest: " + ((Interest) acc).calculateInterest());
            }
        }

        System.out.println("\n=== Duplicate and errors ===");
        try {
            bank.addAccount(new CheckingAccount("C-001", "Fake", 10, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            bank.removeAccount("X-999");
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Search (Map) ===");
        System.out.println("By id C-001:");
        bank.findAccount("C-001").showInfo();
        System.out.println("Accounts of Anna: " + bank.findByOwner("Anna").size());
        System.out.println("Savings accounts: " + bank.findByType("SavingsAccount").size());
        System.out.println("Balance more than 400: " + bank.filterByMinBalance(400).size());
        System.out.println("Accounts of Nobody: " + bank.findByOwner("Nobody").size());

        System.out.println("\n=== Sorting ===");
        System.out.println("Natural order (by id):");
        printIds(bank.getSorted());
        System.out.println("By balance:");
        printIds(bank.getSorted(new AccountComparators.ByBalance()));
        System.out.println("By owner:");
        printIds(bank.getSorted(new AccountComparators.ByOwner()));
        System.out.println("By fee, then balance:");
        printIds(bank.getSorted(new AccountComparators.ByFeeThenBalance()));

        System.out.println("\n=== ATM queue ===");
        ATM atm = new ATM(bank);
        atm.showCash();
        // clients put requests
        atm.addRequest(new WithdrawRequest("S-001", 200));
        atm.addRequest(new WithdrawRequest("C-001", 100));
        atm.addRequest(new WithdrawRequest("Nobody", 50));
        System.out.println("In queue: " + atm.getQueueSize());
        // ATM takes them one by one
        atm.processRequests();
        atm.showCash();
        atm.processRequests();

        System.out.println("\n=== Update and remove ===");
        bank.updateOwner("C-001", "Bekzat");
        System.out.println("Bek: " + bank.findByOwner("Bek").size() + ", Bekzat: " + bank.findByOwner("Bekzat").size());
        bank.removeAccount("S-002");
        System.out.println("Accounts of Anna after remove: " + bank.findByOwner("Anna").size());
        System.out.println("Savings accounts after remove: " + bank.findByType("SavingsAccount").size());

        bank.printReport();

        System.out.println("\n=== Aggregation / Composition ===");
        atm = null; // ATM and its CashBox are gone
        System.out.println("ATM removed, but bank and accounts still exist:");
        bank.showAll();
    }

    private static void printIds(List<BankAccount> list) {
        for (BankAccount acc : list) {
            System.out.println("  " + acc.getId() + " " + acc.getOwner() + " " + acc.getBalance());
        }
    }
}
