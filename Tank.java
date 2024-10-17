package Droids;
import Main.Main;

import java.util.*;
import static BattleField.DroidBattle.delay;
public class Tank  extends Droid {
    private double armorCorrosion;
    public Tank(String name, double health, double damage, double armor,double armorCorrosion){
        super(name,health,damage,armor);
        this.armorCorrosion = armorCorrosion;
    }
    public double getArmorCorrosion() {
        return armorCorrosion;
    }
    public void setArmorCorrosion(double armorCorrosion) {
        this.armorCorrosion = armorCorrosion;
    }

    @Override
    public void attack(ArrayList<Droid> enemies) {


        Random random = new Random();
        if(enemies.size() == 0){return;}
        int enemyIndex = random.nextInt(enemies.size());

        Droid enemy = enemies.get(enemyIndex);
        if (!enemy.isAlive()) return;
        Main.printWithFile("\u001B[31m" + getName() + " attack " + enemy.getName()+ "\n");
        enemy.takeDamage(getDamage());

        double reducedArmor = enemy.getArmor() * (armorCorrosion / 100);
        enemy.setArmor(enemy.getArmor() - reducedArmor);
        delay();
        Main.printWithFile("\u001B[33m"+getName() + " reduced armor " + enemy.getName() + " by " + reducedArmor + "%."+ "\n");

        if (!enemy.isAlive()) {
            enemies.remove(enemy);
            Main.printWithFile("\u001B[31m"+enemy.getName() + " has been destroyed!"+ "\n");
        }
    }
    public Tank clone() {
        return new Tank(getName(), getHealth(), getDamage(), getArmor(),getArmorCorrosion());
    }
    @Override
    public String toString() {
        return super.toString()+
                " Armor corrosion " + armorCorrosion +
                '%';
    }
}
