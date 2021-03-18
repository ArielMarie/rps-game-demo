package com.amm.rpsgame.model;

import com.amm.rpsgame.RPSObject;
import lombok.Data;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.amm.rpsgame.RPSObject.getBeats;

@Data
public class GamePlayers {

    List<Player> gamePlayers = new ArrayList<>();

    public GamePlayers(final int numberOfPlayers) {
        for(int i = 1; i < numberOfPlayers + 1; i++) {
            gamePlayers.add(new Player(Integer.toString(i), Player.chooseHand()));
        }
    }

    public boolean isWinner() {
        return gamePlayers.size() == 1;
    }

    public Player getWinner() {
        return gamePlayers.size() == 1 ? gamePlayers.get(0) : null;
    }

    public void chooseHands() {
        gamePlayers.forEach(player -> player.setRpsObject(Player.chooseHand()));
    }

    public List<RPSObject> playHands() {
        gamePlayers.forEach(player -> System.out.println(player.playHand()));
        return gamePlayers.stream().map(Player::getRpsObject).collect(Collectors.toList());
    }

    public void eliminateLosers(RPSObject winningHand) {
        val eliminatedList = gamePlayers.stream()
                .filter(player -> player.getRpsObject().equals(getBeats(winningHand)))
                .collect(Collectors.toList());
        System.out.println("Eliminated: " + eliminatedList.size());
        this.gamePlayers = this.gamePlayers.stream().filter(player -> !player.getRpsObject().equals(getBeats(winningHand))).collect(Collectors.toList());

    }



}
