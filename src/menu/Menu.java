package menu;

import droids.*;
import battle.*;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Командний бій");
            System.out.println("5. Записати бій у файл");
            System.out.println("6. Відтворити бій з файлу");
            System.out.println("7. Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    duel();
                    break;
                case 4:
                    teamBattle();
                    break;
                case 5:
                    FileHandler.writeToFile("battle_log.txt", "Бій записано.");
                    break;
                case 6:
                    FileHandler.readFromFile("battle_log.txt");
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }

    public void createDroid() {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. Інженер");
        System.out.println("2. Джаггернаут");
        System.out.println("3. Псі-Дроїд");

        int choice = scanner.nextInt();
        System.out.println("Введіть ім'я для дроїда:");
        String name = scanner.next();

        Droid droid;
        switch (choice) {
            case 1:
                droid = new Engineer(name, 100, 20, 50, 10);
                break;
            case 2:
                droid = new Juggernaut(name, 150, 30, 60, 5);
                break;
            case 3:
                droid = new Psi_Runner(name, 80, 40, 30, 20);
                break;
            default:
                System.out.println("Невірний вибір! Дроїд не створений.");
                return;
        }

        droids.add(droid);
    }

    private Droid chooseDroid() {
        System.out.println("Оберіть дроїда:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }
        int choice = scanner.nextInt() - 1;
        return droids.get(choice);
    }

    public void showDroids() {}

    public void duel() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для дуелі. Потрібно як мінімум 2.");
            return;
        }

        System.out.println("Виберіть першого дроїда:");
        Droid droid1 = droids.get(scanner.nextInt() - 1);
        System.out.println("Виберіть другого дроїда:");
        Droid droid2 = droids.get(scanner.nextInt() - 1);

        Battle battle = new Duel(droid1, droid2);
        battle.start();
    }


    public void teamBattle() {
        if (droids.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою. Потрібно як мінімум 4.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Оберіть дроїдів для команди 1:");
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team1.add(droid);
        }

        System.out.println("Оберіть дроїдів для команди 2:");
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team2.add(droid);
        }

        Battle battle = new TeamVsTeam(team1, team2);
        battle.start();
    }

}