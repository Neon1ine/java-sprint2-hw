import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

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

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, " +
                    "файл не находится в нужной директории.");
            return null;
        }
    }

}
