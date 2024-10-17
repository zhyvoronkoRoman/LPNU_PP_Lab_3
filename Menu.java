package Menu;
import Droids.*;
import BattleField.DroidBattle;
import Main.Main;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Menu {
    private ArrayList<Droid> allies = new ArrayList<>();
    private ArrayList<Droid> enemys = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    public void menu(){
    int choice = 0;
    while(true) {
        showMenu();
        System.out.println("Enter option");
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                createSingleDroid();
                break;
            case 2:
                createDroidTeam();
                break;
            case 3:
                FileWriter writer = null;
                String file_name = Main.enterStringValue("Write file name (Enter to none): ");
                if (!file_name.isEmpty()) {
                    try {
                        writer = new FileWriter(file_name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<Droid> alliesCopy = new ArrayList<>();
                 ArrayList<Droid> enemysCopy = new ArrayList<>();
                if (allies.isEmpty() || enemys.isEmpty()) {
                    System.out.println("Teams is empty.");
                    return;
                }
                alliesCopy.clear();
                enemysCopy.clear();
                for (Droid d : enemys) {
                    enemysCopy.add(d.clone());
                }
                for (Droid d : allies) {
                    alliesCopy.add(d.clone());
                }
                DroidBattle battleField = new DroidBattle();
            battleField.startBattle(alliesCopy,enemysCopy);
                if (!file_name.isEmpty()) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                showTeams(enemysCopy,alliesCopy);
                break;
            case 4:removeDroid();
                break;
            case 5:Main.printFile();
                break;
            case 6:enemys.clear();
            allies.clear();
                break;
            case 7:
                showTeams(allies,enemys);
                break;
            case 8:
                return;
            default:System.out.println("Wrong option!");
        }
    }
    }

    private void showMenu(){
        System.out.print("\u001B[0m");
        System.out.println("\n\t\t MENU");
        System.out.println("1 - Create single droid");
        System.out.println("2 - Create droid team");
        System.out.println("3 - Start Battle");
        System.out.println("4 - Remove droid");
        System.out.println("5 - Read battle from file");
        System.out.println("6 - Remove all Droids");
        System.out.println("7 - Show teams");
        System.out.println("8 - Exit");
    }

    private void showTeams(ArrayList<Droid> team1, ArrayList<Droid> team2) {
        System.out.println("Enemy's team:");
        if (team1.isEmpty()) {
            System.out.println("Team is empty.");
        } else {
            for (Droid d : team1) {
                System.out.println(d);
            }
        }
        System.out.println("Allie's team:");
        if (team2.isEmpty()) {
            System.out.println("Team is empty.");
        } else {
            for (Droid i : team2) {
                System.out.println(i);
            }
        }
    }
private void droidsInfo(){
    System.out.println("\n\t\tInfo about Droids types");
    System.out.println("1 - Damage Dealer(Has the greatest damage and has the ability to heal for a percentage of the damage inflicted by the enemy)");
    System.out.println("2 - Tank(Has the greatest health and armor and can cut through the armor)");
    System.out.println("3 - Warrior(Simple warrior that don't have any special abilitys(Every story has its own side characters))");
}
    private int createDroidTeam(){
        droidsInfo();
        System.out.println("Create Damage Dealer");
        allies.add(createDamageDealer());
        System.out.println("Create Tank");
        allies.add(createTank());
        System.out.println("Create Mage");
        allies.add(createWarrior());
        return 0;
    }
    private void createSingleDroid(){
        droidsInfo();
        System.out.println("What kind of droid you want to create?");
        System.out.println("4 - Exit");
        int choice = scan.nextInt();
        switch (choice){
            case 1:allies.add(createDamageDealer());
                break;
            case 2:allies.add(createTank());
                break;
            case 3: allies.add(createWarrior());
                break;
            case 4:break;
        }
    }

    private DamageDealer createDamageDealer() {
        Random random = new Random();
        String name1 = "";
        double health1 = 0;
        double damage1 = 0;
        double armor1 = 0;
        double vampirism = 0;
        int choise;

        while (true) {
            System.out.println("Choose option: 1 - Enter details, 2 - Default settings");
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Invalid input, please enter a number.");
                scan.nextLine();
                continue;
            }

            switch (choise) {
                case 1:
                    System.out.print("Enter name: ");
                    name1 = scan.nextLine();
                    System.out.print("Enter health: ");
                    if (scan.hasNextDouble()) {
                        health1 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid health value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter damage: ");
                    if (scan.hasNextDouble()) {
                        damage1 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid damage value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter armor: ");
                    if (scan.hasNextDouble()) {
                        armor1 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid armor value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();


                    break;
                case 2:
                    name1 = "Your Damage Dealer";
                    health1 = 1000.0f;
                    damage1 = 200.0f;
                    armor1 = 10.0f;
                    break;
                default:
                    System.out.println("Wrong option. Please choose again.");
                    continue;
            }
            break;
        }
        vampirism = damage1/4;
        DamageDealer damageDealer = new DamageDealer(name1, health1, damage1, armor1, vampirism);
        System.out.println(damageDealer + " ");
        enemys.add(createEnemyDamageDealer(health1, damage1, armor1, vampirism));
        return damageDealer;
    }
    private Warrior createWarrior() {
        String name3 = "";
        double health3 = 0;
        double damage3 = 0;
        double armor3 = 0;
        int choise;

        while (true) {
            System.out.println("Choose option: 1 - Enter details, 2 - Default settings");
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Invalid input, please enter a number.");
                scan.nextLine();
                continue;
            }

            switch (choise) {
                case 1:
                    System.out.print("Enter name: ");
                    name3 = scan.nextLine();
                    System.out.print("Enter health: ");
                    if (scan.hasNextDouble()) {
                        health3 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid health value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter damage: ");
                    if (scan.hasNextDouble()) {
                        damage3 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid damage value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter armor: ");
                    if (scan.hasNextDouble()) {
                        armor3 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid armor value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    break;
                case 2:
                    name3 = "Your Warrior";
                    health3 = 1500.0f;
                    damage3 = 190.0f;
                    armor3 = 15.0f;
                    break;
                default:
                    System.out.println("Wrong option. Please choose again.");
                    continue;
            }
            break;
        }
        Warrior Mage = new Warrior(name3,health3,damage3,armor3);
        System.out.println(Mage);
        enemys.add(createEnemyWarrior(health3,damage3,armor3));
        return Mage;
    }
    private Tank createTank(){
        String name2 = "";
        double health2 = 0;
        double damage2 = 0;
        double armor2 = 0;
        double armorCorrosion = 0;
        int choise;
        while (true) {
            System.out.println("Choose option: 1 - Enter details, 2 - Default settings");
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Invalid input, please enter a number.");
                scan.nextLine();
                continue;
            }

            switch (choise) {
                case 1:
                    System.out.print("Enter name: ");
                    name2 = scan.nextLine();
                    System.out.print("Enter health: ");
                    if (scan.hasNextDouble()) {
                        health2 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid health value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter damage: ");
                    if (scan.hasNextDouble()) {
                        damage2 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid damage value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.print("Enter armor: ");
                    if (scan.hasNextDouble()) {
                        armor2 = scan.nextDouble();
                    } else {
                        System.out.println("Invalid armor value. Try again.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    break;
                case 2:
                    name2 = "Your Tank";
                    health2 = 2000.0f;
                    damage2 = 180.0f;
                    armor2 = 20.0f;
                    break;
                default:
                    System.out.println("Wrong option. Please choose again.");
                    continue;
            }
            break;
        }
        armorCorrosion = (health2 / damage2) * 0.1f;
        Tank Tank = new Tank(name2,health2,damage2,armor2,armorCorrosion);
        System.out.println(Tank);
        enemys.add(createEnemyTank(health2,damage2,armor2,armorCorrosion));
        return Tank;
    }
    private Warrior createEnemyWarrior(double health, double damage, double armor){
    String enemyName = "Enemy Warrior";
    Random random = new Random();
        double enemyHealth = health+random.nextInt(21) - 10;
        double enemyDamage = damage + random.nextInt(21) - 10;
        double enemyArmor = armor + random.nextInt(21) - 10;
    Warrior EnemyWarrior = new Warrior(enemyName,enemyHealth,enemyDamage,enemyArmor);
    return EnemyWarrior;
    }
    private DamageDealer createEnemyDamageDealer(double health,double damage,double armor,double vampirism){
        String enemyName = "Enemy Damage Dealer";
        Random random = new Random();
        double enemyHealth = health+random.nextInt(21) - 10;
        double enemyDamage = damage + random.nextInt(21) - 10;
        double enemyArmor = armor + random.nextInt(21) - 10;
        double enemyVampirism  = vampirism + random.nextInt(10) - 1;
        DamageDealer EnemyDamagedealer = new DamageDealer(enemyName,enemyHealth,enemyDamage,enemyArmor,enemyVampirism);
        return EnemyDamagedealer;
    }
    private Tank createEnemyTank(double health,double damage,double armor,double armorCorrosion){
        String enemyName = "Enemy Tank";
        Random random = new Random();
        double enemyHealth = health+random.nextInt(21) - 10;
        double enemyDamage = damage + random.nextInt(21) - 10;
        double enemyArmor = armor + random.nextInt(10) - 1;
        double enemyArmorCorrosion  = armorCorrosion + random.nextInt(2) - 1;
        Tank EnemyTank = new Tank(enemyName,enemyHealth,enemyDamage,enemyArmor,enemyArmorCorrosion);
        return EnemyTank;
    }
    private void removeDroid(){
        System.out.print("Choose droid to delete: ");

        if (allies.isEmpty()) {
            System.out.println("Your team doesn't have any droids.");
            return;
        }
        System.out.println("Droids in your team:");
        for (int i = 0; i < allies.size(); i++) {
            System.out.println(i + 1 + ". " + allies.get(i));
        }

        System.out.print("Choose number of droid: ");
        int droidIndex = scan.nextInt();
        scan.nextLine();

        if (droidIndex > 0 && droidIndex <= allies.size()) {
            Droid removedDroid = allies.remove(droidIndex - 1);
            Droid removedEnemyDroid = enemys.remove(droidIndex - 1);
            System.out.println("Droid " + removedDroid.getName() + " was deleted.");
        } else {
            System.out.println("Wrong option.");
        }
    }
}
