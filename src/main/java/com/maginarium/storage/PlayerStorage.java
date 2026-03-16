package com.maginarium.storage;

import com.maginarium.model.Player;
import com.maginarium.model.Room;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerStorage {

    private Map<String, Room> rooms = new HashMap<>();

    public void createRoom (String roomCode, Player host) {
        Room newRoom = new Room(roomCode);
        newRoom.addPlayer(host);
        rooms.put(roomCode, newRoom);
    }

}
