package org.example;

import java.io.Serializable;
import java.util.Random;

public class Npc extends GameCharacter implements Serializable {
    static GameCharacter spawnNpc(int hp, double dexterity) {
        String name = randomNpcName();
        return new Npc(name, hp, dexterity);
    }
    static GameCharacter spawnNpc(String name, int hp, Weapon equippedWeapon, double dexterity) {
        return new Npc(name, hp, equippedWeapon, dexterity);
    }
    static Random rand = new Random();
    // Random damage for NPC
    static int dmg = Utils.randomNumber(10, 25);
    public Npc(String name, int hitPoints, Weapon equippedWeapon, double dexterity) {
        super(name, hitPoints, equippedWeapon, dexterity);
    }
    public Npc(String name, int hitPoints, double dexterity) {
        super(name, hitPoints, new Weapon(Utils.randomNumber(10,25)), dexterity);
    }
    public Npc(int hitPoints, double dexterity) {
        super(randomNpcName(), hitPoints, new Weapon(Utils.randomNumber(10, 25)), dexterity);
    }
    // Generates a random monster on instantiation
    public static String randomNpcName() {
        String[] npcNames = {
                "Monster", "Ghoul", "Zombie", "Clown", "Wolfman", "Goblin", "Thief", "Witch", "Marauder", "Ghost", "Mutant", "Troll", "Orc", "Skeleton", "Vampire", "Minotaur", "Specter", "Demon"
        };
        return npcNames[rand.nextInt(0, npcNames.length)];
    }

}
