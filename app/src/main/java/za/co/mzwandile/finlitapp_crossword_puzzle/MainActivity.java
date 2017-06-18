package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.security.AccessController.getContext;

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
    }

    private void setOnTouchListenerOnLayout(){

        final GridLayout gameGridLayout = (GridLayout) findViewById(R.id.puzzle_grid_layout);
        gameGridLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();

                for (int i = 0; i < gameGridLayout.getChildCount(); i++) {
                    TextView view = (TextView) gameGridLayout.getChildAt(i);

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.setBackgroundResource(R.drawable.normal_color);
                    }

                    if(event.getAction() == MotionEvent.ACTION_MOVE) {
                        if (isPointWithin(x, y, view.getLeft(), view.getRight(),
                                view.getTop(), view.getBottom())) {
                            view.setBackgroundResource(R.drawable.pressed_color);
                        }
                    }
                }
                return true;
            }
        });
    }

    private boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }

    public void getSelectedLetter(View view){
        Log.v("getSelectedLetter","pressed!");
        String clickedButtonText;
        TextView clickedView;
        StringBuilder stringBuilder;

        clickedView = (TextView)view;
        clickedButtonText = clickedView.getText().toString();
       /* clickedView.setBackgroundResource(R.drawable.pressed_color);*/
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
