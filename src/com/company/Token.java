package com.company;

/**
 * This class represents a single token in the game . It can have red or black color which is defined
 * with unicode field. It has a location in the board which is specified with x and y ; and it also
 * has a block number which means the number of block it is in .
 *
 * @author Mina Beiki
 * @version 11.04.2020
 */
public class Token {
    private int x;
    private int y;
    //the block which token is in :
    private int block;
    //specifies the color of token :
    private String unicode;
    /*
    flag is used when rotating a block . when we want to rotate , we want every token's location to be
    changes just once , so we use a flag to check if it has already been changed or not .
     */
    private int flag;

    /**
     * Makes a new token with given x and y for its location , unicode string for color , and
     * block number for specifying the block which the token is in .
     *
     * @param x       token's location x , it's a number from 0 to 5
     * @param y       token's location y , it's a number from 0 to 5
     * @param unicode string which when printed , shows the color of token
     * @param block   block number that the token is in
     */
    public Token(int x, int y, String unicode, int block) {
        this.x = x;
        this.y = y;
        this.unicode = unicode;
        this.block = block;
        this.flag = 0;
    }

    /**
     * Gets location's x of a token
     *
     * @return x of token
     */
    public int getX() {
        return x;
    }

    /**
     * Gets location's x of a token
     *
     * @return y of token
     */
    public int getY() {
        return y;
    }

    /**
     * Gets block number of token
     *
     * @return block number of token
     */
    public int getBlock() {
        return block;
    }

    /**
     * Gets color unicode of toke
     *
     * @return unicode of token
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Checks if two tokens are the same or not by checking their types and their fields' values .
     *
     * @param o object to be checked
     * @return returns true if they are the same , and false if they are not .
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return getX() == token.getX() &&
                getY() == token.getY() &&
                getBlock() == token.getBlock() &&
                getUnicode().equals(token.getUnicode());
    }

    /**
     * Returns data (x ,y and unicode ) of a token in string .
     *
     * @return string of data (x , y and unicode)
     */
    @Override
    public String toString() {
        return "Token{" +
                "x=" + x +
                ", y=" + y +
                ", unicode='" + unicode + '\'' +
                '}';
    }

    /**
     * Changes the location of a token to the given location specified by x and y .
     *
     * @param x x of new location
     * @param y y of new location
     */
    public void changePlace(int x, int y) {
        this.x = x;
        this.y = y;
        //System.out.println("new x =" + x + " new y =" + y);
    }

    /**
     * Sets the flag
     *
     * @param flag flag to be set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Gets the flag
     *
     * @return flag of token
     */
    public int getFlag() {
        return flag;
    }

}
