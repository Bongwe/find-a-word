package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] correctWords = {"save","grow","earn"};
    private String constructedWord = "";
    private int correctWordTextViewID = 0;
    private List<TextView> selectedTextViews = new LinkedList<>();
    private List<TextView> puzzleBlocks = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnTouchListenerOnLayout();
        setOnTouchListenerOnButon();
    }

    private void setOnTouchListenerOnButon(){
        final int ROWS = 4;
        final int COLS = 5;
        final int SIZE = 4;
        final LinearLayout puzzleBoxLayout[] = new LinearLayout[SIZE];

        puzzleBoxLayout[0] = (LinearLayout) findViewById(R.id.puzzle_box_layout_0);
        puzzleBoxLayout[1] = (LinearLayout) findViewById(R.id.puzzle_box_layout_1);
        puzzleBoxLayout[2] = (LinearLayout) findViewById(R.id.puzzle_box_layout_2);
        puzzleBoxLayout[3] = (LinearLayout) findViewById(R.id.puzzle_box_layout_3);

        for( int row = 0;row < ROWS; row++) {
            final LinearLayout currentLayout = puzzleBoxLayout[row];
            for( int col = 0;col < COLS; col++) {
                TextView currentView = (TextView) currentLayout.getChildAt(col);
                currentView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            v.setBackgroundResource(R.drawable.normal_color);
                        }
                        return true;
                    }
                });
            }
        }
    }

    private void setOnTouchListenerOnLayout(){
        final int ROWS = 4;
        final int COLS = 5;
        final int SIZE = 4;
        final LinearLayout puzzleBoxLayout[] = new LinearLayout[SIZE];

        puzzleBoxLayout[0] = (LinearLayout) findViewById(R.id.puzzle_box_layout_0);
        puzzleBoxLayout[1] = (LinearLayout) findViewById(R.id.puzzle_box_layout_1);
        puzzleBoxLayout[2] = (LinearLayout) findViewById(R.id.puzzle_box_layout_2);
        puzzleBoxLayout[3] = (LinearLayout) findViewById(R.id.puzzle_box_layout_3);

        for( int row = 0;row < ROWS; row++) {
            final LinearLayout currentLayout =  puzzleBoxLayout[row];
            currentLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < currentLayout.getChildCount(); i++) {
                            TextView view = (TextView) currentLayout.getChildAt(i);
                            view.setBackgroundResource(R.drawable.normal_color);
                        }
                    }

                    for (int i = 0; i < currentLayout.getChildCount(); i++) {
                        TextView view = (TextView) currentLayout.getChildAt(i);
                       /* if (!isPointWithin(x, y, view.getLeft(), view.getRight(), view.getTop(),
                                view.getBottom())) {
                            view.setBackgroundResource(R.drawable.normal_color);
                        }*/
                     /*  if(event.getAction() == MotionEvent.ACTION_DOWN){*/
                           if (isPointWithin(x, y, view.getLeft(), view.getRight(), view.getTop(),
                                   view.getBottom())) {
                               view.setBackgroundResource(R.drawable.pressed_color);
                           }
                      /* }*/

                    }
                    return true;
                }
            });
        }


       /* for(int row = 0;row < ROWS; row++){
            TextView view;
            for(int col = 0; col < COLS;col++){
                view = (TextView) puzzleBoxLayout[row].getChildAt(col);
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        *//*if(event.getAction() == MotionEvent.ACTION_MOVE){
                            createCorrectWordTextView( "touchd" );
                          *//**//*  createCorrectWordTextView( v.getId() + "" );*//**//*
                            *//**//*v.setBackgroundResource(R.drawable.pressed_color);*//**//*
                            // Do what you want
                            return true;
                        }*//*
                        return true;
                    }
                });
            }
        }*/
    }

    private boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }

    public void getSelectedLetter(View view){
        String clickedButtonText;
        TextView clickedView;
        StringBuilder stringBuilder;

        clickedView = (TextView)view;
        clickedButtonText = clickedView.getText().toString();
        clickedView.setBackgroundResource(R.drawable.pressed_color);
        selectedTextViews.add(clickedView);
        stringBuilder = new StringBuilder(constructedWord);

        stringBuilder.append(clickedButtonText);
        constructedWord = stringBuilder.toString();
    }

    public void checkIfConstructedWordIsCorrect(View view){
        String message = " is incorrect!";
        changeBackgroundColor();
        for (int i = 0; i < correctWords.length;i++){
            if (correctWords[i].toUpperCase().equals(constructedWord)) {
                createCorrectWordTextView(constructedWord);
                constructedWord = "";
                return;
            }
        }
        createInCorrectWordTextView(constructedWord);
        constructedWord = "";
    }

    private void changeBackgroundColor(){
        Iterator iterator = selectedTextViews.iterator();

        while (iterator.hasNext()){
            TextView view = (TextView) iterator.next();
            view.setBackgroundResource(R.drawable.normal_color);
        }
        selectedTextViews.clear();
    }

    private void createCorrectWordTextView(String word){
        TextView newTextView = new TextView(this);
        LinearLayout layout = (LinearLayout)findViewById(R.id.correctWordsLayout);
        int padding = 7;
        int margin = 5;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin,margin,margin,margin);

        newTextView.setText(word);
        newTextView.setTextSize(16);
        newTextView.setTextColor(Color.parseColor("#000000"));
        newTextView.setPadding(padding,padding,padding,padding);
        newTextView.setLayoutParams(params);
        newTextView.setBackgroundColor(Color.parseColor("#67BC8C"));
        layout.addView(newTextView);
    }

    private void createInCorrectWordTextView(String word){
        TextView newTextView = new TextView(this);
        LinearLayout layout = (LinearLayout)findViewById(R.id.correctWordsLayout);
        int padding = 7;
        int margin = 5;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin,margin,margin,margin);

        newTextView.setText(word);
        newTextView.setTextSize(16);
        newTextView.setTextColor(Color.parseColor("#000000"));
        newTextView.setPadding(padding,padding,padding,padding);
        newTextView.setLayoutParams(params);
        newTextView.setBackgroundColor(Color.parseColor("#BC6E67"));
        layout.addView(newTextView);
    }
}
