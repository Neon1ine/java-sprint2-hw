import java.util.ArrayList;

public class ReportBase {

    static MonthReport[] monthReports = new MonthReport[12];

    public static void getReportsForAllMonths() {
        for (int i = 1; i <= 3; i++) {
            MonthReport monthReport = new MonthReport(getMonthReport(i), i);
            monthReports[i-1] = monthReport;
        }
    }

    public static ArrayList<Item> getMonthReport(int month) {
        String s = FileReader.readFileContentsOrNull("resources/m.20210" + month + ".csv");
        String[] lines = s.split("\\n");
        ArrayList<Item> itemArr = new ArrayList<>();

        for (int i = 1; i < lines.length; i++){
            String[] items = lines[i].split(",");
            Item item = new Item(items[0], Boolean.parseBoolean(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]));
            itemArr.add(item);
        }

        return itemArr;
    }

    static ArrayList<MonthItem> expenses = new ArrayList<>();

    public static void getYearlyReport() {
        String s = FileReader.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = s.split("\\n");
        for (int i = 1; i < lines.length; i++) {
            String[] items = lines[i].split(",");
            MonthItem item = new MonthItem(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Boolean.parseBoolean(items[2]));
            expenses.add(item);
        }

    }
}
