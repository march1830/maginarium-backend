package com.maginarium.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerStorageTest {

    @Autowired
    private PlayerStorage playerStorage;

    @Test
    public void canAddPlayer() {
        // given
        var roomCode = "test";
        var player = PlayerStorage.Player.builder()
                .name("test")
                .roomCode(roomCode)
                .build();

        // when
        playerStorage.addPlayer(player);

        // then
        assertThat(playerStorage.getPlayers(roomCode)).containsExactly(player);
    }

}