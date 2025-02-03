package org.example;

import java.io.Serializable;
import java.util.Random;

public class Weapon implements Serializable {

    String name;
    int damage;
    public Weapon() {
        name = assignRandomWeapon();
        damage = Utils.randomNumber(10, 25);
    }
    public Weapon(String name) {
        this.name = name;
        damage = Utils.randomNumber(10, 25);
    }
    public Weapon(int damage) {
        name = assignRandomWeapon();
        this.damage = damage;
    }
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // Methods
    public static String assignRandomWeapon() {
        String[] weapons = {
                "Knife", "Mace", "Sword", "Bow", "Flail", "Morningstar", "Staff", "Crossbow", "Pike", "Spear", "Dagger", "Polearm", "Halberd", "Axe", "Longbow", "Javelin", "Rapier", "Scythe", "Trident", "Scimitar", "Warhammer", "Falchion"
        };
        Random random = new Random();
        int rand = random.nextInt(0, weapons.length);
        return weapons[rand];
    }

    // Getter & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
