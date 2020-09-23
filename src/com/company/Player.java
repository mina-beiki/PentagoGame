package com.company;

import java.util.ArrayList;

/**
 * This class is used for showing a player in the game .It has a unicode which is the color for
 * all of the tokens added to board , and list of all tokens added . We can add a new token for
 * a player .
 *
 * @author Mina Beiki
 * @version 11.04.2020
 */
public class Player {
    //specifies the player tokens unicode :
    String unicode;
    //list of all the tokens player has :
    private ArrayList<Token> tokens;

    /**
     * Makes a new player with given color unicode .
     *
     * @param unicode color chosen for the player
     */
    public Player(String unicode) {
        this.unicode = unicode;
        tokens = new ArrayList<>();
    }

    /**
     * Adds a new token to a players list of tokens after checking if it already
     * exists or not .
     *
     * @param newToken new token to be added
     */
    public void addNewToken(Token newToken) {
        int flag = 0;
        //We check if the token is already in the list or not :
        for (Token token : tokens) {
            if (token.equals(newToken)) {
                flag = 1;
                break;
            }
        }
        //if the token is not repeated :
        if (flag == 0)
            tokens.add(newToken);
    }

    /**
     * Gets color unicode of a player
     *
     * @return unicode string
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Gets tokens of a player
     *
     * @return Array list of tokens
     */
    public ArrayList<Token> getTokens() {
        return tokens;
    }

}
