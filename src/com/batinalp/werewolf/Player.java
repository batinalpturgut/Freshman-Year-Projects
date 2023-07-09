package com.batinalp.werewolf;

public class Player {
    private static int totalAlivePlayerCount;
    private PlayerRoles playerRole;
    private boolean isAlive;
    private String playerName;
    private boolean isProtected;

    public Player(PlayerRoles playerRole, String playerName) {
        totalAlivePlayerCount++;
        this.playerRole = playerRole;
        this.playerName = playerName;
        isAlive = true;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public PlayerRoles getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRoles playerRole) {
        this.playerRole = playerRole;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void kill(){
        setAlive(false);
    }

    public static int getTotalAlivePlayerCount() {
        return totalAlivePlayerCount;
    }

    public static void setTotalAlivePlayerCount(int totalPlayerCount) {
        Player.totalAlivePlayerCount = totalPlayerCount;
    }
}