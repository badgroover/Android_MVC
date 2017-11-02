package PMViews;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nsohoni on 16/09/17.
 */

public class Boogle {
    char[][] board = {{'A','B','X','H'},
                      {'D','C','B','W'},
                      {'W','B','T','I'},
                      {'T','S','J','I'}};

    String[] words = {"ABCBBS",
            "TAXA",
            "ABA",
            "VITA",
            "VITTA",
            "GO",
            "AXAL",
            "LATTE",
            "TALA",
            "HVLTI"};

    boolean found = false;


    public void solveBoard() {
        ArrayList<String> foundWords = new ArrayList<>();
        for(int i =0; i<words.length; i++) {
            String word = words[i];
            if(wordExists(board, word)) {
                foundWords.add(word);
            }
        }
        //return as an array
        Collections.sort(foundWords);
        String [] array = foundWords.toArray(new String[foundWords.size()]);

        //return array;


    }

    boolean wordExists(char[][] board, String word) {
        for (int row = 0; row < board.length; row++)
           for (int col = 0; col < board[0].length; col++) {
                char c = board[row][col];
                if (word.charAt(0) == c) {
                    char[][]copy = getCopy(board);
                    found = false;
                    findWord(copy, row, col, word, 1);
                    if(found)
                        return found;

                }
            }
        return false;
    }

    void findWord(char[][]board, int row, int col, String word, int index) {
        if(word.length() == index) {
            found =  true;
        } else {
            int newRow = 0;
            int newCol = 0;
            char c;

            //left
            newRow = row;
            newCol = col-1;
            if(newCol >= 0 && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                c = board[row][col];
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
                if(!found)
                    board[row][col] = c;
            }

            //bottom
            newRow = row +1;
            newCol = col;
            if(newRow < board.length && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                c = board[row][col];
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
                if(!found)
                    board[row][col] = c;


            }

            //right
            newRow = row;
            newCol = col+1;
            if(newCol < board[0].length && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }

            //top
            newRow = row - 1;
            newCol = col;
            if(newRow >= 0 && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }




            //left-top
            newRow = row - 1;
            newCol = col - 1;
            if(newRow >= 0 && newCol >=0 && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }


            //right-top
            newRow = row - 1;
            newCol = col + 1;
            if(newRow >= 0 && newCol < board[0].length && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }

            //left-bottom
            newRow = row + 1;
            newCol = col - 1;
            if(newRow < board.length && newCol >= 0 && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }

            //right-bottom
            newRow = row + 1;
            newCol = col + 1;
            if(newRow < board.length && newCol < board[0].length && board[newRow][newCol] != '.' && board[newRow][newCol] == word.charAt(index)) {
                board[row][col] = '.';
                findWord(board, newRow, newCol, word, index+1);
            }

        }
    }

    char[][] getCopy(char[][] board) {
        char[][] copy = new char[board.length][board[0].length];
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[0].length; col++) {
                copy[row][col] = board[row][col];
            }
        return copy;
    }
}
