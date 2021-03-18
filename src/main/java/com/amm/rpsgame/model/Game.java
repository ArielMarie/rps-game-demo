package com.amm.rpsgame.model;

import com.amm.rpsgame.RPSObject;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.amm.rpsgame.RPSObject.*;

@Component
@RequiredArgsConstructor
public class Game {

    private final GameConfiguration config;


    public void playGame() {
        Random random = new Random();
        int numberOfPlayers = random.nextInt(config.getMaxPlayers() - config.getMinPlayers() + 1) + config.getMinPlayers();

        GamePlayers gamePlayers = new GamePlayers(numberOfPlayers);

        while(!gamePlayers.isWinner()) {

            gamePlayers.chooseHands();

            val handsInPlay = gamePlayers.playHands();

            val rockCount = getPlayedHandCount(handsInPlay, ROCK);
            val paperCount = getPlayedHandCount(handsInPlay, PAPER);
            val scissorCount = getPlayedHandCount(handsInPlay, SCISSORS);

            val winningHand = playRound(rockCount, paperCount, scissorCount);

            if(winningHand == null) {
                System.out.println("Tied\nEliminated: 0");
            } else {
                System.out.println(getWinningStatement(winningHand));
                gamePlayers.eliminateLosers(winningHand);
            }
            System.out.println("---------------------");
        }

        val winner = gamePlayers.getWinner();
        System.out.println("Player " + winner.getId() + " is the winner!");
    }

    private long getPlayedHandCount(List<RPSObject> list, RPSObject rpsObject) {
        return list.stream().filter(rpsObj -> rpsObj.equals(rpsObject)).count();
    }

    private RPSObject playRound(long rockCount, long paperCount, long scissorCount) {
        val isAllPlayed = isAllPlayed(rockCount, paperCount, scissorCount);
        val isOnePlayed = isOnePlayed(rockCount, paperCount, scissorCount);
        if (isAllPlayed && rockCount > paperCount && rockCount > scissorCount) {
            return ROCK;
        }
        else if (isAllPlayed && paperCount > rockCount && paperCount > scissorCount) {
            return PAPER;
        }
        else if (isAllPlayed && scissorCount > rockCount && scissorCount > paperCount) {
            return SCISSORS;
        }
        else if (!isAllPlayed && !isOnePlayed) {
            return getTwoPlayedWinner(rockCount, paperCount, scissorCount);
        }
        else {
            return null;
        }
    }

    private RPSObject getTwoPlayedWinner(long rockCount, long paperCount, long scissorCount) {
        if(rockCount == 0) {
            return SCISSORS;
        }
        else if (paperCount == 0) {
            return ROCK;
        }
        else if (scissorCount == 0) {
            return PAPER;
        } else {
            return null;
        }
    }

    private boolean isAllPlayed(long rockCount, long paperCount, long scissorCount) {
        return rockCount > 0 && paperCount > 0 && scissorCount > 0;
    }

    private boolean isOnePlayed(long rockCount, long paperCount, long scissorCount) {
        if (rockCount == 0 && paperCount == 0 && scissorCount > 0) {
            return true;
        }
        else if (rockCount == 0 && paperCount > 0 && scissorCount == 0) {
            return true;
        }
        else return rockCount > 0 && paperCount == 0 && scissorCount == 0;
    }


}
