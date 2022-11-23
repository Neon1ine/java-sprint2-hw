import java.util.ArrayList;

public class ReportBase {

    MonthReport[] monthReports = new MonthReport[12];
    ArrayList<MonthItem> yearlyReport = new ArrayList<>();

    public boolean getReportsForAllMonths(boolean isReportCreated) {
        if (isReportCreated) clearMonthReports();
        for (int i = 1; i <= 3; i++) {
            ArrayList<Item> arr = getMonthReport(i);
            if (arr.isEmpty()) return true;
            MonthReport monthReport = new MonthReport(arr, i);
            monthReports[i - 1] = monthReport;
        }
        return false;
    }

    private void clearMonthReports() {
        for (int i = 0; i < monthReports.length; i++)
            monthReports[0].itemsFromFile.clear();
    }

    public ArrayList<Item> getMonthReport(int month) {
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

    public boolean getYearlyReport(boolean isReportCreated) {
        if (isReportCreated) clearYearReport();
        String s = FileReader.readFileContentsOrNull("resources/y.2021.csv");
        if (s == null) return true;
        String[] lines = s.split("\\n");
        for (int i = 1; i < lines.length; i++) {
            String[] items = lines[i].split(",");
            MonthItem item = new MonthItem(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Boolean.parseBoolean(items[2]));
            yearlyReport.add(item);
        }
        return false;
    }

    private void clearYearReport() {
        yearlyReport.clear();
    }

    public int checkReports() {
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < monthReports[i].itemsFromFile.size(); j++) {
                if (monthReports[i].itemsFromFile.get(j).isExpense)
                    sum -= monthReports[i].itemsFromFile.get(j).amount;
                else
                    sum += monthReports[i].itemsFromFile.get(j).amount;
            }
            if (sum != findIncomeForMonth(i)) return i + 1;
        }
        return -1;
    }

    public String convertToMonthName(int i) {
        switch (i) {
            case 1:
                return "январь";
            case 2:
                return "февраль";
            case 3:
                return "март";
            default:
                return String.valueOf(i);
        }
    }

    public String findMostProfitableItem(int month) {
        int max = 0;
        String result = "";
        for (int i = 0; i < monthReports[month].itemsFromFile.size(); i++) {
            if (!monthReports[month].itemsFromFile.get(i).isExpense){
                int value = monthReports[month].itemsFromFile.get(i).amount;
                if (max < value)
                    result = monthReports[month].itemsFromFile.get(i).name + " " + value;
                max = value;
            }
        }
        return result;
    }

    public String findMostExpensiveItem(int month) {
        int max = 0;
        String result = "";
        for (int i = 0; i < monthReports[month].itemsFromFile.size(); i++) {
            if (monthReports[month].itemsFromFile.get(i).isExpense){
                int value = monthReports[month].itemsFromFile.get(i).amount;
                if (max < value)
                    result = monthReports[month].itemsFromFile.get(i).name + " " + value;
                max = value;
            }
        }
        return result;
    }

    public int findIncomeForMonth(int month) {
        int sum = 0;
        for (int i = 0; i < yearlyReport.size(); i++) {
            if (yearlyReport.get(i).month == month + 1) {
                if (yearlyReport.get(i).isExpense) sum -= yearlyReport.get(i).amount;
                else sum += yearlyReport.get(i).amount;
            }
        }
        return sum;
    }

    public double averageFlowForYear(boolean isExpense) {
        double sum = 0;
        for (int i = 0; i < 6; i++) {
            if (isExpense && yearlyReport.get(i).isExpense) sum += yearlyReport.get(i).amount;
            if (!isExpense && !yearlyReport.get(i).isExpense) sum += yearlyReport.get(i).amount;
        }
        return sum / 3;
    }
}
