package com.amm.rpsgame;

public enum RPSObject {

    ROCK,
    PAPER,
    SCISSORS;

    public static RPSObject getBeats(RPSObject rps) {
        switch(rps) {
            case ROCK:
                return SCISSORS;
            case PAPER:
                return ROCK;
            case SCISSORS:
                return PAPER;
            default:
                throw new IllegalArgumentException("Invalid RPSObject given.");
        }
    }

    public static String getWinningStatement(RPSObject rps) {
        switch(rps) {
            case ROCK:
                return "Rock beat Scissors";
            case PAPER:
                return "Paper beats Rock";
            case SCISSORS:
                return "Scissors beats Paper";
            default:
                throw new IllegalArgumentException("Invalid RPSObject given.");
        }
    }

}
