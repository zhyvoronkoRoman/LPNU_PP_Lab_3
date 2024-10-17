package Droids;

import java.util.*;
import Main.Main;
import static BattleField.DroidBattle.delay;

public class Droid {
    private String name;
    private double health;
    private double damage;
    private double armor;

    public Droid() {}
    public Droid(String name, double health, double damage, double armor) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
    }

    public String getName() { return name; }
    public double getDamage() { return damage; }
    public double getHealth() { return health; }
    public double getArmor() { return armor; }
    public void setName(String name) { this.name = name; }
    public void setArmor(double armor) { this.armor = armor; }
    public void setDamage(double damage) { this.damage = damage; }
    public void setHealth(double health) { this.health = health; }

    public boolean isAlive() {
        return health > 0;
    }


    public void takeDamage(double damage) {
        double actualDamage = damage - armor;
        if (actualDamage < 0) actualDamage = 0;
        health -= actualDamage;
        if (health < 0) {
            health = 0;
        }
        delay();
        System.out.println("\u001B[34m"+name + " get " + actualDamage + " damage. health left: " + health+ "\n");
        if (health == 0) {
            Main.enterStringValue("\u001B[31m"+name + " has been destroyed!"+ "\n");
        }
    }

    public void attack(ArrayList<Droid> enemies) {
        if (!isAlive()) {
            return;
        }
        Random random = new Random();
        if(enemies.size() == 0){return;}
        int enemyIndex = random.nextInt(enemies.size());

        Droid enemy = enemies.get(enemyIndex);
        delay();
        Main.printWithFile("\u001B[31m"+name + " attack " + enemy.getName()+ "\n");
        enemy.takeDamage(damage);

        if (!enemy.isAlive()) {
            enemies.remove(enemy);
           delay();
            Main.printWithFile("\u001B[31m"+enemy.getName() + " has been destroyed!"+ "\n");
        }
    }

    public void takeTurn(ArrayList<Droid> enemies, ArrayList<Droid> allies) {
        attack(enemies);
    }
    public Droid clone() {
        return new Droid(this.name, this.health, this.damage, this.armor);
    }
    @Override
    public String toString() {
        return " Name - " + getName() +
                " Health - " + getHealth() +
                " Armor - " + getArmor() +
                " Damage - " + getDamage();
    }
}

