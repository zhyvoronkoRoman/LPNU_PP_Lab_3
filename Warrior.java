package Droids;

public class Warrior extends Droid {

    public Warrior(String name, double health, double damage, double armor){
        super(name,health,damage,armor);
    }
    public Warrior clone() {
        return new Warrior(getName(), getHealth(), getDamage(), getArmor());
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
