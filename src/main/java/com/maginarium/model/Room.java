package com.maginarium.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String roomCode;
    private List<Player> players = new ArrayList<>();

    public Room (String code) {
        this.roomCode = code;
    }

    public void addPlayer (Player player) {
        players.add(player);
    }
    public void removePlayer (Player player) {
        players.remove(player);
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
