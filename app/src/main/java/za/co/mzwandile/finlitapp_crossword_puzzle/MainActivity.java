package za.co.mzwandile.finlitapp_crossword_puzzle;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] correctWords = {"save","grow","earn"};
    private String constructedWord = "";
    private int correctWordTextViewID = 0;
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
        stringBuilder = new StringBuilder(constructedWord);

        stringBuilder.append(clickedButtonText);
        constructedWord = stringBuilder.toString();

    }

    public void checkIfConstructedWordIsCorrect(View view){
        TextView constructedWordView;
        String message = " is incorrect!";
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
