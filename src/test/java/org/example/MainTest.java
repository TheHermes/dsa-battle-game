package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        assertTrue(true);
    }
    @Test
    void nameTest() {
        GameCharacter name = new Player("Peter", 100, new Weapon("Mace", 20), 0.8);
        assertEquals("Peter", name.getName());
        
    }

    @Test
    void healthPointTest() {
        GameCharacter hpTest = new Player("Stormare", 100, new Weapon("Sword", 20), 0.8);
        hpTest.takeDamage(30);
        assertEquals(70, hpTest.getHitPoints());
    }

    @Test
    void weaponTest() {
        Weapon weaponTest = new Weapon("Sword", 20);
        assertEquals("Sword", weaponTest.getName());
        assertEquals(20, weaponTest.getDamage());
    }

    @Test
    void attackPlayerMethodTest() {
        GameCharacter playerTest = new Player("George", 100, new Weapon(), 0.8);
        GameCharacter npcTest = new Npc("Comp", 100, 0.8);
        int testDmg = playerTest.attack(npcTest);
        // Attackers hp stays the same
        assertEquals(100, playerTest.getHitPoints());
        // Defenders hp should not be 100
        assertEquals(100 - testDmg, npcTest.getHitPoints());
    }
    @Test
    void attackNpcMethodTest(){
        GameCharacter playerTest = new Player("George", 100, new Weapon(), 0.8);
        GameCharacter npcTest = new Npc("Comp", 100, 0.8);
        int testDmg = npcTest.attack(playerTest);
        // Attackers hp stays the same
        assertEquals(100, npcTest.getHitPoints());
        // Defenders hp should not be 100
        assertEquals(100 - testDmg, playerTest.getHitPoints());
    }

    @Test
    void spawnNpcTest() {
        GameCharacter npcTest;
        Weapon testWeapon = new Weapon("Sword", 20);
        npcTest = Npc.spawnNpc("Monster",50, testWeapon, 0.5);

        assertEquals(50, npcTest.getHitPoints());
        assertEquals(0.5, npcTest.getDexterity());
        assertEquals("Monster", npcTest.getName());
        assertEquals("Sword", npcTest.getEquippedWeapon().getName());
        assertEquals(20, npcTest.getEquippedWeapon().getDamage());

        testWeapon = new Weapon("Club", 10);
        npcTest = Npc.spawnNpc("Ghoul", 100, testWeapon, 1);

        assertEquals(100, npcTest.getHitPoints());
        assertEquals(1, npcTest.getDexterity());
        assertEquals("Ghoul", npcTest.getName());
        assertEquals("Club", npcTest.getEquippedWeapon().getName());
        assertEquals(10, npcTest.getEquippedWeapon().getDamage());
    }

    @Test
    void testSaveGame() {
        GameCharacter saveTester = new Player("Sven", 100, new Weapon("Sword", 20), 0.8);
        String name = saveTester.getName();
        int hitPoints = saveTester.getHitPoints();
        Weapon weapon = saveTester.getEquippedWeapon();
        double dex = saveTester.getDexterity();
        String testFile = "testFile.save";

        Utils.saveGame(saveTester, testFile);
        GameCharacter loadTester = (GameCharacter) Utils.loadGame(testFile);

        assertEquals(name, loadTester.getName());
        assertEquals(hitPoints, loadTester.getHitPoints());
        assertEquals(weapon.getName(), loadTester.getEquippedWeapon().getName());
        assertEquals(dex, loadTester.getDexterity());
    }
}