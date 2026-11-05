import java.util.ArrayDeque;
import java.util.Queue;

public class ATM {
    private CashBox cashBox;   
    private Bank bank;        
    private Queue<WithdrawRequest> requests = new ArrayDeque<>();   // FIFO 

    public ATM(Bank bank) {
        this.bank = bank;
        this.cashBox = new CashBox(5000);
    }

    public void addRequest(WithdrawRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is null");
        }
        requests.offer(request);
    }

    
    public void processRequests() {
        if (requests.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        while (!requests.isEmpty()) {
            WithdrawRequest request = requests.poll();
            withdrawMoney(request.getAccountId(), request.getAmount());
        }
    }

    public int getQueueSize() {
        return requests.size();
    }

    public void withdrawMoney(String accountId, double amount) {
        BankAccount acc = bank.findAccount(accountId);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        if (!cashBox.hasCash(amount)) {
            System.out.println("ATM has not enough cash");
            return;
        }
        if (acc.withdraw(amount)) {
            cashBox.takeCash(amount);
            Receipt receipt = new Receipt(acc.getOwner(), amount, acc.getFee()); // dependency
            receipt.print();
        }
    }

    public void showCash() {
        System.out.println("ATM cash: " + cashBox.getCash());
    }
}
