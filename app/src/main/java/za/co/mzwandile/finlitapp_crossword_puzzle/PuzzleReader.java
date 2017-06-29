package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PuzzleReader {

    final int COLUMNSIZE = 5;
    final int ROWSIZE = 4;
    private List<String> contentFromFile  = new LinkedList<>();
    private Board board;
    private Words words;
    Context context;

    public PuzzleReader(Context context){
        board = new Board(ROWSIZE,COLUMNSIZE);
        words = new Words();
        this.context = context;
        readFromFile(context);
        insertWordsInToBoard(board, contentFromFile);
        createAnswers(words,contentFromFile);
        System.out.print(board);
    }

    private void readFromFile(Context context){
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.banking);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String eachline = bufferedReader.readLine();
            while (eachline != null) {
                contentFromFile.add(eachline);
                eachline = bufferedReader.readLine();
            }
            inputStream.close();
        }catch (Exception e){
            Log.e("File read error",e.toString());
        }
    }

    private void insertWordsInToBoard(Board board, List<String> fileContentList){
        Iterator<String> iterator = fileContentList.iterator();
        while(iterator.hasNext()) {
            String line = iterator.next();
            if(line.toUpperCase().equals("WORDS")){
                break;
            }
            board.insertLine(line);
        }
    }

    private void createAnswers(Words words, List<String> fileContentList){
        Iterator<String> iterator = fileContentList.iterator();
        while(iterator.hasNext()) {
            String word = iterator.next();
            words.setAnswer(word);
        }
    }

}
