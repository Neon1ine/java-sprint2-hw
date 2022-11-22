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

    static ArrayList<MonthItem> yearlyReport = new ArrayList<>();

    public static void getYearlyReport() {
        String s = FileReader.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = s.split("\\n");
        for (int i = 1; i < lines.length; i++) {
            String[] items = lines[i].split(",");
            MonthItem item = new MonthItem(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Boolean.parseBoolean(items[2]));
            yearlyReport.add(item);
        }
    }

    public static int checkReports() {
        for (int i = 0; i < 3; i++) {
            int eSum = 0;
            int notESum = 0;
            for (int j = 0; j < monthReports[i].itemsFromFile.size(); j++) {
                if (monthReports[i].itemsFromFile.get(j).isExpense)
                    eSum += monthReports[i].itemsFromFile.get(j).quantity * monthReports[i].itemsFromFile.get(j).sumOfOne;
                else
                    notESum += monthReports[i].itemsFromFile.get(j).quantity * monthReports[i].itemsFromFile.get(j).sumOfOne;
            }
            if (yearlyReport.get(i * 2).isExpense) {
                if (yearlyReport.get(i * 2).amount != eSum) return yearlyReport.get(i * 2).month;
            } else {
                if (yearlyReport.get(i * 2).amount != notESum) return yearlyReport.get(i * 2).month;
            }
            if (yearlyReport.get(i * 2 + 1).isExpense) {
                if (yearlyReport.get(i * 2 + 1).amount != eSum) return yearlyReport.get(i * 2 + 1).month;
            } else {
                if (yearlyReport.get(i * 2 + 1).amount != notESum) return yearlyReport.get(i * 2 + 1).month;
            }
        }
        return -1;
    }

    public static String convertToMonthName(int i) {
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

    public static String findMostProfitableItem(int month) {
        int max = 0;
        String result = "";
        for (int i = 0; i < monthReports[month].itemsFromFile.size(); i++) {
            if (!monthReports[month].itemsFromFile.get(i).isExpense){
                int value = monthReports[month].itemsFromFile.get(i).quantity * monthReports[month].itemsFromFile.get(i).sumOfOne;
                if (max < value)
                    result = monthReports[month].itemsFromFile.get(i).name + " " + value;
                max = value;
            }
        }
        return result;
    }

    public static String findMostExpensiveItem(int month) {
        int max = 0;
        String result = "";
        for (int i = 0; i < monthReports[month].itemsFromFile.size(); i++) {
            if (monthReports[month].itemsFromFile.get(i).isExpense){
                int value = monthReports[month].itemsFromFile.get(i).quantity * monthReports[month].itemsFromFile.get(i).sumOfOne;
                if (max < value)
                    result = monthReports[month].itemsFromFile.get(i).name + " " + value;
                max = value;
            }
        }
        return result;
    }

    public static int findIncomeForMonth(int month) {
        month--;
        int sum = 0;
        if (yearlyReport.get(month * 2 + 1).isExpense) sum -= yearlyReport.get(month * 2 + 1).amount;
        else sum += yearlyReport.get(month * 2 + 1).amount;
        if (yearlyReport.get(month * 2).isExpense) sum -= yearlyReport.get(month * 2).amount;
        else sum += yearlyReport.get(month * 2).amount;
        return sum;
    }

    public static double averageFlowForYear(boolean isExpense) {
        double sum = 0;
        for (int i = 0; i < 6; i++) {
            if (isExpense && yearlyReport.get(i).isExpense) sum += yearlyReport.get(i).amount;
            if (!isExpense && !yearlyReport.get(i).isExpense) sum += yearlyReport.get(i).amount;
        }
        return sum / 3;
    }
}
