package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends GameCharacter implements Serializable {
    public Player(String name, int hitPoints, Weapon equippedWeapon, double dexterity) {
        super(name, hitPoints, equippedWeapon, dexterity);
    }
}
