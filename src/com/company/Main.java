package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for testing the game .
 */
public class Main {

    public static void main(String[] args) {
        String red = "\uD83D\uDD34";
        String black = "\u26AB";
        PlayingSystem myGame = new PlayingSystem();


        Scanner scanner = new Scanner(System.in);
        myGame.setTurn(2);


        myGame.printMenu();
        int input = scanner.nextInt();
        //initial board printing :
        myGame.printBoard();
        //player vs player mode :
        if (input == 1) {
            while (true) {
                //changes turn :
                myGame.playTurn();
                if (myGame.getTurn() == 1)
                    System.out.println("Player 1 : ");
                else if (myGame.getTurn() == 2)
                    System.out.println("Player 2 : ");
                System.out.println("New token place : (Please enter as :x y , for example :0 0)");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                myGame.addNewToken(x, y, myGame.findUnicode(myGame.getTurn()));
                myGame.printBoard();
                //checks if there is a winning :
                if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                    System.out.println("Victory !");
                    System.out.println("Player " + myGame.getTurn() + " is the winner !");
                    break;
                }
                System.out.println("Block number and direction which you want to rotate : (Please enter as :BlockNumber DirectionNumber)");
                System.out.println("Notice : DirectionNumber : 1=clockWise 2=antiClockwise ");
                int block = scanner.nextInt();
                int direction = scanner.nextInt();

                //clockwise rotation :
                if (direction == 1) {
                    if (block == 1)
                        myGame.rotateClockwise(myGame.getBlockTokens(block), 1);
                    if (block == 2)
                        myGame.rotateClockwise(myGame.getBlockTokens(block), 2);
                    if (block == 3)
                        myGame.rotateClockwise(myGame.getBlockTokens(block), 3);
                    if (block == 4)
                        myGame.rotateClockwise(myGame.getBlockTokens(block), 4);
                }
                //anticlockwise rotation :
                if (direction == 2) {
                    if (block == 1)
                        myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 1);
                    if (block == 2)
                        myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 2);
                    if (block == 3)
                        myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 3);
                    if (block == 4)
                        myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 4);
                }
                //myGame.printData();

                //checks if there is a tie or winning :
                if (myGame.checkTie()) {
                    System.out.println("It's a tie !");
                    break;
                }
                myGame.printBoard();
                if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                    System.out.println("Victory !");
                    System.out.println("Player " + myGame.getTurn() + " is the winner !");
                    break;
                }
                //myGame.printData();
            }

        } else if (input == 2) {
            int block = 0;
            int direction = 0;
            while (true) {
                //turn 1 is for computer and turn 2 is for player
                myGame.playTurn();
                if (myGame.getTurn() == 1)
                    System.out.println("Computer turn ... ");
                else if (myGame.getTurn() == 2)
                    System.out.println("Your turn : ");
                //COMPUTER'S TURN :
                if (myGame.getTurn() == 1) {
                    ArrayList<Integer> result = new ArrayList<>();
                    result = myGame.playComputer();
                    System.out.println(result);
                    myGame.addNewToken(result.get(0), result.get(1), myGame.findUnicode(myGame.getTurn()));
                    myGame.printBoard();
                    if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                        System.out.println("Victory !");
                        System.out.println("Computer is the winner !");
                        break;
                    }
                    block = result.get(2);
                    direction = result.get(3);
                    if (direction == 1) {
                        if (block == 1)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 1);
                        if (block == 2)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 2);
                        if (block == 3)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 3);
                        if (block == 4)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 4);
                    }
                    //anticlockwise rotation :
                    if (direction == 2) {
                        if (block == 1)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 1);
                        if (block == 2)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 2);
                        if (block == 3)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 3);
                        if (block == 4)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 4);
                    }
                    String directionString = "";
                    if (direction == 1)
                        directionString = "clockwise";
                    else if (direction == 2)
                        directionString = "antiClockwise";

                    System.out.println("Computer turned block number " + block + " in " + directionString + " direction .");
                    myGame.printBoard();
                    if (myGame.checkTie()) {
                        System.out.println("It's a tie !");
                        break;
                    }
                    if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                        System.out.println("Victory !");
                        System.out.println("Computer is the winner !");
                        break;
                    }
                    //PLAYER'S TURN :
                } else if (myGame.getTurn() == 2) {
                    System.out.println("New token place : (Please enter as :x y , for example :0 0)");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    myGame.addNewToken(x, y, myGame.findUnicode(myGame.getTurn()));
                    myGame.printBoard();
                    if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                        System.out.println("Victory !");
                        System.out.println("You are the winner !");
                        break;
                    }
                    System.out.println("Block number and direction which you want to rotate : (Please enter as :BlockNumber DirectionNumber)");
                    System.out.println("Notice : DirectionNumber : 1=clockWise 2=antiClockwise ");
                    block = scanner.nextInt();
                    direction = scanner.nextInt();

                    //clockwise rotation :
                    if (direction == 1) {
                        if (block == 1)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 1);
                        if (block == 2)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 2);
                        if (block == 3)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 3);
                        if (block == 4)
                            myGame.rotateClockwise(myGame.getBlockTokens(block), 4);
                    }
                    //anticlockwise rotation :
                    if (direction == 2) {
                        if (block == 1)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 1);
                        if (block == 2)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 2);
                        if (block == 3)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 3);
                        if (block == 4)
                            myGame.rotateAnticlockwise(myGame.getBlockTokens(block), 4);
                    }
                    //myGame.printData();


                    if (myGame.checkTie()) {
                        System.out.println("It's a tie !");
                        break;
                    }
                    myGame.printBoard();
                    if (myGame.checkIfWin(myGame.findUnicode(myGame.getTurn()))) {
                        System.out.println("Victory !");
                        System.out.println("You are the winner !");
                        break;
                    }
                }
            }
        }
    }
}
