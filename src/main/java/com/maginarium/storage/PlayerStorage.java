package com.maginarium.storage;

import com.maginarium.model.Player;
import com.maginarium.model.Room;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PlayerStorage {

    private Map<String, Room> rooms;
    private static final String ALPHABET = "ABCDEFGHJKMNPQRSTUVWXYZ";
    private static final int CODE_LENGTH = 4;

    public String createRoom (Player host) {
        String roomCode;
        do {
            roomCode = generateCode();
        } while (rooms.containsKey(roomCode));

        Room newRoom = new Room(roomCode);
        newRoom.addPlayer(host);
        rooms.put(roomCode, newRoom);
        return roomCode;
    }

    public Room getRoom (String roomCode) {
        return rooms.get(roomCode);
    }

    public boolean existRoom (String roomCode) {
        return rooms.containsKey(roomCode);
    }

    private String generateCode () {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(ALPHABET.length());
            code.append(ALPHABET.charAt(randomIndex));
        }
        return code.toString();

    }

}


