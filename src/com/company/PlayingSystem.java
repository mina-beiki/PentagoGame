package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the most important class in the structure of this game .It controls the process of
 * playing such as changing turns , adding tokens , checking if there is a winning , checking
 * if there is a tie , rotating blocks and computers playing functions .
 *
 * @author Mina Beiki
 * @version 11.04.2020
 */
public class PlayingSystem {
    //red:"\uD83D\uDD34" , black:"\u26AB"
    //2 players of the game :
    private Player player1;
    private Player player2;
    //list of all tokens :
    private ArrayList<Token> allTokens;
    //list of tokens added in each block:
    private ArrayList<Token> block1tokens;
    private ArrayList<Token> block2tokens;
    private ArrayList<Token> block3tokens;
    private ArrayList<Token> block4tokens;
    private int turn;

    /**
     * Makes a new playing system with 2 players . Players' colors are chosen
     * randomly and each time it differs from the last time . It is assumed that
     * player1 starts the game .
     */
    public PlayingSystem() {
        String unicode1 = "";
        String unicode2 = "";
        Random random = new Random();
        //for finding random unicodes for colors :
        int index = random.nextInt(2) + 1;
        if (index == 1) {
            unicode1 = "\uD83D\uDD34"; //Red
            unicode2 = "\u26AB"; //Black
        } else if (index == 2) {
            unicode1 = "\u26AB"; //Black
            unicode2 = "\uD83D\uDD34"; //Red
        }
        player1 = new Player(unicode1);
        player2 = new Player(unicode2);
        allTokens = new ArrayList<>();
        block1tokens = new ArrayList<>();
        block2tokens = new ArrayList<>();
        block3tokens = new ArrayList<>();
        block4tokens = new ArrayList<>();
        //we assume player 1 starts the game :
        turn = 1;
    }

    /**
     * Adds a new token to board . Check is the token is not already in the list and then
     * adds it to list of all tokens and list of the block tokens the token is in .
     *
     * @param x       x of new token
     * @param y       y of new token
     * @param unicode color unicode of token
     */
    public void addNewToken(int x, int y, String unicode) {
        int flag = 0;
        //checks if x and y exist in map :
        if (x < 6 && y < 6) {
            int block = findBlock(x, y);
            Token newToken = new Token(x, y, unicode, block);
            //Check if we already have this token or not :
            for (Token token : allTokens) {
                if (token.equals(newToken)) {
                    flag = 1;
                    break;
                }
            }
            //if the token is not repeated in the list :
            if (flag == 0) {
                allTokens.add(newToken);
                if (block == 1)
                    block1tokens.add(newToken);
                else if (block == 2)
                    block2tokens.add(newToken);
                else if (block == 3)
                    block3tokens.add(newToken);
                else if (block == 4)
                    block4tokens.add(newToken);
                if (turn == 1)
                    player1.addNewToken(newToken);
                else if (turn == 2)
                    player2.addNewToken(newToken);
            }
        }
    }

