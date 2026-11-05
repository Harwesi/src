public class Receipt {
    private String owner;
    private double amount;
    private double fee;

    public Receipt(String owner, double amount, double fee) {
        this.owner = owner;
        this.amount = amount;
        this.fee = fee;
    }

    public void print() {
        System.out.println("----- RECEIPT -----");
        System.out.println("Client: " + owner);
        System.out.println("Withdrawn: " + amount);
        System.out.println("Fee: " + fee);
        System.out.println("-------------------");
    }
}
