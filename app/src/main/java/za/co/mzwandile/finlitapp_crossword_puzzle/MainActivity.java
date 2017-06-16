package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
