package BattleField;
import Droids.*;
import java.util.*;
import Main.Main;
public class DroidBattle{

    public boolean isBattleOver(ArrayList<Droid> team1, ArrayList<Droid> team2) {
        boolean team1Alive = team1.stream().anyMatch(Droid::isAlive);
        boolean team2Alive = team2.stream().anyMatch(Droid::isAlive);
        return !team1Alive || !team2Alive;
    }
    public static void delay(){
      try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    public void startBattle(ArrayList<Droid> yourTeam, ArrayList<Droid> enemyTeam) {
        int round = 1;
        while (true) {
           delay();
            Main.printWithFile("Round " + round+ "\n");

            for (Droid droid : yourTeam) {
                if (droid.isAlive()) {
                    droid.takeTurn(enemyTeam, yourTeam);
                }
            }
            for (Droid droid : enemyTeam) {
                if (droid.isAlive()) {
                    droid.takeTurn(yourTeam, enemyTeam);
                }
            }
            if (isBattleOver(yourTeam, enemyTeam)) {
                delay();
                Main.printWithFile("The battle is over!"+ "\n");
                break;
            }
            round++;

        }
    }

}