    /**
     * Prints the board with tokens in it .
     */
    public void printBoard() {
        int flag = 0;
        //i is sanding for y
        for (int i = 0; i < 6; i++) {
            //j is standing for x
            for (int j = 0; j < 6; j++) {
                flag = 0;
                //if there is a token in that place , prints the token :
                for (Token token : allTokens) {
                    if (token.getX() == j && token.getY() == i) {
                        if (j == 2)
                            System.out.print("  " + token.getUnicode() + " |");
                        else
                            System.out.print("  " + token.getUnicode() + " ");
                        flag = 1;
                    }
                }
                //if there is not a token there , prints a white circle :
                if (flag == 0) {
                    if (j == 2)
                        System.out.print("  " + "\u26AA" + " |");
                    else
                        System.out.print("  " + "\u26AA" + " ");
                }
            }
            if (i == 2) {
                System.out.println();
                for (int k = 0; k < 4; k++) {
                    System.out.print("  --  --");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    /**
     * By giving x and y it find the block given location is in .
     *
     * @param x x of location
     * @param y y of location
     * @return block number
     */
    public int findBlock(int x, int y) {
        int block = 0;
        if (x < 3 && y < 3)
            block = 1;
        else if (x > 2 && y < 3)
            block = 2;
        else if (x < 3 && y > 2)
            block = 3;
        else if (x > 2 && y > 2)
            block = 4;
        return block;
    }

    /**
     * Prints data of playing system such as each block tokens and all tokens . It is used for debugging .
     */
    public void printData() {
        System.out.println("All tokens : ");
        for (Token t : allTokens) {
            System.out.println(t);
        }
        System.out.println("block 1 tokens:");
        for (Token t : block1tokens) {
            System.out.println(t);
        }
        System.out.println("block 2 tokens:");
        for (Token t : block2tokens) {
            System.out.println(t);
        }
        System.out.println("block 3 tokens:");
        for (Token t : block3tokens) {
            System.out.println(t);
        }
        System.out.println("block 4 tokens:");
        for (Token t : block4tokens) {
            System.out.println(t);
        }
        System.out.println("Player 1 tokens:");
        for (Token t : player1.getTokens()) {
            System.out.println(t);
        }
        System.out.println("Player 2 tokens:");
        for (Token t : player2.getTokens()) {
            System.out.println(t);
        }
    }

    /**
     * Checks if there is a winning or not . It checks if there is any 5 tokens of the given string in the same line.
     * That line can be straight , perpendicular , and 2 kinds of diagonal .All these kinds of lines are
     * checked .
     *
     * @param unicode color unicode which we want to check winning of it
     * @return true , if it has won and false if it has not .
     */
    public boolean checkIfWin(String unicode) {
        int i = 0, j = 0;
        for (int k = 0; k < 4; k++) {
            //straight line :
            if (k == 0) {
                i = 1;
                j = 0;
            }
            //perpendicular line :
            if (k == 1) {
                i = 0;
                j = 1;
            }
            //diagonal line from left down to right up :
            if (k == 2) {
                i = 1;
                j = 1;
            }
            //diagonal line from right down to left up :
            if (k == 3) {
                i = -1;
                j = 1;
            }
            /*
            Algorithm is that we check behind and after the token for a sequence of 5 or more , ctr helps
            us count the number of tokens in the sequence .
             */
            for (Token token : allTokens) {
                if (token.getUnicode().equals(unicode)) {
                /*ctr starts from -1 because every time the while loop starts , ctr counts itself too , and
                we just need itself to be counted once so we start it from -1 , the first while loop it is not counted
                the second while loop it is counted , so it is counted once as we wanted it .*/
                    int ctr = -1;
                    int yUpper = token.getY();
                    int yLower = token.getY();
                    int xBackward = token.getX();
                    int xForward = token.getX();
                    //checks if there is a token after it and the color is the same :
                    while (checkTokenExistence(xForward, yUpper) && unicode.equals(getTokenUnicode(xForward, yUpper))) {
                        ctr++;
                        xForward += i;
                        yUpper -= j;
                    }
                    //checks if there is a token behind it and the color is the same :
                    while (checkTokenExistence(xBackward, yLower) && unicode.equals(getTokenUnicode(xBackward, yLower))) {
                        ctr++;
                        xBackward -= i;
                        yLower += j;
                    }
                    //checks the number of tokens in the sequence :
                    if (ctr >= 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if in the given location a token exits or not .
     *
     * @param x x of location to be checked
     * @param y y of location to be checked
     * @return true if it exists and false if it doesn't exist .
     */
    public boolean checkTokenExistence(int x, int y) {
        for (Token token : allTokens) {
            if (token.getX() == x && token.getY() == y) {
                //token is found :
                return true;
            }
        }
        //token is not found :
        return false;
    }

    /**
     * Gets color unicode of a token in the given location . If there is no token there , returns an empty string .
     *
     * @param x x of location to be checked
     * @param y y of location to be checked
     * @return color unicode string
     */
    public String getTokenUnicode(int x, int y) {
        //finds the token and returns its unicode :
        for (Token token : allTokens) {
            if (token.getX() == x && token.getY() == y) {
                return token.getUnicode();
            }
        }
        return "";
    }

    /**
     * Rotates a block in clock wise direction ; based on what block it is , it has a different algorithm
     * to run so it is implemented for all four blocks .
     *
     * @param blockTokens Array list of tokens in that block
     * @param blockNumber number of block to be rotated
     */
    public void rotateClockwise(ArrayList<Token> blockTokens, int blockNumber) {
        /*
        flag for each token defines if it already has been changed or not , because we want to change every token
        just once . So first we set all flags to 0 .
         */
        for (Token token : blockTokens) {
            token.setFlag(0);
        }
        //clockwise rotation algorithm for block number 1 :
        int x = 0, y = 0;
        int ctr, ctr2;
        if (blockNumber == 1) {
            for (x = 0; x <= 2; x++) {
                ctr = -1;
                for (y = 2; y >= 0; y--) {
                    ctr++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            if (token.getFlag() == 0) {
                                token.changePlace(ctr, x);
                                //once a token is changed , we set its flag to 1 :
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //clockwise rotation algorithm for block number 2 :
        if (blockNumber == 2) {
            ctr2 = -1;
            for (x = 3; x <= 5; x++) {
                ctr = -1;
                ctr2++;
                for (y = 2; y >= 0; y--) {
                    ctr++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            //System.out.println("old x = " + token.getX() + " old y =" + token.getY());
                            //System.out.println("new x =" + ctr + " new y =" + x);
                            if (token.getFlag() == 0) {
                                token.changePlace(ctr + 3, ctr2);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //clockwise rotation algorithm for block number 3 :
        if (blockNumber == 3) {
            ctr2 = 2;
            for (x = 0; x <= 2; x++) {
                ctr = -1;
                ctr2++;
                for (y = 5; y >= 3; y--) {
                    ctr++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            //System.out.println("old x = " + token.getX() + " old y =" + token.getY());
                            //System.out.println("new x =" + ctr + " new y =" + x);
                            if (token.getFlag() == 0) {
                                token.changePlace(ctr, ctr2);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //clockwise rotation algorithm for block number 4 :
        if (blockNumber == 4) {
            for (x = 3; x <= 5; x++) {
                ctr = -1;
                for (y = 5; y >= 3; y--) {
                    ctr++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            //System.out.println("old x = " + token.getX() + " old y =" + token.getY());
                            //System.out.println("new x =" + ctr + " new y =" + x);
                            if (token.getFlag() == 0) {
                                token.changePlace(ctr + 3, x);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }


    }

    /**
     * Rotates a block in anti clock wise direction ; based on what block it is , it has a different algorithm
     * to run so it is implemented for all four blocks .
     *
     * @param blockTokens Array list of tokens in that block
     * @param blockNumber number of block to be rotated
     */
    public void rotateAnticlockwise(ArrayList<Token> blockTokens, int blockNumber) {
         /*
        flag for each token defines if it already has been changed or not , because we want to change every token
        just once . So first we set all flags to 0 .
         */
        for (Token token : blockTokens) {
            token.setFlag(0);
        }
        //anticlockwise rotation algorithm for block number 1 :
        int x = 0, y = 0;
        int ctr, ctr2;
        if (blockNumber == 1) {
            ctr = -1;
            for (x = 2; x >= 0; x--) {
                ctr++;
                for (y = 0; y <= 2; y++) {
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            if (token.getFlag() == 0) {
                                token.changePlace(y, ctr);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //anticlockwise rotation algorithm for block number 2 :
        if (blockNumber == 2) {
            ctr2 = -1;
            for (x = 5; x >= 3; x--) {
                ctr = -1;
                ctr2++;
                for (y = 0; y <= 2; y++) {
                    ctr++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            if (token.getFlag() == 0) {
                                //System.out.println("old x = " + token.getX() + " old y =" + token.getY());
                                token.changePlace(ctr + 3, ctr2);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //anticlockwise rotation algorithm for block number 3 :
        if (blockNumber == 3) {
            ctr = -1;
            for (x = 2; x >= 0; x--) {
                ctr++;
                ctr2 = -1;
                for (y = 3; y <= 5; y++) {
                    ctr2++;
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            if (token.getFlag() == 0) {
                                token.changePlace(ctr2, ctr + 3);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }
        //anticlockwise rotation algorithm for block number 4 :
        if (blockNumber == 4) {
            ctr = 2;
            for (x = 5; x >= 3; x--) {
                ctr++;
                for (y = 3; y <= 5; y++) {
                    for (Token token : blockTokens) {
                        if (token.getX() == x && token.getY() == y) {
                            if (token.getFlag() == 0) {
                                token.changePlace(y, ctr);
                                token.setFlag(1);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Sets turn to the given turn number .
     *
     * @param turn new turn number to be set
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Controls over turns changing process and change turns .
     */
    public void playTurn() {
        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
    }

    /**
     * Get current turn number .
     *
     * @return turn number
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Gets turn number and find the color unicode string for the player playing .
     *
     * @param turn turn number
     * @return color unicode string
     */
    public String findUnicode(int turn) {
        if (turn == 1)
            return player1.getUnicode();
        else if (turn == 2)
            return player2.getUnicode();
        return "";
    }

    /**
     * Gets all tokens in specified block by using the given block number .
     *
     * @param blockNumber number of the block which we want the list of tokens in it
     * @return Array list of tokens in that block
     */
    public ArrayList<Token> getBlockTokens(int blockNumber) {
        if (blockNumber == 1)
            return block1tokens;
        if (blockNumber == 2)
            return block2tokens;
        if (blockNumber == 3)
            return block3tokens;
        //else the block number should be 4 :
        return block4tokens;
    }

    /**
     * Checks if after a rotation , there is 2 different sequences of both colors and if there is no more
     * available movements and no one has won .
     *
     * @return true if there is a tie , and false if there isn't
     */
    //red:"\uD83D\uDD34" , black:"\u26AB"
    public boolean checkTie() {
        String red = "\uD83D\uDD34";
        String black = "\u26AB";
        if (allTokens.size() == 36)
            return true;
        if (checkIfWin(red) && checkIfWin(black))
            return true;
        return false;
    }

    /**
     * Prints the menu for the start of game .
     */
    public void printMenu() {
        System.out.println("<< Welcome to Pentago Game ! >>");
        System.out.println("Choose your mode : (Just type the index number )");
        System.out.println("1.Player VS player");
        System.out.println("2.Player VS Computer");
    }

    /**
     * Simple computer playing method which works randomly every time . Finds a random
     * place to put a token (checks if it's empty) and random block and direction ro rotate .
     *
     * @return Array list of in order : x , y , block number , direction number
     * (direction number : 1 is for clockwise and 2 is for anticlockwise)
     */
    public ArrayList<Integer> playComputer() {
        ArrayList<Integer> result = new ArrayList<>();
        int x = 0, y = 0;
        Random random = new Random();
        //it checks every time that the place is empty or not , if it's not empty then th loop runs again
        //and anoother random numbers are generated:
        while (true) {
            x = random.nextInt(6);
            y = random.nextInt(6);
            if (!checkTokenExistence(x, y))
                break;
        }
        result.add(x);
        result.add(y);
        int block = random.nextInt(4) + 1;
        int direction = random.nextInt(2) + 1;
        result.add(block);
        result.add(direction);
        return result;
    }
}

