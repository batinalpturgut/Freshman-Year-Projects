package com.batinalp.werewolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private final String[] playerNames;

    private int protectedPlayerIndex;

    private int vampireIndex;

    private int doctorIndex;

    private final Player[] players;

    public Game() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of players: ");
        int totalPlayers = scan.nextInt();
        while (totalPlayers <= 2) {
            System.out.println("Invalid value. Try again.");
            System.out.println("Enter the number of players: ");
            totalPlayers = scan.nextInt();
        }

        players = new Player[totalPlayers];
        playerNames = new String[totalPlayers];
        setPlayerNames();
    }

    public void setPlayerNames() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < playerNames.length; i++) {
            System.out.println("Please enter player " + (i + 1) + " name");
            playerNames[i] = scan.nextLine();
        }

        for (int i = 0; i < playerNames.length; i++) {
            System.out.println("Player " + (i + 1) + ": " + playerNames[i]);
        }
        System.out.println();

        createPlayersAndRandomRoles();
    }

    public void createPlayersAndRandomRoles() {
        ArrayList<PlayerRoles> roles = new ArrayList<>();

        roles.add(PlayerRoles.DOCTOR);
        roles.add(PlayerRoles.VAMPIRE);

        for (int i = 0; i < players.length - 2; i++) {
            roles.add(PlayerRoles.VILLAGER);
        }

        Collections.shuffle(roles);

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(roles.get(i), playerNames[i]);
        }

        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getPlayerName() + " : " + players[i].getPlayerRole());
        }
        System.out.println();

        this.doctorIndex = roles.indexOf(PlayerRoles.DOCTOR);
        this.vampireIndex = roles.indexOf(PlayerRoles.VAMPIRE);

        gameplay();
    }

    public void gameplay() {

        while (true) {

            doctorGameplay();

            vampireGameplay();

            if (!isContinuing()) {
                System.out.println("Vampire won.");
                System.out.println("Vampire was: " + players[vampireIndex].getPlayerName());
                break;
            }

            while (!voteSystem()) {
                System.out.println("Vote result is a draw. Try again.\n");
            }

            if (!players[vampireIndex].isAlive()) {
                System.out.println("Villagers won.");
                System.out.println("Vampire was: " + players[vampireIndex].getPlayerName());
                break;
            }

        }
    }

    public void doctorGameplay() {
        if (players[doctorIndex].isAlive()) {

            Scanner scan = new Scanner(System.in);

            System.out.println("Doctor wakes.\n");
            playerStatus();
            System.out.println("Choose who you want to protect ?");
            protectedPlayerIndex = scan.nextInt() - 1;

            while (protectedPlayerIndex < 0 || protectedPlayerIndex > players.length - 1 ||
                    !players[protectedPlayerIndex].isAlive()) {
                System.out.println("Invalid value. Please choose another player.");
                System.out.println("Choose who you want to protect ?");
                protectedPlayerIndex = scan.nextInt() - 1;
            }

            players[protectedPlayerIndex].setProtected(true);

        } else
            System.out.println("Doctor is dead.\n");
    }


    public void vampireGameplay() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Vampire wakes.\n");
        playerStatus();
        System.out.println("Choose who you want to kill ?");
        int chosenPlayerIndex = scan.nextInt() - 1;


        while (chosenPlayerIndex < 0 || chosenPlayerIndex > players.length - 1 ||
                !players[chosenPlayerIndex].isAlive()) {
            System.out.println("Invalid value. Please choose another player.\n");
            playerStatus();
            System.out.println("Choose who you want to kill ?");
            chosenPlayerIndex = scan.nextInt() - 1;
        }

        while (PlayerRoles.VAMPIRE == players[chosenPlayerIndex].getPlayerRole()) {
            System.out.println("You cannot kill yourself.\n");
            playerStatus();
            System.out.println("Choose who you want to kill ?");
            chosenPlayerIndex = scan.nextInt() - 1;
        }

        if (!players[chosenPlayerIndex].isProtected()) {

            System.out.println(players[chosenPlayerIndex].getPlayerName() + " is killed by vampire.\n");
            Player.setTotalAlivePlayerCount(Player.getTotalAlivePlayerCount() - 1);
            players[chosenPlayerIndex].kill();

        } else
            System.out.println("No one killed. Doctor was successfull.\n");


        players[protectedPlayerIndex].setProtected(false);
    }

    public boolean isContinuing() {
        return Player.getTotalAlivePlayerCount() > 2;
    }

    public boolean voteSystem() {

        Scanner scan = new Scanner(System.in);
        int[] votes = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            if (players[i].isAlive()) {
                playerStatus();
                System.out.println(players[i].getPlayerName() + " please  enter your vote.");
                int vote = scan.nextInt() - 1;

                while (vote > players.length - 1 || vote < 0 || vote == i || !players[vote].isAlive()) {
                    System.out.println("Invalid vote. Try again.\n");
                    playerStatus();
                    System.out.println(players[i].getPlayerName() + " please  enter your vote.");
                    vote = scan.nextInt() - 1;
                }

                votes[vote]++;
            }
        }

        System.out.println("All votes: ");
        for (int i = 0; i < votes.length; i++) {
            System.out.println(players[i].getPlayerName() + " votes : " + votes[i]);
        }
        System.out.println();

        int maxIndex = 0;
        for (int i = 0; i < votes.length; i++) {
            if (votes[i] > votes[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = 0; i < votes.length; i++) {
            if (i != maxIndex && votes[i] == votes[maxIndex]) {
                return false;
            }
        }

        players[maxIndex].kill();
        Player.setTotalAlivePlayerCount(Player.getTotalAlivePlayerCount() - 1);
        System.out.println(players[maxIndex].getPlayerName() + " is killed by majority vote.\n");

        return true;
    }

    public void playerStatus() {
        for (int i = 0; i < players.length; i++) {
            System.out.println((i + 1) + " - " + players[i].getPlayerName() + " is alive ? : " + players[i].isAlive());
        }
        System.out.println();
    }
}