package com.maginarium.storage;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PlayerStorage {

    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers(String roomCode) {
        return players.stream()
                .filter(player -> player.roomCode.equals(roomCode))
                .toList();
    }

    @RequiredArgsConstructor
    @Builder
    public static class Player {

        @NotNull
        private final String name;
        @NotNull
        private final String roomCode;
        private final boolean isAdmin = false;

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Player player)) return false;
            return Objects.equals(name, player.name) && Objects.equals(roomCode, player.roomCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, roomCode);
        }
    }
}

