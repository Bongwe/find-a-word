package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.util.Log;

public class Board {

    private String[][] grid;
    private int rowSize;
    private int columnSize;
    private int lineCount;

    Board(int rows,int columns){
        rowSize = rows;
        columnSize = columns;
        grid = new String[rowSize][columnSize];
        lineCount = 0;
    }

    public void insertLine(String line){
        for(int index = 0; index  < line.length();index++){
            grid[lineCount][index] = line.charAt(index) + "";
        }
        lineCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < rowSize; row++ ){
            for (int col = 0; col < columnSize; col++){
                stringBuilder.append(grid[row][col] + " ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
