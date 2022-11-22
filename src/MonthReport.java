import java.util.ArrayList;

public class MonthReport {
    ArrayList<Item> itemsFromFile;
    Integer month;

    public MonthReport(ArrayList<Item> itemsFromFile, Integer month) {
        this.itemsFromFile = itemsFromFile;
        this.month = month;
    }
}
