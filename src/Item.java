public class Item {
    String name;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    int amount;

    public Item(String name, boolean isExpense, int quantity, int sumOfOne) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.amount = quantity * sumOfOne;
    }
}
