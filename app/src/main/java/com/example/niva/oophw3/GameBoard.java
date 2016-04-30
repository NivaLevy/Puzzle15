package com.example.niva.oophw3;

import java.util.Random;

/**
 * Created by Niva on 01/04/2016.
 */
public class GameBoard {

    private int [][] tiles;
    private final static int NUM_OF_ROWS = 4;
    private final static int EMPTY = 16;

    public GameBoard() {
        generateBoard();
    }

    public int getTile(int i, int j) {
        return tiles[i][j];
    }

    private void generateBoard() {
        //generate random numbers between 1 - 16. 16 is the empty tile
        tiles = new int[NUM_OF_ROWS][NUM_OF_ROWS];
        boolean [] checkIfExists = new boolean[NUM_OF_ROWS * NUM_OF_ROWS];
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++ ) {
                int val = generateRandomNum();
                while (checkIfExists[val - 1])
                    val = generateRandomNum();
                tiles[i][j] = val;
                checkIfExists[val - 1] = true;
            }
        }

    }

    private int generateRandomNum(){
        Random r = new Random();
        int low = 1;
        int high = EMPTY + 1;
        return r.nextInt(high-low) + low;
    }

    public boolean moveTile(int id){
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (tiles[i][j] == id){
                    if(canMove(i, j)){
                        swapLocation(i, j);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean canMove(int row, int column) {

//        int upleft = tiles[row-1][column-1];
//        int up = tiles[row-1][column];
//        int upright = tiles[row-1][column+1];
//        int left = tiles[row][column-1];
//        int right = tiles[row][column+1];
//        int downleft = tiles[row+1][column-1];
//        int down  = tiles[row+1][column];
//        int downright = tiles[row+1][column+1];


//        for (int i = row - 1; i <= row + 1; i++){
//            for (int j = column - 1; j <= column + 1; j++){
//                if (isLegal(i, j)){
//                    if (tiles[i][j] == EMPTY)
//                        return true;
//                }
//            }
//        }

        return (isLegal(row - 1, column) && tiles[row - 1][column] == EMPTY || // up
                isLegal(row, column - 1) && tiles[row][column - 1] == EMPTY || // left
                isLegal(row, column + 1) && tiles[row][column + 1] == EMPTY || // right
                isLegal(row + 1, column) && tiles[row + 1][column] == EMPTY );//down

    }

    private boolean isLegal(int row, int column) {
        return row >= 0 && row < NUM_OF_ROWS  && column >= 0 && column < NUM_OF_ROWS;
    }

    private void swapLocation(int i, int j) {
        int emptyI = getEmptyTile()[0], emptyJ = getEmptyTile()[1];
        int temp = tiles[i][j];
        tiles[i][j] = EMPTY;
        tiles[emptyI][emptyJ] = temp;
    }

    private int[] getEmptyTile(){
        int res [] = new int[2];
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (tiles[i][j] == EMPTY){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return null;
    }

    public boolean isOrdered(){
        int [] arr = new int[NUM_OF_ROWS * NUM_OF_ROWS];
        int arrI = 0;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                arr[arrI] = tiles[i][j];
                arrI++;
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }
}
