import java.util.Comparator;

public class AccountComparators {


    public static class ByBalance implements Comparator<BankAccount> {
        @Override
        public int compare(BankAccount a, BankAccount b) {
            return Double.compare(b.getBalance(), a.getBalance());
        }
    }


    public static class ByOwner implements Comparator<BankAccount> {
        @Override
        public int compare(BankAccount a, BankAccount b) {
            return a.getOwner().compareTo(b.getOwner());
        }
    }

    public static class ByFeeThenBalance implements Comparator<BankAccount> {
        @Override
        public int compare(BankAccount a, BankAccount b) {
            int result = Double.compare(a.getFee(), b.getFee());
            if (result == 0) {
                result = Double.compare(b.getBalance(), a.getBalance());
            }
            return result;
        }
    }
}
