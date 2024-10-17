package Main;

import Menu.Menu;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        Menu Menu = new Menu();
        Menu.menu();
        }
    public static void printWithFile(String string) {
        try (FileWriter writer = new FileWriter("battle.txt", true)) {
            System.out.println(string);

            if (writer != null) {
                try {
                    writer.write(string);
                } catch (IOException e) {
                    System.out.println(e);;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);;
            }

    }
    public static void printFile() {
        String file_name = Main.enterStringValue("Write file name (Enter to none): ");

        if (!file_name.isEmpty()) {
            try {
                System.out.println(Files.readString(Paths.get(file_name)) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   public static String enterStringValue(String hint) {
        Scanner in = new Scanner(System.in);
        System.out.print(hint);

        return in.nextLine();
    }
    }
