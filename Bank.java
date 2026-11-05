 import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Bank {
    private String name;

    private Set<BankAccount> accounts = new LinkedHashSet<>();
    private Map<String, BankAccount> accountsById = new HashMap<>();
    private Map<String, List<BankAccount>> accountsByOwner = new HashMap<>();
    private Map<String, List<BankAccount>> accountsByType = new TreeMap<>();

    public Bank(String name) {
        this.name = name;
    }



    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is null");
        }
        if (account.getOwner() == null) {
            throw new IllegalArgumentException("Account has no owner");
        }
        if (!accounts.add(account)) {
            throw new IllegalArgumentException("Account already exists: " + account.getId());
        }
        accountsById.put(account.getId(), account);
        addToIndex(accountsByOwner, account.getOwner(), account);
        addToIndex(accountsByType, getType(account), account);
    }

    public BankAccount findAccount(String id) {
        return accountsById.get(id);
    }

    public void updateOwner(String id, String newOwner) {
        BankAccount acc = accountsById.get(id);
        if (acc == null) {
            throw new AccountNotFoundException(id);
        }
        if (newOwner == null || newOwner.isEmpty()) {
            throw new IllegalArgumentException("New owner name is empty");
        }
        removeFromIndex(accountsByOwner, acc.getOwner(), acc);
        acc.setOwner(newOwner);
        addToIndex(accountsByOwner, newOwner, acc);
    }


    public void removeAccount(String id) {
        BankAccount acc = accountsById.remove(id);
        if (acc == null) {
            throw new AccountNotFoundException(id);
        }
        accounts.remove(acc);
        removeFromIndex(accountsByOwner, acc.getOwner(), acc);
        removeFromIndex(accountsByType, getType(acc), acc);
    }


    public List<BankAccount> findByOwner(String owner) {
        List<BankAccount> list = accountsByOwner.get(owner);
        if (list == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }

    public List<BankAccount> findByType(String type) {
        List<BankAccount> list = accountsByType.get(type);
        if (list == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }

    public List<BankAccount> filterByMinBalance(double minBalance) {
        List<BankAccount> result = new ArrayList<>();
        for (BankAccount acc : accounts) {
            if (acc.getBalance() > minBalance) {
                result.add(acc);
            }
        }
        return result;
    }


    public List<BankAccount> getSorted() {
        List<BankAccount> list = new ArrayList<>(accounts);
        Collections.sort(list);
        return list;
    }

    public List<BankAccount> getSorted(Comparator<BankAccount> comparator) {
        List<BankAccount> list = new ArrayList<>(accounts);
        list.sort(comparator);
        return list;
    }



    public double getTotalBalance() {
        double sum = 0;
        for (BankAccount acc : accounts) {
            sum += acc.getBalance();
        }
        return sum;
    }

    public void printReport() {
        System.out.println("=== Report: " + name + " ===");
        if (accounts.isEmpty()) {
            System.out.println("No accounts");
            return;
        }
        System.out.println("Accounts: " + accounts.size());
        System.out.println("Total balance: " + getTotalBalance());
        for (String type : accountsByType.keySet()) {
            System.out.println(type + ": " + accountsByType.get(type).size());
        }
        BankAccount richest = getSorted(new AccountComparators.ByBalance()).get(0);
        System.out.println("Biggest balance: " + richest.getOwner() + " (" + richest.getBalance() + ")");
    }

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void showAll() {
        System.out.println("Bank: " + name);
        for (BankAccount acc : accounts) {
            acc.showInfo();
        }
    }


    private String getType(BankAccount acc) {
        return acc.getClass().getSimpleName();
    }

    private void addToIndex(Map<String, List<BankAccount>> map, String key, BankAccount acc) {
        List<BankAccount> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        list.add(acc);
    }

    private void removeFromIndex(Map<String, List<BankAccount>> map, String key, BankAccount acc) {
        List<BankAccount> list = map.get(key);
        if (list != null) {
            list.remove(acc);
            if (list.isEmpty()) {
                map.remove(key);
            }
        }
    }
}
