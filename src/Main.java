import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isReportsForMonthsDone = false;
        boolean isReportsForYearDone = false;
        while (true) {
            printMenu();
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    ReportBase.getReportsForAllMonths();
                    isReportsForMonthsDone = true;
                    System.out.println("Все месячные отчёты считаны.");
                    //System.out.println(ReportBase.monthReports[0].itemsFromFile.get(0).name);
                    break;
                case 2:
                    ReportBase.getYearlyReport();
                    isReportsForYearDone = true;
                    System.out.println("Годовой отчёт считан.");
                    //System.out.println(ReportBase.expenses.get(0).amount);
                    break;
                case 3:
                    if (isReportsForMonthsDone && isReportsForYearDone) {

                    } else System.out.println("Отчёты ещё не считаны");
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    return;
                default:
                    System.out.println("Такой команды не существует!");
                    break;
            }
        }

    }

    private static void printMenu() {
        System.out.println("Введите число от 1 до 6, соответствующее одной из команд:" +
                "\n1. Считать все месячные отчёты;" +
                "\n2. Считать годовой отчёт;" +
                "\n3. Сверить отчёты;" +
                "\n4. Вывести информацию о всех месячных отчётах;" +
                "\n5. Вывести информацию о годовом отчёте;" +
                "\n6. Выйти из программы.");
    }

}
