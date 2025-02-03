package org.example;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.round;

abstract class GameCharacter implements Serializable {
    private String name;
    private int hitPoints;
    private Weapon equippedWeapon;
    public double dexterity;
    ArrayList<Weapon> inventory;

    public GameCharacter(String name, int hitPoints, double dexterity){
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = new Weapon("Club");
        this.dexterity = dexterity;
    }

    public GameCharacter(String name, int hitPoints, Weapon equippedWeapon, double dexterity){
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = equippedWeapon;
        this.dexterity = dexterity;
        inventory = new ArrayList<>();
        inventory.add(equippedWeapon);
    }
    public GameCharacter(String name, Weapon equippedWeapon, double dexterity) {
        this.name = name;
        this.hitPoints = Utils.randomNumber(10, 20);
        this.equippedWeapon = equippedWeapon;
        this.dexterity = dexterity;
    }
    public void takeDamage(int damage) {
        hitPoints = hitPoints - damage;
    }
    public int attack(GameCharacter defender) {
        int dmg = Utils.randomNumber((int) (equippedWeapon.getDamage() * this.getDexterity()), equippedWeapon.getDamage());
        defender.setHitPoints( defender.getHitPoints() - dmg );  ;
        return dmg;
    }
    public int heal() {
        int maxHeal = (100 - this.getHitPoints())/2;
        int minHeal = 5;
        int healAmount = Utils.randomNumber(minHeal, maxHeal);
        int healResult = this.getHitPoints() + healAmount;
        this.setHitPoints(Math.min(healResult, 100)); // Max hp is 100, if healResult (current hp + healing amount) is above 100 then it sets to max hp 100
        return healAmount;
    }
    // Getters & Setters

        // Getters
    public String getName() {
        return name;
    }
    public int getHitPoints() {
        return hitPoints;
    }
        // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Weapon> inventory) {
        this.inventory = inventory;
    }
}
