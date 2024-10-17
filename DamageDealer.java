package Droids;
import Main.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static BattleField.DroidBattle.delay;

public class DamageDealer extends Droid {
    private double vampirism;
    public DamageDealer(String name, double health, double damage, double armor,double vampiarism){
        super(name,health,damage,armor);
        this.vampirism = vampiarism;
    }
    public double getVampirism() {
        return vampirism;
    }
    public void setVampirism(double vampirism) {
        this.vampirism = vampirism;
    }


    public void attack(ArrayList<Droid> enemies) {
        if (!isAlive()) return;
            Random random = new Random();
        if(enemies.size() == 0){return;}
            int enemyIndex = random.nextInt(enemies.size());

            Droid enemy = enemies.get(enemyIndex);

            double dealtDamage = enemy.getDamage() - enemy.getArmor();
            if (dealtDamage < 0) dealtDamage = 0;
            delay();
            Main.printWithFile("\u001B[31m"+getName() + " attack " + enemy.getName()+ "\n");
            enemy.takeDamage(dealtDamage);

            delay();
            double healthRestored = dealtDamage * (vampirism / 100);
            setHealth(getHealth() + healthRestored);
            Main.printWithFile("\u001B[32m"+getName() + " restore " + healthRestored + " health by vampirism."+ "\n");

            if (!enemy.isAlive()) {
                enemies.remove(enemy);
                delay();
                Main.printWithFile("\u001B[31m"+enemy.getName() + " has been destroyed!"+ "\n");
            }
    }
    public DamageDealer clone() {
        return new DamageDealer(getName(), getHealth(), getDamage(), getArmor(),getVampirism());
    }
    @Override
    public String toString() {
        return super.toString()+
                " vampirism - " + vampirism;
    }
}
