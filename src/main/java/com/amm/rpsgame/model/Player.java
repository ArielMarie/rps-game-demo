package com.amm.rpsgame.model;

import com.amm.rpsgame.RPSObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@AllArgsConstructor
@Data
public class Player {

    String id;
    RPSObject rpsObject;

    public static RPSObject chooseHand() {
        Random random = new Random();
        RPSObject[] rps = RPSObject.values();
        return rps[random.nextInt(rps.length)];
    }

    public String playHand() {
        return "Player " + id + " threw " + rpsObject.toString().toLowerCase();
    }
}
