public class CashBox {
    private double cash;

    public CashBox(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }

    public boolean hasCash(double amount) {
        return cash >= amount;
    }

    public void takeCash(double amount) {
        cash -= amount;
    }
}
