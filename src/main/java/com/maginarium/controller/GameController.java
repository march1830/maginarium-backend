package com.maginarium.controller;

import com.maginarium.model.Player;
import com.maginarium.model.Room;
import com.maginarium.storage.PlayerStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class GameController {
    private final PlayerStorage playerStorage;

    @PostMapping("/game/create")
    public ResponseEntity<String> createGame(@RequestParam String name) {
        Player host = new Player(name, true);
        String roomCode = playerStorage.createRoom(host);
        return ResponseEntity.ok(roomCode);
    }

    @GetMapping("/game/lobby")
    public ResponseEntity<Room> getLobby(@RequestParam String roomCode) {
        if (!playerStorage.existRoom(roomCode)) {
            return ResponseEntity.notFound().build();
        }
        Room foundRoom = playerStorage.getRoom(roomCode);
        return ResponseEntity.ok(foundRoom);
    }

    @PostMapping("/game/join")
    public ResponseEntity<String> joinGame(@RequestParam String name, @RequestParam String roomCode) {
        if (!playerStorage.existRoom(roomCode)) {
            return ResponseEntity.notFound().build();
        }
        Room foundRoom = playerStorage.getRoom(roomCode);
        synchronized (foundRoom) {
            for (Player player : foundRoom.getPlayers()) {
                if (player.name().equalsIgnoreCase(name)) {
                    return ResponseEntity.badRequest().body("Name is already taken");
                }
            }
            Player player = new Player(name, false);
            foundRoom.addPlayer(player);
            return ResponseEntity.ok(roomCode);
        }
    }
}
