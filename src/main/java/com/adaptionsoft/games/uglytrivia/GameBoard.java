package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private int currentPlayer;
    private List<Player> players;

    public GameBoard() {
        this.currentPlayer = 0;
        this.players = new ArrayList<>();
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        players.add(player);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
