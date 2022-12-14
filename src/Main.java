import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isMReportsDone = false;
        boolean isYReportDone = false;
        ReportBase reportBase = new ReportBase();
        while (true) {
            printMenu();
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    if (!reportBase.getReportsForAllMonths(isMReportsDone)) {
                        isMReportsDone = true;
                        System.out.println("Все месячные отчёты считаны.");
                    }
                    break;
                case 2:
                    if (!reportBase.getYearlyReport(isYReportDone)) {
                        isYReportDone = true;
                        System.out.println("Годовой отчёт считан.");
                    }
                    break;
                case 3:
                    if (isMReportsDone && isYReportDone) {
                        int result = reportBase.checkReports();
                        if (result == -1) System.out.println("Отчёты сходятся.");
                        else System.out.println("Отчёты не сходятся. Ошибка в месяце " + reportBase.convertToMonthName(result) + ".");
                    } else printWarning();
                    break;
                case 4:
                    if (isMReportsDone) printMonthlyReport(reportBase);
                    else printWarning();
                    break;
                case 5:
                    if (isYReportDone) printYearlyReport(reportBase);
                    else printWarning();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Такой команды не существует.");
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

    private static void printWarning() {
        System.out.println("Отчёты ещё не считаны.");
    }

    private static void printMonthlyReport(ReportBase reportBase) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Месяц " + reportBase.convertToMonthName(i + 1) + ":" +
                    "\nСамый прибыльный товар: " + reportBase.findMostProfitableItem(i) +
                    "\nСамая большая трата: " + reportBase.findMostExpensiveItem(i));
        }
    }

    private static void printYearlyReport(ReportBase reportBase) {
        System.out.println("Рассматриваемый год: 2021" +
                "\nПрибыль по каждому месяцу:");
        for (int i = 1; i <= 3; i++)
            System.out.println(i + " месяц: " + reportBase.findIncomeForMonth(i - 1));
        System.out.println("Средний расход за все месяцы в году: " + reportBase.averageFlowForYear(true) +
                "\nСредний доход за все месяцы в году: " + reportBase.averageFlowForYear(false));
    }

}
