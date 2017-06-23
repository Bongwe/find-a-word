package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] correctWords = {"save","grow","earn"};
    private String constructedWord = "";
    private List<TextView> selectedTextViews = new LinkedList<>();
    private List<TextView> selectedPuzzleBoxes = new LinkedList<>();
    private LinearLayout mainLayout;
    private GridLayout puzzleLayout;
    //private List<TextView> puzzleBoxes = new LinkedList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTouchListenerOnMainLayout();
        setTouchListenerOnPuzzleLayout();
    }

    private void setTouchListenerOnMainLayout(){
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                highLightSelectedPuzzleBoxes( v,event);
                return true;
            }
        });
    }

    private void setTouchListenerOnPuzzleLayout(){
        puzzleLayout = (GridLayout) findViewById(R.id.puzzle_grid_layout);
        puzzleLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    highLightSelectedPuzzleBoxes(v, event);
                }

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    unHighLightDeselectedPuzzleBoxes(v, event);
                }

                return true;
            }
        });
    }

    private void highLightSelectedPuzzleBoxes(View view, MotionEvent event){

        int x = (int) event.getX();
        int y = (int) event.getY();

        for(int puzzleBox = 0; puzzleBox < puzzleLayout.getChildCount();puzzleBox++){
            TextView currentPuzzleBox = (TextView) puzzleLayout.getChildAt(puzzleBox);
            if (isPointWithin(x, y, currentPuzzleBox.getLeft(), currentPuzzleBox.getRight(),
                    currentPuzzleBox.getTop(), currentPuzzleBox.getBottom())) {
                selectedPuzzleBoxes.add(currentPuzzleBox);
                currentPuzzleBox.setBackgroundResource(R.drawable.pressed_color);
            }
        }
    }

    private void unHighLightDeselectedPuzzleBoxes(View view, MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP) {
            for(int puzzleBox = 0; puzzleBox < selectedPuzzleBoxes.size();puzzleBox++){
                TextView selectedPuzzleBox = selectedPuzzleBoxes.get(puzzleBox);
                selectedPuzzleBox.setBackgroundResource(R.drawable.normal_color);
            }
        }
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
        selectedTextViews.add(clickedView);
        stringBuilder = new StringBuilder(constructedWord);

        stringBuilder.append(clickedButtonText);
        constructedWord = stringBuilder.toString();
    }

    public void checkIfConstructedWordIsCorrect(View view){
        String message = constructedWord +" is incorrect!";
        changeBackgroundColor();
        for (int i = 0; i < correctWords.length;i++){
            if (correctWords[i].toUpperCase().equals(constructedWord)) {
                createCorrectWordTextView(constructedWord);
                constructedWord = "";
                return;
            }
        }
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
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
}
