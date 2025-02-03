package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String saveFile = "playerfile.save";
        GameCharacter player = null;
        System.out.println("\n| ---- The Monster Slayer ---- |");
        System.out.println("\nWelcome!\n\nCreate a new game (Enter)? or continue from saved game (cont)?");
        if (userInput.nextLine().equals("cont")) {
            try {
                player = (GameCharacter) Utils.loadGame(saveFile);
                System.out.printf("\nWelcome back %s!\n",
                        player.getName()
                );
            } catch (RuntimeException e) {
                System.out.println("No save file available, creating a new game");
            }
        }
        if (player == null) {
            System.out.println("Creating a new game.\n");
            String playerName;
            while(true) {
                System.out.print("Welcome! Enter your name: ");
                playerName = userInput.nextLine();
                if(playerName.isEmpty()) {
                    System.out.println("Write a name please!");
                } else {
                    break;
                }
            }
            System.out.print("What weapon do you have? ");
            String playerWeapon = userInput.nextLine();
            // Assign random weapon if left empty.
            if(playerWeapon.trim().isEmpty()){
                playerWeapon = Weapon.assignRandomWeapon();
            }
            // Instantiate player
            player = new Player(playerName, 100, new Weapon(playerWeapon), 0.8f);
            System.out.println(player.getInventory().get(0).getName());
        }
        GameCharacter attacker;
        GameCharacter temp;
        GameCharacter npc;
        // Loop runs while both characters have above 0 hp and breaks when either becomes 0 or bellow
        while (true) {
            // Instantiate new Npc for every loop
            npc = Npc.spawnNpc(50, 0.5f);
            GameCharacter defender = npc;
            attacker = player;
            System.out.printf("\nA %s approaches.\n",
                    npc.getName()
            );
            while (true) {
                if (attacker == player) {
                    int option;
                    while (true) {
                        System.out.print("\nWhat weapon do you want to equip?(\"delete\" to discard items (only if you have more than 1 item).");
                        System.out.println("\n\n| --- Inventory --- |");
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            System.out.printf("\n[%d] %s (%d dmg) ",
                                    i + 1,
                                    player.getInventory().get(i).getName(),
                                    player.getInventory().get(i).getDamage()
                            );
                        }
                        String delete = userInput.nextLine();
                        if (delete.equals("delete") && player.getInventory().size() > 1) {

                            while (true) {
                                System.out.print("\nChoose which item you would like to delete: or leave (q)\n");
                                System.out.println("\n| --- Inventory --- |");
                                for (int i = 0; i < player.getInventory().size(); i++) {
                                    System.out.printf("\n[%d] %s (%d dmg) ",
                                            i + 1,
                                            player.getInventory().get(i).getName(),
                                            player.getInventory().get(i).getDamage()
                                    );
                                }
                                String deleteInput = userInput.nextLine();
                                if (deleteInput.equalsIgnoreCase("q")) {
                                    break;
                                } else {
                                    try {
                                        int delOp = Integer.parseInt(deleteInput) - 1; // -1 for proper index
                                        String deletedItem = player.getInventory().get(delOp).getName();
                                        player.getInventory().remove(delOp);
                                        System.out.printf("\nYou removed %s from your inventory.\n",
                                                deletedItem
                                        );
                                        break;
                                    } catch (IndexOutOfBoundsException | NumberFormatException | InputMismatchException e) {
                                        System.out.println("\nNot a viable option, write the item number you want to delete or exit (q). ");
                                    }
                                }
                            }
                        } else {
                            try {
                                option = Integer.parseInt(delete) - 1;
                                player.setEquippedWeapon(player.getInventory().get(option));
                                System.out.printf("\nEquipped %s ",
                                        player.getEquippedWeapon().getName()
                                );
                                break;
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                System.out.println("You need to choose an item in your inventory with numbers and you have in your inventory and you cannot delete an item if you only have one item! ");
                            }
                        }
                    }
                    System.out.printf("\n\nAttack the %s (Enter)? or escape (q)? ",
                            defender.getName()
                    );
                    if (userInput.nextLine().equalsIgnoreCase("q")) {
                        System.out.printf("\nYou escape from the %s ",
                                defender.getName()
                        );
                        break;
                    }
                }
                // Player turn
                if (attacker.getHitPoints() > 0) {
                    System.out.printf("\n%s (%d hp) attacks %s (%d hp) with %s for %d hp. ",
                            attacker.getName(),
                            attacker.getHitPoints(),
                            defender.getName(),
                            defender.getHitPoints(),
                            attacker.getEquippedWeapon().getName(),
                            attacker.attack(defender) // Attack method for lowering targets hp
                    );
                    System.out.printf("\n -> %s has %d hp left.\n",
                            defender.getName(),
                            Math.max(defender.getHitPoints(), 0) // Output 0 if defender hp < 0
                    );
                }
                temp = attacker;
                attacker = defender;
                defender = temp;
                // Player defeats the opponent
                if (npc.getHitPoints() <= 0) {
                    System.out.printf("\nYou defeated the %s!\n", npc.getName());
                    System.out.printf("%s dropped a %s (%d dmg).\nWould you like to pick it up (yes/no)? ",
                            npc.getName(),
                            npc.getEquippedWeapon().getName(),
                            npc.getEquippedWeapon().getDamage()
                    );
                    if (userInput.nextLine().equalsIgnoreCase("yes")){
                        player.getInventory().add(npc.getEquippedWeapon());
                        System.out.printf("You picked up the %s\n ",
                                player.getInventory().get(player.getInventory().size()-1).getName()
                        );
                    }
                    break;
                }
                // Player loses
                if (player.getHitPoints() <= 0) {
                    System.out.printf("\n%s (%d hp), killed you! You lose!\n\n| --- GAME OVER --- |\n",
                            npc.getName(),
                            npc.getHitPoints()
                    );
                    System.exit(0);
                    break;
                }
            }
            // Healing
            if (player.getHitPoints() < 100) {
                System.out.printf("\nCurrent Health: %d\n", player.getHitPoints());
                System.out.printf("\nWould you like to heal (Range: %d - %d) (yes/no)?\n",
                        5,
                        (100 - player.getHitPoints())/2
                );
                if (userInput.nextLine().equalsIgnoreCase("yes")) {
                    int healAmount = player.heal();
                    System.out.printf("Amount healed: %d", healAmount);
                    System.out.printf(" Hp after heal: %d", player.getHitPoints() );
                }
            }
            // Save game
            System.out.println("\nWould you like to save your game (yes)? Or continue without(enter). ");
            String inputSave = userInput.nextLine().trim().toLowerCase();
            if (inputSave.equals("yes") || inputSave.equals("y") || inputSave.equals("ye")) {
                Utils.saveGame(player, saveFile);
                System.out.println("Game Saved! ");
            }
            System.out.print("\nPlay another round (Enter)? or Leave with your life still intact (q)? ");
            if (userInput.nextLine().equalsIgnoreCase("q")) {
                System.out.println("Thanks for playing! ");
                System.exit(0);
                break;
            }
        }
    }
}