package com.maginarium.controller;

import com.maginarium.model.Player;
import com.maginarium.storage.PlayerStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class GameController {
    private final PlayerStorage playerStorage;

    @PostMapping("/game/create")
    public String createGame(@RequestParam String hostName) {
        String roomCode = "NEW1";
        Player host = new Player(hostName, true);
        playerStorage.createRoom(roomCode, host);
        return roomCode;
    }

}
